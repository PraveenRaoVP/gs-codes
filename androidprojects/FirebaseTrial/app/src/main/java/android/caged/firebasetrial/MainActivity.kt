package android.caged.firebasetrial

import androidx.credentials.CredentialManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        auth = Firebase.auth

        setContent {
            val startDestination = if(auth.currentUser == null) {
                Screens.Login.name
            } else {
                Screens.Home.name
            }


            NavGraph(startDestination = startDestination, auth = auth)
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }

}
