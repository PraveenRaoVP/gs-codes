package android.caged.jogfit.presentation.auth.signup

data class SignUpUiState(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val repeatPassword: String = "",
    val isLoading: Boolean = false,
    val isUsernameError: Boolean = false,
    val isEmailError: Boolean = false,
    val isPasswordError: Boolean = false,
    val isRepeatPasswordError: Boolean = false,
    val usernameError: String = "",
    val emailError: String = "",
    val passwordError: String = "",
    val repeatPasswordError: String = "",
)
