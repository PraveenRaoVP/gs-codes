package android.example.todo_application.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="note_table")
data class Note (
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name="title")
    val title: String = "",

    @ColumnInfo(name = "description")
    val content: String = ""
)