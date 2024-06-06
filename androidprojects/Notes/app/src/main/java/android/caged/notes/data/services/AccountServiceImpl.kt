package android.caged.notes.data.services

import android.app.Activity
import android.caged.notes.R
import android.caged.notes.domain.model.User
import android.caged.notes.domain.services.AccountService
import android.caged.notes.utils.Constants.GOOGLE_WEB_CLIENT_ID
import android.content.Intent
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AccountServiceImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val googleSignInClient: GoogleSignInClient
) : AccountService {

    override val currentUserID: String
        get() = auth.currentUser?.uid.orEmpty()

    override val currentUser: Flow<User>
        get() = callbackFlow {
            val listener = FirebaseAuth.AuthStateListener { auth ->
                this.trySend(auth.currentUser?.let { User(it.uid) } ?: User())
            }
            auth.addAuthStateListener(listener)
            awaitClose { auth.removeAuthStateListener(listener) }
        }

    override suspend fun authenticate(email: String, password: String) {
        Log.i("AccountServiceImpl", "authenticate: $email, $password")
        auth.signInWithEmailAndPassword(email, password).await()
    }

    override suspend fun authenticateWithGoogle(activity: Activity) {
        val signInIntent = googleSignInClient.signInIntent
        // Handle the sign-in in your Activity using startActivityForResult or a similar method.
        activity.startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override suspend fun handleGoogleSignInResult(data: Intent) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
        val account = task.getResult(ApiException::class.java)!!
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(credential).await()
    }

    override suspend fun sendRecoveryEmail(email: String) {
        try {
            auth.sendPasswordResetEmail(email).await()
        } catch(e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun linkAccount(email: String, password: String) {
        trace(LINK_ACCOUNT_TRACE) {
            Log.i("AccountServiceImpl", "linkAccount: $email, $password")
            try {
                val credential = EmailAuthProvider.getCredential(email, password)
                auth.currentUser!!.linkWithCredential(credential).await()
                Log.i("AccountServiceImpl", auth.currentUser!!.uid)

            } catch(e: Exception) {
                Log.e("AccountServiceImpl", e.message.toString())
            }
        }
    }

    override suspend fun deleteAccount() {
        auth.currentUser!!.delete().await()
    }

    override suspend fun signOut() {
        auth.signOut()
    }

    override suspend fun getGoogleSignInIntent(): Intent {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(GOOGLE_WEB_CLIENT_ID)
            .requestEmail()
            .build()
        return googleSignInClient.signInIntent
    }

    companion object {
        private const val LINK_ACCOUNT_TRACE = "linkAccount"
        const val RC_SIGN_IN = 9001
    }
}