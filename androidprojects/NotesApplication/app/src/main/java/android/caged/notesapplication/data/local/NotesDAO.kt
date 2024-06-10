package android.caged.notesapplication.data.local

import android.caged.notesapplication.domain.model.Note
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDAO {
    @Query("SELECT * FROM notes WHERE isHidden = 0")
    fun getNotes() : Flow<List<Note>>

    @Query("SELECT * FROM notes WHERE id = :noteId")
    fun getNoteById(noteId: Int) : Note?

    @Query("SELECT * FROM notes WHERE isHidden = 1")
    fun getHiddenNotes() : Flow<List<Note>>

    @Query("SELECT * FROM notes WHERE title LIKE :query OR content LIKE :query")
    fun searchNotes(query: String) : Flow<List<Note>>

    @Query("UPDATE notes SET isHidden = 1 WHERE id = :noteId")
    suspend fun hideNoteById(noteId: Int)

    @Query("SELECT COUNT(*) FROM notes WHERE isHidden = 0")
    fun getNotesCount() : Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNote(note: Note)

    // check if the note exists in the db
    @Query("SELECT EXISTS(SELECT 1 FROM notes WHERE id = :noteId)")
    fun noteExists(noteId: Int) : Boolean
}