package android.caged.notesapplication.domain.model

import androidx.room.PrimaryKey
import com.google.firebase.firestore.DocumentId

data class User(
    val id: String = "",
    val displayName: String? = "",
    val email: String? = ""
)
