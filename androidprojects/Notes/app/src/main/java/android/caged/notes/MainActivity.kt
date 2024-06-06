package android.caged.notes

import android.caged.notes.domain.manager.LocalUserManager
import android.caged.notes.domain.services.AccountService
import android.caged.notes.presentation.navigation.NavGraph
import android.caged.notes.presentation.navigation.Route
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import android.caged.notes.ui.theme.NotesTheme
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var accountService: AccountService

    @Inject
    lateinit var localUserManager: LocalUserManager

    private val googleSignInLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        result.data?.let { data ->
            // Handle the sign-in result
            lifecycleScope.launch {
                accountService.handleGoogleSignInResult(data)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesTheme {
                val scope = rememberCoroutineScope()
                var startDestination by remember { mutableStateOf<String?>(null) }

                LaunchedEffect(Unit) {
                    scope.launch {
                        val user = accountService.currentUser.first()
                        val hasCompletedOnboarding = localUserManager.readAppEntry().first()

                        startDestination = if (user.id.isNotEmpty()) {
                            Route.PostSigningIn.route
                        } else {
                            if (hasCompletedOnboarding) {
                                Route.SignInScreen.route
                            } else {
                                Route.OnboardingScreen.route
                            }
                        }
                    }
                }

                if (startDestination != null) {
                    Surface(modifier = Modifier.fillMaxSize()) {
                        Scaffold(
                            snackbarHost = {
                                SnackbarHost(
                                    hostState = SnackbarHostState(),
                                    modifier = Modifier.padding(8.dp),
                                    snackbar = { snackbarData ->
                                        Snackbar(snackbarData, contentColor = MaterialTheme.colorScheme.onPrimary)
                                    }
                                )}) { innerPadding ->
                            NavGraph(startDestination = startDestination!!, modifier = Modifier.padding(innerPadding))
                        }
                    }
                }
            }
        }
    }

    private suspend fun startGoogleSignIn() {
        val signInIntent = accountService.getGoogleSignInIntent()
        googleSignInLauncher.launch(signInIntent)
    }

}