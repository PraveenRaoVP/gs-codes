package android.caged.jetmapsampleapp.featur_typicode_users.domain.repository

import android.caged.jetmapsampleapp.util.Resource
import android.caged.jetmapsampleapp.featur_typicode_users.domain.model.UserInfo
import kotlinx.coroutines.flow.Flow

interface UserInfoRepository {
    fun getUsersInfo(): Flow<Resource<List<UserInfo>>>
}