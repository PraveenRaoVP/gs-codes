package android.caged.notesapplication.presentation.sign_up

import android.caged.notesapplication.MainViewModel
import android.caged.notesapplication.data.services.AccountServiceImpl
import android.caged.notesapplication.data.services.trace
import android.caged.notesapplication.domain.services.AccountService
import android.caged.notesapplication.domain.services.LogService
import android.caged.notesapplication.ext.isValidEmail
import android.caged.notesapplication.ext.isValidPassword
import android.caged.notesapplication.ext.passwordMatches
import android.caged.notesapplication.presentation.navigation.Routes
import android.caged.notesapplication.presentation.snackbar.SnackbarManager
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import android.caged.notesapplication.R.string as AppText

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val accountService: AccountService,
    private val logService: LogService,
    private val auth: FirebaseAuth
) : MainViewModel(logService) {
    var uiState = mutableStateOf(SignUpUiState())
        private set

    private val email
        get() = uiState.value.email

    private val password
        get() = uiState.value.password

    private val repeatPassword
        get() = uiState.value.repeatPassword

    fun onEmailChange(email: String) {
        uiState.value = uiState.value.copy(email = email)
    }

    fun onPasswordChange(password: String) {
        uiState.value = uiState.value.copy(password = password)
    }

    fun onRepeatPasswordChange(repeatPassword: String) {
        uiState.value = uiState.value.copy(repeatPassword = repeatPassword)
    }

    fun onSignUpClick(navigateAndPopup: (String, String) -> Unit) {
        if(!email.isValidEmail()) {
            SnackbarManager.showMessage(AppText.email_error)
            return
        }
        if(!password.isValidPassword()) {
            SnackbarManager.showMessage(AppText.password_error)
            return
        }
        if(!password.passwordMatches(repeatPassword)) {
            SnackbarManager.showMessage(AppText.password_match_error)
            return
        }

        launchCatching {
            try {
                Log.i("SignUpViewModel","onSignUpClicked")
//                accountService.linkAccount(email, password)
                trace(AccountServiceImpl.LINK_ACCOUNT_TRACE) {
                    auth.createUserWithEmailAndPassword(email, password).await()
//                    val credential = EmailAuthProvider.getCredential(email, password)
//                    auth.currentUser!!.linkWithCredential(credential).await()
                }
                SnackbarManager.showMessage(AppText.sign_up_success)
                navigateAndPopup(Routes.SignInRoute.route, Routes.SignUpRoute.route)
            } catch (e: Exception) {
                SnackbarManager.showMessage(AppText.email_already_used)
            }
        }
    }
}