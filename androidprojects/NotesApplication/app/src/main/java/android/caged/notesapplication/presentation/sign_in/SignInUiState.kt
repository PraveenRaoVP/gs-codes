package android.caged.notesapplication.presentation.sign_in

data class SignInUiState (
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = ""
)