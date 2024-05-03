package android.example.todo_application.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDatabaseDao {
    @Insert
    fun insert(note: Note)

    @Update
    fun update(note: Note)

    @Query("SELECT * from note_table WHERE id = :key")
    fun get(key: Long): Note?

    @Query("DELETE FROM note_table")
    fun clear()

    @Query("SELECT * FROM note_table ORDER BY id DESC")
    fun getAllNotes(): List<Note>

    @Query("DELETE FROM note_table WHERE id = :key")
    fun delete(key: Long)
}