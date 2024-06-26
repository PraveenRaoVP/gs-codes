package android.caged.jogfit.presentation.auth.login

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isError: Boolean = false,
    val errorMessage: String = "",
    val recoveryEmailSent: Boolean = false,
    val isValidEmail: Boolean = true
)