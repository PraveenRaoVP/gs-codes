package android.caged.videoapplicationapp.presentation.home

import android.Manifest
import android.caged.videoapplicationapp.presentation.home.components.ActionBar
import android.caged.videoapplicationapp.presentation.home.components.UserItem
import android.caged.videoapplicationapp.presentation.navigation.Routes
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreen(
    username: String,
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigate: (String, String) -> Unit
) {
    viewModel.username = username

    val uiState by viewModel.uiState

    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            viewModel.notifyTarget(true)
            onNavigate(Routes.VideoDetailRoute.route+"/true/${viewModel.currentUser}/true", Routes.HomeRoute.route)
        } else {
            // Handle permission denial
//            Toast.makeText(LocalContext.current, "Camera permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    val recordAudioPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            viewModel.notifyTarget(false)
            onNavigate(Routes.VideoDetailRoute.route+"/false/${viewModel.currentUser}/true", Routes.HomeRoute.route)
        } else {
            // Handle permission denial
//            Toast.makeText(LocalContext.current, "Audio permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            ActionBar(
                title = "Welcome, $username",
                onLogoutClick = { viewModel.logout(onNavigate) }
            )
            if (uiState.isCallReceived && uiState.callReceiver == username) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column {
                        Text(uiState.callReceiveText!!)
                        Row {
                            Button(onClick = {
                                viewModel.acceptCall(onNavigate)
                                onNavigate(Routes.VideoDetailRoute.route+"/true/${viewModel.currentUser}/false", Routes.HomeRoute.route)
                            }) {
                                Text("Accept")
                            }
                            Button(onClick = {
                                viewModel.rejectCall()
                            }) {
                                Text("Reject")
                            }
                        }
                    }
                }
            }
            LazyColumn {
                items(viewModel.usersList.value) { user ->
                    UserItem(
                        user = user,
                        onVideoCallClicked = {
//                            if (user.second == "online") {
                                viewModel.prepareForVideoCall(user)
                                cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
//                            } else {
//                                Toast.makeText(LocalContext.current, "User is offline", Toast.LENGTH_SHORT).show()
//                            }
                        },
                        onAudioCallClicked = {
//                            if (user.second == "online") {
                                viewModel.prepareForAudioCall(user)
                                recordAudioPermissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
//                            } else {
//                                Toast.makeText(LocalContext.current, "User is offline", Toast.LENGTH_SHORT).show()
//                            }
                        }
                    )
                }
            }
        }
    }
}