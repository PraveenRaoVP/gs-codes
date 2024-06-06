package android.caged.notes.presentation.sign_up

import android.caged.notes.MainViewModel
import android.caged.notes.data.ext.isValidEmail
import android.caged.notes.data.ext.isValidPassword
import android.caged.notes.data.ext.passwordMatches
import android.caged.notes.data.snackbar.SnackbarManager
import android.caged.notes.domain.services.AccountService
import android.caged.notes.domain.services.LogService
import android.caged.notes.presentation.navigation.Route
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import android.caged.notes.R.string as AppText

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val accountService: AccountService,
    logService: LogService
) : MainViewModel(logService) {
    var uiState = mutableStateOf(SignUpState())
        private set

    private val email
        get() = uiState.value.email

    private val password
        get() = uiState.value.password

    private val repeatPassword
        get() = uiState.value.repeatPassword

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onRepeatPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(repeatPassword = newValue)
    }

    fun onSignUpClick(openAndPopUp: (String, String) -> Unit) {
        if (!email.isValidEmail()) {
            SnackbarManager.showMessage(AppText.email_error)
            return
        }

        if (!password.isValidPassword()) {
            SnackbarManager.showMessage(AppText.password_error)
            return
        }

        if (!password.passwordMatches(repeatPassword)) {
            SnackbarManager.showMessage(AppText.password_match_error)
            return
        }

        Log.i("SignUpViewModel","${accountService.currentUser}")
        launchCatching {
            accountService.linkAccount(email, password)
            openAndPopUp(Route.SignInScreen.route, Route.SignUpScreen.route)
        }
    }
}