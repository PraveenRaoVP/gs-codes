package android.caged.videoapplicationapp.presentation.call.components

import android.view.SurfaceView
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import org.webrtc.EglBase
import org.webrtc.SurfaceViewRenderer

@Composable
fun LocalView(
    modifier: Modifier,
    localSurfaceView: SurfaceViewRenderer,
    eglContext: EglBase.Context?
) {
    AndroidView(
        factory = { context ->
            localSurfaceView.apply {
                setEnableHardwareScaler(true)
                init(eglContext, null)
            }
        },
        modifier = Modifier
            .padding(10.dp)
            .size(100.dp, 150.dp)
    )
}