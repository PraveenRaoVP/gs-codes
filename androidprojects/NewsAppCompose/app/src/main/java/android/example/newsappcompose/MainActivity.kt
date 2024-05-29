package android.example.newsappcompose

import android.example.newsappcompose.presentation.navgraph.NavGraph
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import android.example.newsappcompose.ui.theme.NewsAppComposeTheme
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val viewModel by viewModels<MainViewModel>()
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        installSplashScreen().apply {
            setKeepOnScreenCondition { viewModel.splashCondition}
        }
        enableEdgeToEdge()
        setContent {
            val isSystemInDarkModeTheme = isSystemInDarkTheme()
            val systemController = rememberSystemUiController()

            SideEffect {
                systemController.setSystemBarsColor(
                    color = Color.Transparent,
                    darkIcons = !isSystemInDarkModeTheme
                )
            }

            NewsAppComposeTheme {
                val startDestination = viewModel.startDestination
                NavGraph(startDestination = startDestination)
            }
        }
    }
}