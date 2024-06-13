package android.caged.videoapplicationapp.presentation.home.components

import android.caged.videoapplicationapp.utils.Status
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun UserItem(
    user: Pair<String, String>,
    onVideoCallClicked: (Pair<String, String>) -> Unit,
    onAudioCallClicked: (Pair<String, String>) -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = RoundedCornerShape(20.dp)
                ).padding(16.dp)
        ) {
            Text(text = user.first)
            Spacer(modifier = Modifier.weight(1f))
            if(user.second == Status.ONLINE.toString()) {
                Button(onClick = { onVideoCallClicked(user) }) {
                    Text("Video Call")
                }
                Button(onClick = { onAudioCallClicked(user) }) {
                    Text("Audio Call")
                }
            }
            Text(text = user.second)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserItemPreview() {
    UserItem(user = Pair("Papa", "ONLINE"),
        onVideoCallClicked = {},
        onAudioCallClicked = {})
}
