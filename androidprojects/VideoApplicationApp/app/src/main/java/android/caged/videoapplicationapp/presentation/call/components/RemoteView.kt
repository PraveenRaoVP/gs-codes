package android.caged.videoapplicationapp.presentation.call.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import org.webrtc.EglBase
import org.webrtc.SurfaceViewRenderer

@Composable
fun RemoteView(
    modifier: Modifier,
    remoteSurfaceView: SurfaceViewRenderer,
    eglContext: EglBase.Context?
) {
    AndroidView(
        factory = { context ->
            remoteSurfaceView.apply {
                setEnableHardwareScaler(true)
                init(eglContext, null)
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}
