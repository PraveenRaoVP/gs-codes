package android.caged.videoapplicationapp

import android.caged.videoapplicationapp.presentation.navigation.NavGraph
import android.caged.videoapplicationapp.presentation.navigation.Routes
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
import android.caged.videoapplicationapp.ui.theme.VideoApplicationAppTheme
import androidx.compose.material3.Surface
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VideoApplicationAppTheme {
                Surface {
                    NavGraph(startDestination = Routes.LoginRoute.route)
                }
            }
        }
    }
}
