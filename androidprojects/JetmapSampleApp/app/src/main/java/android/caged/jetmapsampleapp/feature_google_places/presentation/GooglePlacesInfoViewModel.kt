package android.caged.jetmapsampleapp.feature_google_places.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import android.caged.jetmapsampleapp.TAG
import android.caged.jetmapsampleapp.util.Resource
import android.caged.jetmapsampleapp.feature_google_places.domain.use_case.GetDirectionInfo
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class GooglePlacesInfoViewModel @Inject constructor(private val getDirectionInfo: GetDirectionInfo): ViewModel() {

    private val _googlePlacesInfoState = mutableStateOf(GooglePlacesInfoState())
    val googlePlacesInfoState: State<GooglePlacesInfoState> = _googlePlacesInfoState

//    private var _polyLinesPoints = MutableSharedFlow<List<LatLng>>()
//    val polyLinesPoints = _polyLinesPoints.asSharedFlow()

    private val _polyLinesPoints = MutableStateFlow<List<LatLng>>(emptyList())
    val polyLinesPoints: StateFlow<List<LatLng>>
        get() = _polyLinesPoints

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val evenFlow = _eventFlow.asSharedFlow()

    fun getDirection(origin: String, destination: String, key: String) {
        viewModelScope.launch {
            getDirectionInfo(origin = origin, destination = destination, key = key).onEach { res ->
                when (res) {
                    is Resource.Success -> {
                        val direction = res.data
                        Log.d(TAG, "getDirection: $direction")
                        _googlePlacesInfoState.value = googlePlacesInfoState.value.copy(
                            direction = direction,
                            isLoading = false
                        )
                        _eventFlow.emit(UIEvent.ShowSnackBar(message = "Direction Loaded"))

                        val routes = direction?.routes
                        if (!routes.isNullOrEmpty()) {
                            val polylinePoints = routes[0].overview_polyline.points
                            _eventFlow.emit(
                                UIEvent.ShowSnackBar(
                                    message = polylinePoints.toString()
                                )
                            )
                            decoPoints(points = polylinePoints)
                            Log.d(TAG, "POLYLINE:  $polylinePoints")
                        } else {
                            Log.e(TAG, "No routes found in the direction response")
                            _eventFlow.emit(UIEvent.ShowSnackBar(message = "No routes found"))
                        }
                    }
                    is Resource.Error -> {
                        _eventFlow.emit(
                            UIEvent.ShowSnackBar(
                                message = res.message ?: "Unknown Error"
                            )
                        )
                    }
                    is Resource.Loading -> {
                        _googlePlacesInfoState.value = googlePlacesInfoState.value.copy(
                            direction = null,
                            isLoading = true
                        )
                        _eventFlow.emit(UIEvent.ShowSnackBar(message = "Loading Direction"))
                    }
                }
            }.launchIn(this)
        }
    }


    sealed class UIEvent{
        data class ShowSnackBar(val message: String): UIEvent()
    }

    private fun decoPoints(points: String): List<LatLng>{
        _polyLinesPoints.value = decodePoly(points)
        return decodePoly(points);
    }

    /**
     * Method to decode polyline points
     * Courtesy : https://jeffreysambells.com/2010/05/27/decoding-polylines-from-google-maps-direction-api-with-java
     */
    private fun decodePoly(encoded: String): List<LatLng> {
        val poly = ArrayList<LatLng>()
        var index = 0
        val len = encoded.length
        var lat = 0
        var lng = 0

        while (index < len) {
            var b: Int
            var shift = 0
            var result = 0
            do {
                b = encoded[index++].code - 63
                result = result or (b and 0x1f shl shift)
                shift += 5
            } while (b >= 0x20)
            val dlat = if (result and 1 != 0) (result shr 1).inv() else result shr 1
            lat += dlat

            shift = 0
            result = 0
            do {
                b = encoded[index++].code - 63
                result = result or (b and 0x1f shl shift)
                shift += 5
            } while (b >= 0x20)
            val dlng = if (result and 1 != 0) (result shr 1).inv() else result shr 1
            lng += dlng

            val p = LatLng(lat.toDouble() / 1E5,
                lng.toDouble() / 1E5)
            poly.add(p)
        }

        return poly
    }
}