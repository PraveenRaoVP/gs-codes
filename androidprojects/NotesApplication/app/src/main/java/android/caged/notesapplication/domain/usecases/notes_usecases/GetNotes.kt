package android.caged.notesapplication.domain.usecases.notes_usecases

import android.caged.notesapplication.domain.repository.NotesRepository

class GetNotes(
    private val notesRepository: NotesRepository
) {
    suspend operator fun invoke() = notesRepository.getNotes()
}