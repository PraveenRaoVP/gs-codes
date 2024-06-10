package android.caged.notesapplication.data.services

import android.caged.notesapplication.domain.model.Note
import android.caged.notesapplication.domain.services.AccountService
import android.caged.notesapplication.domain.services.StorageService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.dataObjects
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await

class StorageServiceImpl(
    private val firestore: FirebaseFirestore,
    private val auth: AccountService
) : StorageService {
    @OptIn(ExperimentalCoroutinesApi::class)
    override val notes: Flow<List<Note>>
        get() = auth.currentUser.flatMapLatest {
            firestore.collection("notes").whereEqualTo("userId", it.id).dataObjects()
        }

    override suspend fun save(note: Note): String =
        trace("saveNote") {
            val noteWithUserId = note.copy(userId = auth.currentUserId)
            firestore.collection("notes").add(noteWithUserId).await().id
        }

    override suspend fun delete(noteId: String) {
        firestore.collection("notes").document(noteId).delete().await()
    }

    override suspend fun update(note: Note) {
        trace("updateNote") {
            firestore.collection("notes").document(note.id).set(note).await()
        }
    }
}