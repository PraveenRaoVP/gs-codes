package android.caged.jetmapsampleapp.feature_google_places.data.remote.dto

import android.caged.jetmapsampleapp.feature_google_places.domain.model.OverviewPolyline

data class OverviewPolylineDto(
    val points: String
){
    fun toOverviewPolyline(): OverviewPolyline {
        return OverviewPolyline(
            points = points
        )
    }
}
