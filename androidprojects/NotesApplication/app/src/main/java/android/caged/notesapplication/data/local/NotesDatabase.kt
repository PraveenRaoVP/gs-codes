package android.caged.notesapplication.data.local

import android.caged.notesapplication.domain.model.Note
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun notesDao() : NotesDAO
}