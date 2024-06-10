package android.caged.notesapplication.data.services

import android.caged.notesapplication.domain.model.User
import android.caged.notesapplication.domain.services.AccountService
import android.content.Context
import androidx.credentials.GetCredentialRequest
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.material3.Snackbar
import androidx.compose.ui.platform.LocalContext
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import android.caged.notesapplication.R.string as AppText

class AccountServiceImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val scope: CoroutineScope,
    private val credentialManager: CredentialManager
) : AccountService {

    override val currentUserId: String
        get() = auth.currentUser?.uid.orEmpty()

    override val currentUser: Flow<User>
        get() = callbackFlow {
            val listener = FirebaseAuth.AuthStateListener { auth ->
                this.trySend(
                    auth.currentUser?.let { User(it.uid, it.displayName, it.email) } ?: User()
                )
            }
            auth.addAuthStateListener(listener)
            awaitClose { auth.removeAuthStateListener(listener) }
        }

    override suspend fun authenticate(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).await()
    }

    override suspend fun sendRecoveryMail(email: String) {
        auth.sendPasswordResetEmail(email).await()
    }

    override suspend fun linkAccount(email: String, password: String) {
        Log.i("AccountServiceImpl","linkAccount")
        trace(LINK_ACCOUNT_TRACE) {
            auth.createUserWithEmailAndPassword(email, password).await()
            val credential = EmailAuthProvider.getCredential(email, password)
            auth.currentUser!!.linkWithCredential(credential).await()
        }
    }

    override suspend fun signOut() {
        auth.signOut()
        scope.launch {
            credentialManager.clearCredentialState(
                ClearCredentialStateRequest()
            )
        }
    }

    override suspend fun deleteAccount() {
        TODO("Not yet implemented")
    }

    companion object {
        const val LINK_ACCOUNT_TRACE = "linkAccount"
    }
}