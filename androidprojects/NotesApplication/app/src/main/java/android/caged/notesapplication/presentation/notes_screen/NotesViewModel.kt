package android.caged.notesapplication.presentation.notes_screen

import android.caged.notesapplication.domain.services.AccountService
import android.caged.notesapplication.presentation.navigation.Routes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val accountService: AccountService
) : ViewModel() {
    fun signOut(navigateAndPopUp: (String, String) -> Unit) {
        viewModelScope.launch {
            accountService.signOut()
            navigateAndPopUp(Routes.SignInRoute.route, Routes.NotesRoute.route)
        }
    }
}