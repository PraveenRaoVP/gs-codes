package android.caged.jetmapsampleapp.feature_google_places.domain.use_case

import android.caged.jetmapsampleapp.util.Resource
import android.caged.jetmapsampleapp.feature_google_places.domain.model.GooglePlacesInfo
import android.caged.jetmapsampleapp.feature_google_places.domain.repository.GooglePlacesInfoRepository
import kotlinx.coroutines.flow.Flow

class GetDirectionInfo(private val repository: GooglePlacesInfoRepository) {
    operator fun invoke(origin: String, destination: String, key: String): Flow<Resource<GooglePlacesInfo>> = repository.getDirection(origin = origin, destination = destination, key = key)
}