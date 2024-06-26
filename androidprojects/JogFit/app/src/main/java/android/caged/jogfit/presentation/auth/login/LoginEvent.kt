package android.caged.jogfit.presentation.auth.login

sealed class LoginEvent {
    data object OnLoginWithGoogleClick : LoginEvent()
    data object SignInEvent : LoginEvent()

    data class EmailChanged(val email: String) : LoginEvent()
    data class PasswordChanged(val password: String) : LoginEvent()
    data class ForgotPassword(val email: String) : LoginEvent()
}