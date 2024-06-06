package android.caged.notes.presentation.sign_up

data class SignUpState(
    val email: String = "",
    val password: String = "",
    val repeatPassword: String = ""
)