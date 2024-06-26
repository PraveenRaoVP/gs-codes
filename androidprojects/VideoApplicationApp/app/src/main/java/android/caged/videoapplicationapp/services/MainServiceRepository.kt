package android.caged.videoapplicationapp.services

import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import org.webrtc.SurfaceViewRenderer
import javax.inject.Inject

class MainServiceRepository @Inject constructor(
    private val context: Context
) {
    private fun startServiceIntent(intent: Intent) {
        Log.i("MainServiceRepository", "Starting service")
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intent)
        } else {
            context.startService(intent)
        }
    }

    fun startService(username: String) {
        Thread {
            val intent = Intent(context, MainService::class.java)
            intent.putExtra("username", username)
            intent.action = MainServiceEvents.START_SERVICE.name
            startServiceIntent(intent)

        }.start()
    }

    fun stopService() {
        val intent = Intent(context, MainService::class.java)
        context.stopService(intent)
    }

    fun setupViews(isVideoCall: Boolean, isCaller: Boolean, target: String) {
        MainService.remoteSurfaceView = SurfaceViewRenderer(context)
        MainService.localSurfaceView = SurfaceViewRenderer(context)
    }

    fun toggleAudio(isMicrophoneMuted: Boolean) {
        TODO("Not yet implemented")
    }

    fun toggleVideo(isCameraMuted: Boolean) {
        TODO("Not yet implemented")
    }

    fun toggleAudioDevice(audioMode: String) {
        TODO("Not yet implemented")
    }

    fun toggleScreenShare(screenShared: Boolean) {
        TODO("Not yet implemented")
    }


}