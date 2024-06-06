package android.caged.notes.presentation.onboarding

import android.caged.notes.domain.services.AccountService
import android.caged.notes.domain.usecases.appentry.AppEntryUsecases
import android.caged.notes.presentation.navigation.Route
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val appEntryUsecases: AppEntryUsecases,
    private val accountService: AccountService
) : ViewModel() {
    fun onEvent(event: OnboardingEvent, navigate: (String) -> Unit) {
        when(event) {
            OnboardingEvent.SaveAppEntry -> {
                saveAppEntry(navigate)
            }
        }
    }
    private fun saveAppEntry(navigate: (String) -> Unit) {
        viewModelScope.launch {
            appEntryUsecases.saveAppEntry()
            checkAuthState(navigate)
        }
    }
    private fun checkAuthState(navigate: (String) -> Unit) {
        viewModelScope.launch {
            val user = accountService.currentUser.first()
            if (user.id.isNotEmpty()) {
                navigate(Route.NotesScreen.route)
            } else {
                navigate(Route.SignInScreen.route)
            }
        }
    }
}
