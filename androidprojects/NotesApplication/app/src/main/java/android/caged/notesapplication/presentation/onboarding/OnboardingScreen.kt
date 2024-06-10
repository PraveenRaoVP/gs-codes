package android.caged.notesapplication.presentation.onboarding

import android.caged.notesapplication.R
import android.caged.notesapplication.presentation.navigation.Routes
import android.caged.notesapplication.ui.theme.NotesApplicationTheme
import android.content.res.Configuration
import android.media.metrics.Event
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    event: (OnboardingEvent) -> Unit,
    navigateTo: (String) -> Unit
) {

    val scope = rememberCoroutineScope()

    Surface(modifier = modifier
        .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.onboarding),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.6f)
            )
            Text(
                text = "Welcome to Notes",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = "This is a simple note taking app. Get started by creating your first note!",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(16.dp)
            )
            Button(onClick = {
                scope.launch {
                    event(OnboardingEvent.SaveAppEntry)
                    navigateTo(Routes.SignInRoute.route)
                }
            },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 50.dp)) {
                Text("Get Started")
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    NotesApplicationTheme {
        OnboardingScreen(
            event = {} ,
            navigateTo = {}
        )
    }
}