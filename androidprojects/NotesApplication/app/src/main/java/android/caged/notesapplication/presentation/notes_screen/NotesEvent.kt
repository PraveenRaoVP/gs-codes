package android.caged.notesapplication.presentation.notes_screen

sealed class NotesEvent {
    data object SearchNotes : NotesEvent()
    data object EmptySearch : NotesEvent()
}