package android.caged.notes.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

@Entity(tableName = "notes")
data class Note(
    @DocumentId @PrimaryKey val id: String = "",
    @ServerTimestamp val createdAt: Date = Date(),
    val title: String = "",
    val content: String = ""
)
