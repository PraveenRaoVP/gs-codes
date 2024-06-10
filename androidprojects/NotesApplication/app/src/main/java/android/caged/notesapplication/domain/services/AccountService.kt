package android.caged.notesapplication.domain.services

import android.caged.notesapplication.domain.model.User
import android.content.Context
import kotlinx.coroutines.flow.Flow

interface AccountService {
    val currentUserId: String
    val currentUser: Flow<User>

    suspend fun authenticate(email: String, password: String)
    suspend fun sendRecoveryMail(email: String)
    suspend fun linkAccount(email: String, password: String)
    suspend fun signOut()
    suspend fun deleteAccount()
}