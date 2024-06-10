package android.caged.notesapplication

import android.caged.notesapplication.domain.usecases.AppEntryUseCases
import android.caged.notesapplication.presentation.navigation.NavGraph
import android.caged.notesapplication.presentation.navigation.Routes
import android.caged.notesapplication.presentation.snackbar.SnackbarManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import android.caged.notesapplication.ui.theme.NotesApplicationTheme
import android.content.res.Resources
import android.util.Log
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

@AndroidEntryPoint
@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        auth = Firebase.auth
        setContent {
            val startDestination = if(auth.currentUser == null) {
                // if the user is not signed in, navigate to the onboarding screen
                Routes.OnboardingRoute.route
            } else {
                Routes.NotesRoute.route                
            }

            val appState = rememberAppState()

            NotesApplicationTheme {
                Scaffold(
                    snackbarHost = {
                        SnackbarHost(
                            hostState = appState.scaffoldState.snackbarHostState,
                            snackbar = { data ->
                                Snackbar(
                                    snackbarData = data,
                                    contentColor = MaterialTheme.colorScheme.onPrimary
                                )
                            }
                        )
                    }
                ) { innerPadding ->
                    Log.i("MainActivity", "$innerPadding")
                    NavGraph(startDestination = startDestination)
                }
            }
        }
    }

    @Composable
    @ReadOnlyComposable
    fun resources(): Resources {
        LocalConfiguration.current
        return LocalContext.current.resources
    }

    @Composable
    fun rememberAppState(
        scaffoldState: BottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
        snackbarManager: SnackbarManager = SnackbarManager,
        resources: Resources = resources(),
        coroutineScope: CoroutineScope = rememberCoroutineScope()
    ) =
        remember(scaffoldState, snackbarManager, resources, coroutineScope) {
            NotesApplicationState(scaffoldState, snackbarManager, resources, coroutineScope)
        }
}
