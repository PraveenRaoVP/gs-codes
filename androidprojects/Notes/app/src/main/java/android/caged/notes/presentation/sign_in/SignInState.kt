package android.caged.notes.presentation.sign_in

import com.google.android.gms.auth.api.signin.GoogleSignInAccount

data class SignInState(
    val email: String? = "",
    val password: String? = "",
    val isGoogleSignIn: Boolean = false,
    val googleAccount: GoogleSignInAccount? = null // Optional: if you want to store Google account details
)