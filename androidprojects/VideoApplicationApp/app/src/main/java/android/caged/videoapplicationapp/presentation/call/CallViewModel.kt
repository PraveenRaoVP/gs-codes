package android.caged.videoapplicationapp.presentation.call

import android.caged.videoapplicationapp.services.MainService
import android.caged.videoapplicationapp.services.MainServiceRepository
import android.caged.videoapplicationapp.webrtc.RTCAudioManager
import android.content.Context
import android.content.Intent
import android.media.projection.MediaProjectionManager
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CallViewModel @Inject constructor(
    private val serviceRepository: MainServiceRepository
) : ViewModel(), MainService.EndCallListener {

    var target: String? = null
    var isVideoCall = mutableStateOf(true)
    var isCaller = mutableStateOf(true)
    var isMicrophoneMuted = mutableStateOf(false)
    var isCameraMuted = mutableStateOf(false)
    var isSpeakerMode = mutableStateOf(true)
    var isScreenCasting = mutableStateOf(false)
    var callTime = mutableStateOf("00:00")

    lateinit var requestScreenCaptureLauncher: ActivityResultLauncher<Intent>

    init {
        MainService.endCallListener = this
    }

    fun onStart() {
        // TODO: Initialize screen capture launcher
    }

    fun initCall(target: String, isVideoCall: Boolean, isCaller: Boolean) {
        this.target = target
        this.isVideoCall.value = isVideoCall
        this.isCaller.value = isCaller
        startCallTimer()
        setupCall()
    }

    private fun setupCall() {
        serviceRepository.setupViews(isVideoCall.value, isCaller.value, target!!)
    }

    private fun startCallTimer() {
        CoroutineScope(Dispatchers.IO).launch {
            for (i in 0..3600) {
                delay(1000)
                val minutes = i / 60
                val seconds = i % 60
                callTime.value = String.format("%02d:%02d", minutes, seconds)
            }
        }
    }

    fun toggleMicrophone() {
        isMicrophoneMuted.value = !isMicrophoneMuted.value
        serviceRepository.toggleAudio(isMicrophoneMuted.value)
    }

    fun toggleCamera() {
        isCameraMuted.value = !isCameraMuted.value
        serviceRepository.toggleVideo(isCameraMuted.value)
    }

    fun toggleAudioDevice() {
        if (isSpeakerMode.value) {
            serviceRepository.toggleAudioDevice(RTCAudioManager.AudioDevice.EARPIECE.name)
        } else {
            serviceRepository.toggleAudioDevice(RTCAudioManager.AudioDevice.SPEAKER_PHONE.name)
        }
        isSpeakerMode.value = !isSpeakerMode.value
    }

    fun startScreenCapture(context: Context) {
        val mediaProjectionManager = context.getSystemService(Context.MEDIA_PROJECTION_SERVICE) as MediaProjectionManager
        val captureIntent = mediaProjectionManager.createScreenCaptureIntent()
        requestScreenCaptureLauncher.launch(captureIntent)
    }

    fun toggleScreenShare() {
        if (!isScreenCasting.value) {
            isScreenCasting.value = true
            serviceRepository.toggleScreenShare(true)
        } else {
            isScreenCasting.value = false
            serviceRepository.toggleScreenShare(false)
        }
    }

    override fun onCallEnded() {
        // Handle call end
    }

    override fun onCleared() {
        super.onCleared()
        MainService.remoteSurfaceView?.release()
        MainService.remoteSurfaceView = null

        MainService.localSurfaceView?.release()
        MainService.localSurfaceView = null
    }
}