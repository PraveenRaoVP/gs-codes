package android.caged.jogfit.presentation.appnavigator

import android.caged.jogfit.presentation.navigation.Routes
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppNavigatorViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    private val credentialManager: CredentialManager
) : ViewModel() {
    val currentUser = auth.currentUser
    val profileImageUrl = currentUser?.photoUrl
    val displayName = currentUser?.displayName

    val previousScreen = mutableStateOf(Routes.HomeRoute.route)

    val snackbarHostState = SnackbarHostState()

    fun signOut(navigatePopUp: (String, String) -> Unit) {
        viewModelScope.launch {
            auth.signOut()
            credentialManager.clearCredentialState(
                ClearCredentialStateRequest()
            )
            navigatePopUp(Routes.AppStartRoute.route, Routes.AppNavigationScreen.route)
        }
    }
}