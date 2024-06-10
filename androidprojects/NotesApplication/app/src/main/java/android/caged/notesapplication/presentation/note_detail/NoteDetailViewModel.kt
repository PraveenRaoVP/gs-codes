package android.caged.notesapplication.presentation.note_detail

import android.caged.notesapplication.domain.model.Note
import android.caged.notesapplication.domain.services.StorageService
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteDetailViewModel @Inject constructor(
    private val storageService: StorageService
) : ViewModel() {
    var uiState = mutableStateOf(NoteDetailUiState())
        private set

    private val title
        get() = uiState.value.title

    private val content
        get() = uiState.value.content

    private var isHidden
        get() = uiState.value.isHidden
        set(value) {
            uiState.value = uiState.value.copy(isHidden = value)
        }

    fun onTitleChange(newValue: String) {
        uiState.value = uiState.value.copy(title = newValue)
    }

    fun onContentChange(newValue: String) {
        uiState.value = uiState.value.copy(content = newValue)
    }

    fun onToggleVisibility() {
        isHidden = !isHidden
    }

    fun onComplete() {
        viewModelScope.launch {
            storageService.save(
                Note(
                    title = title,
                    content = content,
                    isHidden = isHidden
                )
            )
        }
    }

}