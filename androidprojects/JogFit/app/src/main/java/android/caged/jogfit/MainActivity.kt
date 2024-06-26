package android.caged.jogfit

import android.caged.jogfit.presentation.navigation.NavGraph
import android.caged.jogfit.presentation.navigation.Routes
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import android.caged.jogfit.ui.theme.JogFitTheme
import android.caged.jogfit.utils.LocalThemeManager
import android.caged.jogfit.utils.SharedPreferenceUtil
import android.caged.jogfit.utils.ThemeManager
import android.caged.jogfit.utils.ThemePreferenceManager
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.content.SharedPreferences
import android.location.Location
import android.os.IBinder
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.collectAsState
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        auth = Firebase.auth


        ThemePreferenceManager.init(this)

        setContent {
            val startDestination = if (auth.currentUser == null) {
                Routes.AppStartRoute.route
            } else {
                Routes.AppNavigatorRoute.route
            }

            val isDarkTheme = ThemePreferenceManager.isDarkTheme.value

            JogFitTheme(
                darkTheme = isDarkTheme
            ) {
                Scaffold() { innerPadding ->
                    NavGraph(
                        startDestination = startDestination,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
