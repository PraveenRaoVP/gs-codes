package android.caged.notesapplication.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp

@Entity(tableName = "notes")
data class Note(
    @DocumentId @PrimaryKey val id: String = "",
    @ServerTimestamp val createdAt: String? = "",
    @ServerTimestamp val updatedAt: String? = "",
    val title: String = "",
    val content: String = "",
    val isHidden: Boolean = false,
    val userId: String = ""
)
