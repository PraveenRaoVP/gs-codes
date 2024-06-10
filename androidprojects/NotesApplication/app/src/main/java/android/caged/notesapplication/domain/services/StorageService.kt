package android.caged.notesapplication.domain.services

import android.caged.notesapplication.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface StorageService {
    val notes: Flow<List<Note>>
    suspend fun save(note: Note): String
    suspend fun delete(noteId: String)
    suspend fun update(note: Note)
}