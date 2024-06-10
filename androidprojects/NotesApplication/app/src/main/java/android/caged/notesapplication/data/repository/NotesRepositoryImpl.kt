package android.caged.notesapplication.data.repository

import android.caged.notesapplication.data.local.NotesDAO
import android.caged.notesapplication.domain.model.Note
import android.caged.notesapplication.domain.repository.NotesRepository
import android.caged.notesapplication.domain.services.StorageService
import kotlinx.coroutines.flow.Flow

class NotesRepositoryImpl(
    private val notesDAO: NotesDAO,
    private val storageService: StorageService
) : NotesRepository {
    override suspend fun getNotes(): Flow<List<Note>> {
        return notesDAO.getNotes()
    }

    override fun getNoteById(noteId: Int): Note? {
        return notesDAO.getNoteById(noteId)
    }

    override fun getHiddenNotes(): Flow<List<Note>> {
        return notesDAO.getHiddenNotes()
    }

    override fun searchNotes(query: String): Flow<List<Note>> {
        return notesDAO.searchNotes(query)
    }

    override suspend fun hideNoteById(noteId: Int) {
        notesDAO.hideNoteById(noteId)
    }

    override fun getNotesCount(): Int {
        return notesDAO.getNotesCount()
    }

    override suspend fun addNote(note: Note) {
        notesDAO.addNote(note)
        storageService.save(note)
    }

    override suspend fun deleteNote(note: Note) {
        notesDAO.deleteNote(note)
        storageService.delete(note.id)
    }

    override suspend fun updateNote(note: Note) {
        storageService.update(note)
        notesDAO.updateNote(note)
    }

    override suspend fun getNotesFromFirestore(): Flow<List<Note>> {
        return storageService.notes
    }

    override fun checkIfNoteExists(noteId: Int): Boolean {
        return notesDAO.noteExists(noteId)
    }
}