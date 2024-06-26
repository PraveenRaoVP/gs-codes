package android.caged.jetmapsampleapp.feature_google_places.presentation

import android.caged.jetmapsampleapp.feature_google_places.domain.model.GooglePlacesInfo

data class GooglePlacesInfoState(val direction: GooglePlacesInfo? = null, val isLoading: Boolean = false)