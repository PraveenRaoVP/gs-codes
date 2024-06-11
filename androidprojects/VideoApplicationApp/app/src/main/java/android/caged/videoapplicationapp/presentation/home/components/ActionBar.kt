package android.caged.videoapplicationapp.presentation.home.components

import android.caged.videoapplicationapp.ui.theme.VideoApplicationAppTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActionBar(
    title: String,
    onLogoutClick: () -> Unit
) {
    TopAppBar(title = { Text(title) },
            actions = {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Spacer(modifier = Modifier.weight(1f))
                        Button(onClick = onLogoutClick) {
                            Text("Logout")
                        }
                    }
                }
            }
        )
}

@Preview
@Composable
fun ActionBarPreview() {
    VideoApplicationAppTheme {
        ActionBar(title = "Title", onLogoutClick = {})
    }
}