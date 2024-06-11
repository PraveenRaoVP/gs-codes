package android.caged.notesapplication.presentation.notes_screen

import android.caged.notesapplication.domain.model.Note
import androidx.room.Query

data class NotesUiState(
    val notes: List<Note> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = "",
    val searchQuery: String = ""
)
