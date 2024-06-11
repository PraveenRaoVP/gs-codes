package android.caged.notesapplication.presentation.notes_screen

import android.caged.notesapplication.MainViewModel
import android.caged.notesapplication.domain.services.AccountService
import android.caged.notesapplication.domain.services.LogService
import android.caged.notesapplication.presentation.navigation.Routes
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val accountService: AccountService,
    logService: LogService
) : MainViewModel(logService) {

    var uiState = mutableStateOf(NotesUiState())
        private set

    private val notes
        get() = uiState.value.notes

    private val isLoading
        get() = uiState.value.isLoading

    private val error
        get() = uiState.value.error

    private val searchQuery
        get() = uiState.value.searchQuery

    fun onEvent(event : NotesEvent) {

    }

    fun signOut(navigateAndPopUp: (String, String) -> Unit) {
        viewModelScope.launch {
            accountService.signOut()
            navigateAndPopUp(Routes.SignInRoute.route, Routes.NotesRoute.route)
        }
    }

    fun onSearchQueryChange(query: String) {
        uiState.value = uiState.value.copy(searchQuery = query)
    }
}