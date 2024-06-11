package android.caged.videoapplicationapp.presentation.login

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    var error: String? = null
)