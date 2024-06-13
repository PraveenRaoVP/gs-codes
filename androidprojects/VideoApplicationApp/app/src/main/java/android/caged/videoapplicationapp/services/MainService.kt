package android.caged.videoapplicationapp.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.caged.videoapplicationapp.R
import android.caged.videoapplicationapp.firebase.FirebaseClient
import android.caged.videoapplicationapp.model.Data
import android.caged.videoapplicationapp.model.DataModelType
import android.caged.videoapplicationapp.model.isValid
import android.caged.videoapplicationapp.repository.MainRepository
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import dagger.hilt.android.AndroidEntryPoint
import org.webrtc.SurfaceViewRenderer
import javax.inject.Inject

@AndroidEntryPoint
class MainService : Service(), MainRepository.Listener {

    @Inject
    lateinit var mainRepository: MainRepository

    private var isServiceRunning = false
    private var username: String? = null

    private lateinit var notificationManager: NotificationManager

    companion object {
        var listener: ServiceListener? = null
        var endCallListener: EndCallListener? = null
        var remoteSurfaceView: SurfaceViewRenderer? = null
        var localSurfaceView: SurfaceViewRenderer? = null
    }

    override fun onCreate() {
        super.onCreate()
        notificationManager = getSystemService(NotificationManager::class.java) as NotificationManager

        // Create the notification channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                "channel1",
                "foreground",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "This channel is used by foreground service"
            }
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let { incomingIntent ->
            when (incomingIntent.action) {
                MainServiceEvents.START_SERVICE.name -> handleStartService(incomingIntent)
                else -> Unit
            }
        }

        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    private fun handleStartService(intent: Intent) {
        if (!isServiceRunning) {
            isServiceRunning = true
            username = intent.getStringExtra("username")
            Log.i("MainService", "Service started for user: $username")
            startServiceWithNotification()

            mainRepository.listener = this
            Log.i("MainService", "Listener set in repository")
            mainRepository.initFirebase()
        }
    }

    private fun startServiceWithNotification() {
        val notification = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationCompat.Builder(this, "channel1")
                .setContentTitle("Foreground Service")
                .setContentText("Service is running")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_SERVICE)
                .build()
        } else {
            NotificationCompat.Builder(this)
                .setContentTitle("Foreground Service")
                .setContentText("Service is running")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_SERVICE)
                .build()
        }

        startForeground(1, notification)
    }

    override fun onLatestEventReceived(event: Data) {
        if(event.isValid()) {
            when(event.type) {
                DataModelType.StartVideoCall,
                DataModelType.StartAudioCall -> {
                    listener?.onCallReceived(event)
                }
                else -> Unit
            }
        }
    }

    interface ServiceListener {
        fun onCallReceived(mode: Data)
    }

    interface EndCallListener {
        fun onCallEnded()
    }
}