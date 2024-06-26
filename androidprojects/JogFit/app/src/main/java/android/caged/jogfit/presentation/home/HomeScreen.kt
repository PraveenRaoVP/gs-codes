package android.caged.jogfit.presentation.home

import android.Manifest
import android.caged.jogfit.R
import android.caged.jogfit.domain.services.LocationForegroundService
import android.caged.jogfit.presentation.common.SnackbarState
import android.caged.jogfit.utils.BitmapHelper
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.delay

fun Location.toLatLng(): LatLng? {
    return if (this.latitude == 0.0 && this.longitude == 0.0) {
        null
    } else {
        LatLng(this.latitude, this.longitude)
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen(
    navigatePopUp: (String, String) -> Unit,
    navigateTo: (String) -> Unit,
    onSnackbarEvent: (SnackbarState, SnackbarHostState, String) -> Unit,
    snackbarHostState: SnackbarHostState
) {
    val context = LocalContext.current
    val locationPermissionState = rememberPermissionState(permission = Manifest.permission.ACCESS_FINE_LOCATION)
    var currentLocation by remember { mutableStateOf<Location?>(null) }

    // Register BroadcastReceiver to receive location updates
    DisposableEffect(Unit) {
        val receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                if (intent?.action == "location_update") {
                    currentLocation = intent.getParcelableExtra("location", Location::class.java)
                }
            }
        }
        val filter = IntentFilter("location_update")
        context.registerReceiver(receiver, filter, Context.RECEIVER_NOT_EXPORTED)

        onDispose {
            context.unregisterReceiver(receiver)
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        if (!locationPermissionState.status.isGranted) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = { locationPermissionState.launchPermissionRequest() }) {
                    Text("Request Location Permission")
                }
            }
        } else {
            if (currentLocation == null) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                    Spacer(modifier = Modifier.height(10.dp))
                    var loadingText by remember { mutableStateOf("Fetching current location") }
                    LaunchedEffect(key1 = Unit) {
                        while (true) {
                            delay(3000)
                            loadingText = when (loadingText) {
                                "Fetching current location" -> "Fetching current location."
                                "Fetching current location." -> "Fetching current location.."
                                "Fetching current location.." -> "Fetching current location..."
                                else -> "Fetching current location"
                            }
                        }
                    }
                    Text(loadingText)
                }
            } else {
                GoogleMapContent(currentLocation = currentLocation, context = context)
            }
        }
    }

    LaunchedEffect(key1 = Unit) {
        LocationForegroundService.startService(context)
    }

    DisposableEffect(key1 = context) {
        onDispose {
            LocationForegroundService.stopService(context)
        }
    }
}

@Composable
fun GoogleMapContent(
    currentLocation: Location?,
    context: Context
) {
    val cameraPositionState = rememberCameraPositionState {
        position =
            CameraPosition.fromLatLngZoom(currentLocation?.toLatLng() ?: LatLng(0.0, 0.0), 15f)
    }

    val playerIcon: BitmapDescriptor by lazy {
        val color = ContextCompat.getColor(context, R.color.primary)
        BitmapHelper.vectorToBitmap(context, R.drawable.round_player_icon, color)
    }

    Log.i("HomeScreen", "currentLocation: $currentLocation")
    Log.i("HomeScreen", "Camera Position: ${cameraPositionState.position}")

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        currentLocation?.toLatLng()?.let { latLng ->
            Marker(
                state = MarkerState(position = latLng),
                title = "Current Location",
                snippet = "You are here",
                icon = playerIcon
            )
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        navigatePopUp = { _, _ -> },
        navigateTo = {},
        snackbarHostState = remember { SnackbarHostState() },
        onSnackbarEvent = { _, _, _ -> }
    )
}