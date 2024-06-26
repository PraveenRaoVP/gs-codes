package android.caged.jetmapsampleapp.feature_google_places.domain.model

import android.caged.jetmapsampleapp.feature_google_places.domain.model.Distance
import android.caged.jetmapsampleapp.feature_google_places.domain.model.Duration

data class Legs(
    val distance: Distance,
    val duration: Duration
)
