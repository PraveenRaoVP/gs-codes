package android.caged.notesapplication.presentation.sign_in

import android.caged.notesapplication.MainViewModel
import android.caged.notesapplication.R
import android.caged.notesapplication.data.services.StorageServiceImpl
import android.caged.notesapplication.domain.services.AccountService
import android.caged.notesapplication.domain.services.LogService
import android.caged.notesapplication.domain.services.StorageService
import android.caged.notesapplication.ext.isValidEmail
import android.caged.notesapplication.presentation.navigation.Routes
import android.caged.notesapplication.presentation.snackbar.SnackbarManager
import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.lifecycle.viewModelScope
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import android.caged.notesapplication.R.string as AppText

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val accountService: AccountService,
    private val auth: FirebaseAuth,
    private val credentialManager: CredentialManager,
    logService: LogService
) : MainViewModel(logService) {

    var uiState = mutableStateOf(SignInUiState())
        private set

    private val email
        get() = uiState.value.email

    private val password
        get() = uiState.value.password

    private val errorMessage
        get() = uiState.value.errorMessage

    fun onEmailChange(email: String) {
        uiState.value = uiState.value.copy(email = email)
    }

    fun onPasswordChange(password: String) {
        uiState.value = uiState.value.copy(password = password)
    }

    fun onEvent(event: SignInEvent) {
        when(event) {
            is SignInEvent.SigningInByGoogle -> {
//                uiState.value = uiState.value.copy(isLoading = true)
            }
            is SignInEvent.SignedInByGoogle -> {
//                uiState.value = uiState.value.copy(isLoading = false)
                // navigate to the next screen
            }
            is SignInEvent.ErrorSignedInByGoogle -> {
//                uiState.value = uiState.value.copy(isLoading = false)
                uiState.value = uiState.value.copy(errorMessage = "Error signing in with Google")
            }
        }
    }

    fun onGoogleSignIn(context: Context, navigateTo: (String, String) -> Unit) {
        onEvent(SignInEvent.SigningInByGoogle)

        val googleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId(context.resources.getString(R.string.google_web_client_id))
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        viewModelScope.launch {
            try {
                val result = credentialManager.getCredential(request=request, context=context)
                val credential = result.credential

                val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                val idToken = googleIdTokenCredential.idToken
                val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)
                auth.signInWithCredential(firebaseCredential)
                    .addOnCompleteListener {task ->
                        if(task.isSuccessful) {
                            navigateTo(Routes.NotesRoute.route, Routes.SignInRoute.route)
                        } else {
                            onEvent(SignInEvent.ErrorSignedInByGoogle)
                            SnackbarManager.showMessage(AppText.generic_error)
                        }
                    }
                return@launch
            } catch (e: Exception) {
                e.printStackTrace()
                return@launch
            }
        }
    }

    fun onSignInClick(navigateAndPopUp: (String, String) -> Unit) {
        try {
            if (!email.isValidEmail()) {
                SnackbarManager.showMessage(AppText.email_error)
                Log.i("SignInViewModel", "Email is not valid")
                return
            }

            if (password.isBlank()) {
                SnackbarManager.showMessage(AppText.empty_password_error)
                Log.i("SignInViewModel", "Password is not valid.")
                return
            }

            launchCatching {
                accountService.authenticate(email, password)
                navigateAndPopUp(Routes.NotesRoute.route, Routes.SignInRoute.route)
            }
        } catch(e: Exception) {
            SnackbarManager.showMessage(AppText.invalid_credentials_error)
        }
    }

    fun onForgotPasswordClick() {
        if (!email.isValidEmail()) {
            SnackbarManager.showMessage(AppText.email_error)
            return
        }

        launchCatching {
            auth.sendPasswordResetEmail(email).await()
            SnackbarManager.showMessage(AppText.recovery_email_sent)
        }
    }

    fun emptyErrorMessage() {
        uiState.value = uiState.value.copy(errorMessage = "")
    }
}