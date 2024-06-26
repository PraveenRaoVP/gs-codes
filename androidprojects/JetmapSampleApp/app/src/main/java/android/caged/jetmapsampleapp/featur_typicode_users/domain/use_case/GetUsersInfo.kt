package android.caged.jetmapsampleapp.featur_typicode_users.domain.use_case

import android.caged.jetmapsampleapp.util.Resource
import android.caged.jetmapsampleapp.featur_typicode_users.domain.model.UserInfo
import android.caged.jetmapsampleapp.featur_typicode_users.domain.repository.UserInfoRepository
import kotlinx.coroutines.flow.Flow

class GetUsersInfo(private val repository: UserInfoRepository) {
    operator fun invoke(): Flow<Resource<List<UserInfo>>> = repository.getUsersInfo()

}