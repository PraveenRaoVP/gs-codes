package android.example.foregroundservicestrialcomp

import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat

class RunningService : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when(intent?.action) {
            Actions.START.toString() -> start()
            Actions.STOP.toString() -> stopSelf()
        }
        return START_STICKY
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun start() {
        val notification = NotificationCompat.Builder(this, "running_channel")
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentTitle("Running Service")
                    .setContentText("Elapsed Time: 00:50")
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .build()
        startForeground(1, notification)
    }

    enum class Actions { START, STOP }
}