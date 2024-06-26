package android.caged.jogfit.presentation.auth.signup

sealed class SignUpEvent {
    data object OnSignUpClick : SignUpEvent()
    data class EmailChanged(val email: String) : SignUpEvent()
    data class UsernameChanged(val username: String) : SignUpEvent()
    data class PasswordChanged(val password: String) : SignUpEvent()
    data class ConfirmPasswordChanged(val confirmPassword: String) : SignUpEvent()
    data object ClearAllErrors : SignUpEvent()
}