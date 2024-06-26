package android.caged.jogfit.presentation.auth.login

import android.caged.jogfit.R
import android.caged.jogfit.presentation.navigation.Routes
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    private val credentialManager: CredentialManager
) : ViewModel() {

    var uiState = mutableStateOf(LoginUiState())
        private set

    private val email
        get() = uiState.value.email

    private val password
        get() = uiState.value.password

    private val isError
        get() = uiState.value.isError

    private val errorMessage
        get() = uiState.value.errorMessage

    private fun onEmailChange(email: String) {
        uiState.value = uiState.value.copy(email = email.trimStart { it == ' ' })
    }

    private fun onPasswordChange(password: String) {
        uiState.value = uiState.value.copy(password = password)
    }

    private fun signInWithGoogle(navigateTo: (String, String) -> Unit, context: Context) {
        val googleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId(context.resources.getString(R.string.google_web_client_id))
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        viewModelScope.launch {
            try {
                val result = credentialManager.getCredential(request = request, context = context)
                val credential = result.credential

                val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                val idToken = googleIdTokenCredential.idToken
                val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)
                auth.signInWithCredential(firebaseCredential)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            navigateTo(Routes.AppNavigationScreen.route, Routes.AppStartRoute.route)
                        } else {
                            val exception = task.exception
                            if (exception != null && exception.message?.contains("The email address is already in use by another account") == true) {
                                // Email already in use, link accounts
                                linkGoogleCredentialToEmailAccount(
                                    firebaseCredential,
                                    context,
                                    navigateTo
                                )
                            } else {
                                Toast.makeText(
                                    context,
                                    "Error signing in with Google. Please try again!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                return@launch
            } catch (e: Exception) {
                e.printStackTrace()
                return@launch
            }
        }
    }

    fun onEvent(event: LoginEvent, context: Context, navigateTo: (String, String) -> Unit) {
        when(event) {
            is LoginEvent.OnLoginWithGoogleClick -> signInWithGoogle(navigateTo, context)
            is LoginEvent.SignInEvent -> signInWithEmailAndPassword(navigateTo, context)
            is LoginEvent.EmailChanged -> onEmailChange(event.email)
            is LoginEvent.PasswordChanged -> onPasswordChange(event.password)
            is LoginEvent.ForgotPassword -> onForgotPassword(event.email, navigateTo)
        }
    }

    private fun onForgotPassword(email: String, navigateToPopUp: (String, String) -> Unit) {
        viewModelScope.launch {
            try {
                auth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
                    if(task.isSuccessful) {
                        uiState.value = uiState.value.copy(recoveryEmailSent = true)
                        navigateToPopUp(Routes.LoginWithEmailPasswordRoute.route, Routes.ForgotPassword.route)
                    } else {
                        uiState.value = uiState.value.copy(isError = true, errorMessage = "Invalid email address")
                    }
                }.addOnFailureListener {
                    uiState.value = uiState.value.copy(isError = true, errorMessage = "Unexpected error occured. Please try again.")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun linkGoogleCredentialToEmailAccount(
        googleCredential: AuthCredential,
        context: Context,
        navigateTo: (String, String) -> Unit
    ) {
        // First, sign in with email and password
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Successfully signed in with email, now link Google credential
                    auth.currentUser?.linkWithCredential(googleCredential)
                        ?.addOnCompleteListener { linkTask ->
                            if (linkTask.isSuccessful) {
                                // Accounts linked successfully
                                Toast.makeText(
                                    context,
                                    "Google account linked successfully.",
                                    Toast.LENGTH_SHORT
                                ).show()
                                navigateTo(
                                    Routes.AppNavigationScreen.route,
                                    Routes.AppStartRoute.route
                                )
                            } else {
                                // Handle error during linking
                                Toast.makeText(
                                    context,
                                    "Error linking Google account. Please try again!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                } else {
                    // Handle error during email sign-in
                    Toast.makeText(
                        context,
                        "Invalid email/password. Please try again!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun signInWithEmailAndPassword(navigateToPopUp: (String, String) -> Unit, context: Context) {
        if (uiState.value.email.isBlank()) {
            uiState.value =
                uiState.value.copy(isError = true, errorMessage = "Email cannot be empty")
            return
        }
        if (uiState.value.password.isBlank()) {
            uiState.value =
                uiState.value.copy(isError = true, errorMessage = "Password cannot be empty")
            return
        }
        viewModelScope.launch {
            try {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        uiState.value = uiState.value.copy(isError = false, errorMessage = "")
                        navigateToPopUp(
                            Routes.AppNavigationScreen.route,
                            Routes.AppStartRoute.route
                        )
                    } else {
                        uiState.value = uiState.value.copy(
                            isError = true,
                            errorMessage = "Invalid Credentials. Please Try Again!"
                        )
                        // show snack bar message to user of invalid credentials
                        Toast.makeText(
                            context,
                            "Invalid Credentials. Please Try Again!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}