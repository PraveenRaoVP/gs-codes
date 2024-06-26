package android.caged.jetmapsampleapp.feature_google_places.domain.model

import android.caged.jetmapsampleapp.feature_google_places.domain.model.Legs
import android.caged.jetmapsampleapp.feature_google_places.domain.model.OverviewPolyline

data class Routes(
    val summary: String,
    val overview_polyline: OverviewPolyline,
    val legs: List<Legs>
)
