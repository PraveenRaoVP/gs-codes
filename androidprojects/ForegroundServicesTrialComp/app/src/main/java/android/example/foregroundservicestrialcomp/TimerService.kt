package android.example.foregroundservicestrialcomp

import android.Manifest
import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Binder
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.math.absoluteValue

class TimerService : Service() {
    private val binder: TimerBinder = TimerBinder()
    private var startTime: Long = 0

    private val _elapsedSeconds = MutableStateFlow(0L)
    val elapsedSeconds: StateFlow<Long> get() = _elapsedSeconds

    private lateinit var runnable: Runnable
    private val handler = Handler(Looper.getMainLooper())

    inner class TimerBinder : Binder() {
        fun getService() = this@TimerService
    }

    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i("TimerService", "Intent Action: ${intent?.action}")
        when (intent?.action) {
            Actions.START.toString() -> startTimer()
            Actions.STOP.toString() -> stopTimer()
        }
        return START_STICKY
    }

    fun startTimer() {
        startTime = System.currentTimeMillis()
        runnable = getRunnable()
        handler.post(runnable)
        startForeground(ONGOING_NOTIFICATION_ID, createNotification("Timer started"))
    }

    fun stopTimer() {
        handler.removeCallbacks(runnable)
        stopForeground(STOP_FOREGROUND_REMOVE)
        stopSelf()
    }

    fun createNotification(contentText: String): Notification {
        val channelId = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            "timer_channel"
        } else {
            ""
        }

        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val stopIntent = Intent(this, TimerService::class.java).apply {
            action = Actions.STOP.toString()
        }
        val stopPendingIntent = PendingIntent.getService(
            this,
            0,
            stopIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        return NotificationCompat.Builder(this, channelId)
            .setContentTitle("Timer Service")
            .setContentText(contentText)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .addAction(R.drawable.ic_stop, "Stop", stopPendingIntent)
            .setOngoing(true)
            .build()
    }

    fun updateNotification(contentText: String) {
        val notification = createNotification(contentText)
        val notificationManager = NotificationManagerCompat.from(this)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        notificationManager.notify(ONGOING_NOTIFICATION_ID, notification)
    }

    fun fetchElapsedTime(): Long {
        return System.currentTimeMillis() - startTime
    }

    private fun getRunnable(): Runnable {
        if (!::runnable.isInitialized) {
            runnable = object : Runnable {
                override fun run() {
                    val elapsedTime = fetchElapsedTime()
                    _elapsedSeconds.value = elapsedTime / 1000
                    val contentText = "Elapsed Time: ${elapsedSeconds.value / 60}:${elapsedSeconds.value % 60}"
                    updateNotification(contentText)
                    handler.postDelayed(this, 1000)
                }
            }
        }
        return runnable
    }

    enum class Actions { START, STOP }

    companion object {
        const val ONGOING_NOTIFICATION_ID = 1
    }
}