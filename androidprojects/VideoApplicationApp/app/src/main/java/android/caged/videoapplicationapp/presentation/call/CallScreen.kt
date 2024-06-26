package android.caged.videoapplicationapp.presentation.call

import android.app.Activity
import android.caged.videoapplicationapp.R
import android.caged.videoapplicationapp.presentation.call.components.LocalView
import android.caged.videoapplicationapp.presentation.call.components.RemoteView
import android.caged.videoapplicationapp.presentation.navigation.Routes
import android.caged.videoapplicationapp.services.MainService
import android.caged.videoapplicationapp.ui.theme.VideoApplicationAppTheme
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import org.webrtc.EglBase
import org.webrtc.SurfaceViewRenderer

@Composable
fun CallScreen(
    target: String,
    isVideoCall: Boolean,
    isCaller: Boolean,
    onNavigate: (String) -> Unit,
    viewModel: CallViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val eglContext: EglBase.Context = EglBase.create().eglBaseContext

    val remoteSurfaceView = MainService.remoteSurfaceView ?: SurfaceViewRenderer(context)
    val localSurfaceView = MainService.localSurfaceView ?: SurfaceViewRenderer(context)


    viewModel.initCall(target, isVideoCall, isCaller)

    val isMicrophoneMuted by viewModel.isMicrophoneMuted
    val isCameraMuted by viewModel.isCameraMuted
    val isSpeakerMode by viewModel.isSpeakerMode
    val isScreenCasting by viewModel.isScreenCasting
    val callTime by viewModel.callTime

    viewModel.requestScreenCaptureLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val intent = result.data
            MainService.screenPermissionIntent = intent
            viewModel.toggleScreenShare()
        }
    }


    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(text = "In call with $target", modifier = Modifier.padding(16.dp))
            Text(text = callTime.toString(), modifier = Modifier.padding(16.dp))

            if (isVideoCall) {
                RemoteView(modifier = Modifier.weight(1f), remoteSurfaceView, eglContext)
                LocalView(modifier = Modifier.weight(1f), localSurfaceView, eglContext)
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                IconButton(onClick = { viewModel.toggleMicrophone() }) {
                    Icon(
                        painter = painterResource(
                            if (isMicrophoneMuted)
                                R.drawable.mic_off_24px
                            else
                                R.drawable.mic_24px
                            ),
                        contentDescription = null
                    )
                }
                if (isVideoCall) {
                    IconButton(onClick = { viewModel.toggleCamera() }) {
                        Icon(
                            painter = painterResource(
                                if (isCameraMuted)
                                    R.drawable.videocam_off_24px
                                else
                                    R.drawable.videocam_24px
                            ),
                            contentDescription = null
                        )
                    }
                }
                IconButton(onClick = { viewModel.toggleAudioDevice() }) {
                    Icon(
                        painter = painterResource(
                            if (isSpeakerMode)
                                R.drawable.volume_up_24px
                            else
                                R.drawable.ic_ear
                        ),
                        contentDescription = null
                    )
                }
                if (isVideoCall) {
                    IconButton(onClick = {
                        if (!isScreenCasting) {
                            viewModel.startScreenCapture(context)
                        } else {
                            viewModel.toggleScreenShare()
                        }
                    }) {
                        Icon(
                            painter = painterResource(
                                if (isScreenCasting)
                                    R.drawable.stop_screen_share_24px
                                else
                                    R.drawable.mobile_screen_share_24px
                            ),
                            contentDescription = null
                        )
                    }
                }
                IconButton(onClick = {
                    viewModel.onCallEnded()
                    onNavigate(Routes.HomeRoute.route)
                }) {
                    Icon(
                        painter = painterResource(R.drawable.call_end_24px),
                        contentDescription = null
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CallScreenPreview() {
    VideoApplicationAppTheme {
        CallScreen(
            target = "John Doe",
            isVideoCall = true,
            isCaller = true,
            onNavigate = { }
        )
    }
}