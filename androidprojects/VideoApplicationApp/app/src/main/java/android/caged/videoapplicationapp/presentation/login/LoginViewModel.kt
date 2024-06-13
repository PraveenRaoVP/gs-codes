package android.caged.videoapplicationapp.presentation.login

import android.caged.videoapplicationapp.firebase.FirebaseClient
import android.caged.videoapplicationapp.presentation.navigation.Routes
import android.caged.videoapplicationapp.repository.MainRepository
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    var uiState = mutableStateOf(LoginUiState())
        private set

    private val email: String
        get() = uiState.value.email

    private val password: String
        get() = uiState.value.password

    fun onEmailChange(email: String) {
        uiState.value = uiState.value.copy(email = email)
    }

    fun onPasswordChange(password: String) {
        uiState.value = uiState.value.copy(password = password)
    }

    fun onClick(onNavigate : (String, String, String) -> Unit) {
        viewModelScope.launch {
            mainRepository.login(email, password) { isDone, reason ->
                if(!isDone) {
                    uiState.value = uiState.value.copy(error = reason)
                } else {
                    val route = Routes.HomeRoute.createRoute(uiState.value.email)
                    onNavigate(route, Routes.LoginRoute.route, uiState.value.email)
                }
            }
        }
    }
}