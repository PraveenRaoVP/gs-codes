package android.caged.notes.presentation.sign_in

import android.app.Activity
import android.caged.notes.MainViewModel
import android.caged.notes.data.ext.isValidEmail
import android.caged.notes.data.ext.isValidPassword
import android.caged.notes.data.ext.passwordMatches
import android.caged.notes.data.snackbar.SnackbarManager
import android.caged.notes.domain.services.AccountService
import android.caged.notes.domain.services.LogService
import android.caged.notes.presentation.navigation.Route
import android.content.Intent
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import android.caged.notes.R.string as AppText
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val accountService: AccountService,
    logService: LogService
) : MainViewModel(logService) {
    // ViewModel logic
    var uiState = mutableStateOf(SignInState())
        private set

    private val email
        get() = uiState.value.email

    private val password
        get() = uiState.value.password

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onGoogleSignInClick(activity: Activity) {
        uiState.value = uiState.value.copy(isGoogleSignIn = true)
        launchCatching {
            accountService.authenticateWithGoogle(activity)
        }
    }

    fun handleGoogleSignInResult(data: Intent, openAndPopUp: (String, String) -> Unit) {
        launchCatching {
            accountService.handleGoogleSignInResult(data)
            openAndPopUp(Route.NotesScreen.route, Route.SignInScreen.route)
        }
    }

    fun onForgotPasswordClick() {
        if (!email?.isValidEmail()!!) {
            SnackbarManager.showMessage(AppText.email_error)
            return
        }

        launchCatching {
            accountService.sendRecoveryEmail(email!!)
            SnackbarManager.showMessage(AppText.recovery_email_sent)
        }
    }

    fun onSignInClick(openAndPopUp: (String, String) -> Unit) {
        Log.i("SignInViewModel", "onSignInClick")
        if (!email?.isValidEmail()!!) {
            SnackbarManager.showMessage(AppText.email_error)
            Log.i("SignInViewModel", "Email error")
            return
        }

        if (password?.isBlank()!!) {
            SnackbarManager.showMessage(AppText.empty_password_error)
            Log.i("SignInViewModel", "pass error")
            return
        }

        launchCatching {
            accountService.authenticate(email!!, password!!)
            openAndPopUp(Route.NotesScreen.route, Route.SignInScreen.route)
        }
    }

    suspend fun getGoogleSignInIntent(): Intent {
        return accountService.getGoogleSignInIntent()
    }
}
