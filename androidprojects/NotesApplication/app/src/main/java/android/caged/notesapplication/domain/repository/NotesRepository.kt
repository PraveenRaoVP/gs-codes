package android.caged.notesapplication.domain.repository

import android.caged.notesapplication.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    suspend fun getNotes(): Flow<List<Note>> // get from db


    fun getNoteById(noteId: Int) : Note? // from db

    fun getHiddenNotes() : Flow<List<Note>> // from db

    fun searchNotes(query: String) : Flow<List<Note>> // from db

    fun getNotesCount() : Int // from db

    suspend fun hideNoteById(noteId: Int) // update the property in both db and firestore

    suspend fun addNote(note: Note) // insert into db and firestore

    suspend fun deleteNote(note: Note) // delete from both db and firestore

    suspend fun updateNote(note: Note) // update in both db and firestore

    suspend fun getNotesFromFirestore() : Flow<List<Note>> // sync with firestore and db

    fun checkIfNoteExists(noteId: Int) : Boolean // check if the note exists in the db

}