package android.caged.notesapplication.presentation.note_detail

import android.caged.notesapplication.MainViewModel
import android.caged.notesapplication.domain.model.Note
import android.caged.notesapplication.domain.services.LogService
import android.caged.notesapplication.domain.services.StorageService
import android.caged.notesapplication.presentation.navigation.Routes
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteDetailViewModel @Inject constructor(
    private val storageService: StorageService,
    logService: LogService
) : ViewModel() {
    var uiState = mutableStateOf(NoteDetailUiState())
        private set

    private val id: String
        get() = uiState.value.id

    private val title: String
        get() = uiState.value.title

    private val content: String
        get() = uiState.value.content

    val isHidden: Boolean
        get() = uiState.value.isHidden

    fun onTitleChange(title: String) {
        uiState.value = uiState.value.copy(title = title)
    }

    fun onContentChange(content: String) {
        uiState.value = uiState.value.copy(content = content)
    }

    private fun onToggleVisibility() {
        uiState.value = uiState.value.copy(isHidden = !uiState.value.isHidden)
    }

    fun onEvent(event: NoteDetailEvent) {
        when(event) {
            is NoteDetailEvent.ToggleVisibility -> {
                onToggleVisibility()
            }
        }
    }

    fun onBackClicked(onNavigate: (String, String) -> Unit) {
        onNavigate(Routes.NotesRoute.route, Routes.EditNoteRoute.route)
    }

    fun onDeleteClicked(noteId: String, onDeleted: (String, String) -> Unit) {
        viewModelScope.launch {
            storageService.delete(noteId)
            onDeleted(Routes.NotesRoute.route, Routes.EditNoteRoute.route)
        }
    }

    fun onComplete(onNavigate: (String, String) -> Unit) {
        val note = Note(
            id = id,
            title = title,
            content = content,
            isHidden = isHidden
        )
        viewModelScope.launch {
            storageService.update(note)
            onNavigate(Routes.NotesRoute.route, Routes.EditNoteRoute.route)
        }
    }
}