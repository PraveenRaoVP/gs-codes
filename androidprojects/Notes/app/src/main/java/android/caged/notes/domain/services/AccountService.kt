package android.caged.notes.domain.services

import android.app.Activity
import android.caged.notes.domain.model.User
import android.content.Intent
import kotlinx.coroutines.flow.Flow

interface AccountService {
    val currentUserID: String
    val currentUser: Flow<User>

    suspend fun authenticate(email: String, password: String)
    suspend fun sendRecoveryEmail(email: String)
    suspend fun linkAccount(email: String, password: String)
    suspend fun deleteAccount()
    suspend fun signOut()
    suspend fun handleGoogleSignInResult(data: Intent)
    suspend fun getGoogleSignInIntent(): Intent
    suspend fun authenticateWithGoogle(activity: Activity)
}