package android.caged.firebaseauthtrial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import android.caged.firebaseauthtrial.ui.theme.FirebaseAuthTrialTheme
import androidx.navigation.NavGraph
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