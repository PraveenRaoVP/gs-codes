package android.caged.jogfit.presentation.home

import android.caged.jogfit.presentation.common.SnackbarState
import android.caged.jogfit.presentation.navigation.Routes
import android.location.Location
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.mutableStateOf
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    private val credentialManager: CredentialManager
) : ViewModel() {

    val currentUser = auth.currentUser

    private val _currentLocation = MutableLiveData<Location>()
    val currentLocation: LiveData<Location>
        get() = _currentLocation

    val destinationLocation = mutableStateOf<Location?>(null)

    fun setLocation(location: Location) {
        _currentLocation.postValue(location)
    }

    fun onEvent(event: HomeEvent) {
        when(event) {
            is HomeEvent.CalculateRandomPointWithinRadiusOfCurrentLocation -> {
                val destinationLocation = calculateRandomPointWithinCircumference(event.radiusInKms, event.currentLocation)
            }
            HomeEvent.StartJourney -> {
                TODO()
            }
        }
    }

    private fun calculateRandomPointWithinCircumference(radiusInKms: Double, currentLocation: Location): Location {
        TODO()
    }


    fun onSnackbarEvent(snackbarEvent: SnackbarState, snackbarHostState: SnackbarHostState, message: String?) {
        when (snackbarEvent) {
            is SnackbarState.Action -> {
                viewModelScope.launch {
                    snackbarHostState
                        .showSnackbar(
                            message = message!!,
                            actionLabel = "Dismiss",
                            duration = SnackbarDuration.Short
                        )
                    onSnackbarEvent(SnackbarState.Dismissed, snackbarHostState, null)
                }
            }
            is SnackbarState.Dismissed -> {
                viewModelScope.launch {
                    snackbarHostState.currentSnackbarData?.dismiss()
                }
            }
        }
    }
}