package android.caged.jetmapsampleapp.featur_typicode_users.presentation

import android.caged.jetmapsampleapp.featur_typicode_users.domain.model.UserInfo

data class UsersInfoState(val usersInfo: List<UserInfo> = emptyList(), val isLoading: Boolean = false)