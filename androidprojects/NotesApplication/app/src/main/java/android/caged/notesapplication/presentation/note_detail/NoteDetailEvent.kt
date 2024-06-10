package android.caged.notesapplication.presentation.note_detail

sealed class NoteDetailEvent {
    data object ToggleVisibility : NoteDetailEvent()
}