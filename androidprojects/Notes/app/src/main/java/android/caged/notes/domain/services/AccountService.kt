package android.caged.notes.domain.services

import android.caged.notes.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AccountService {
    val currentUserID: String
    val currentUser: Flow<User>

    suspend fun authenticate(email: String, password: String)
    suspend fun sendRecoveryEmail(email: String)
    suspend fun linkAccount(email: String, password: String)
    suspend fun deleteAccount()
    suspend fun signOut()
    suspend fun authenticateWithGoogle()
}