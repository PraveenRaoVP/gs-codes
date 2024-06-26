package android.caged.jogfit.presentation.profilepage

import android.caged.jogfit.presentation.navigation.Routes
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun ProfilePageScreen(
    profileImageUrl: String,
    displayName: String,
    email: String,
    navigateTo: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(70.dp))
        AsyncImage(
            model = ImageRequest
                .Builder(LocalContext.current)
                .data(profileImageUrl)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .size(150.dp, 150.dp)
                .clip(
                    RoundedCornerShape(80.dp)
                )
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(80.dp)
                )
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(text = displayName, style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(30.dp))

        Text(text = email, style = MaterialTheme.typography.titleSmall)

        Spacer(modifier = Modifier.height(30.dp))

        Button(onClick = { navigateTo(Routes.ProfileSettings.route) }) {
            Text("Edit Profile")
        }
    }
}

@Preview
@Composable
fun ProfilePageScreenPreview() {
    ProfilePageScreen(
        profileImageUrl = "https://example.com/image.jpg",
        displayName = "John Doe",
        email = "johndoe@gmail.com",
        navigateTo = {}
    )
}