package android.caged.notesapplication.presentation.note_detail

import androidx.compose.runtime.MutableState

data class NoteDetailUiState(
    var id: String = "",
    val title: String = "",
    val content: String = "",
    val isHidden: Boolean = false
)
