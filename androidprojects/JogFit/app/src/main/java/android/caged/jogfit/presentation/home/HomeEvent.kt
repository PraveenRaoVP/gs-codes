package android.caged.jogfit.presentation.home

import android.location.Location

sealed class HomeEvent {
    data class CalculateRandomPointWithinRadiusOfCurrentLocation(val radiusInKms: Double, val currentLocation: Location) : HomeEvent()
    data object StartJourney : HomeEvent()
}