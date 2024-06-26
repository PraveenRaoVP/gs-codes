package android.caged.jogfit.presentation.components

import android.caged.jogfit.R
import android.caged.jogfit.presentation.navigation.Routes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.firebase.auth.FirebaseAuth

@Composable
fun DrawerContent(
    navController: NavController,
    onLogoutClick: () -> Unit,
    profilePictureUrl: String,
    profileName: String
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(0.7f)
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        // Profile Picture
        AsyncImage(model = ImageRequest.Builder(LocalContext.current)
            .data(profilePictureUrl)
            .crossfade(true)
            .build()
            ,
            placeholder = painterResource(id = R.drawable.account_circle_24px),
            contentDescription = null,
            modifier = Modifier.height(150.dp)
                .padding(top = 20.dp)
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(80.dp)
                ).clip(
                    RoundedCornerShape(80.dp)
                ),
             )

        Spacer(modifier = Modifier.height(16.dp))

        // Profile Name
        Text(
            text = profileName,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Profile Settings Button
        Button(
            onClick = { navController.navigate(Routes.SettingsScreen.route) },
            modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.background)
        ) {
            Text("Profile Settings")
        }

        Spacer(modifier = Modifier.weight(1f))

        // Logout Button
        Button(
            onClick = onLogoutClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.background
            ),
            elevation = ButtonDefaults.buttonElevation(1.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Logout", color = MaterialTheme.colorScheme.primary)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DrawerContentPreview() {
    DrawerContent(
        navController = NavController(LocalContext.current),
        onLogoutClick = { FirebaseAuth.getInstance().signOut() },
        profilePictureUrl = "https://www.example.com/profile_picture.jpg",
        profileName = "John Doe"
    )
}