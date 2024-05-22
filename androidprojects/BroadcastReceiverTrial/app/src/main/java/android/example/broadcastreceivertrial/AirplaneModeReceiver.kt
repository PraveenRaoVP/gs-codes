package android.example.broadcastreceivertrial

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.widget.Toast

class AirplaneModeReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if(intent?.action == Intent.ACTION_AIRPLANE_MODE_CHANGED) {
            val isAirplaneModeEnabled = Settings.System.getInt(
                context?.contentResolver,
                Settings.Global.AIRPLANE_MODE_ON, 0
            ) != 0

            if(isAirplaneModeEnabled) {
                Toast.makeText(context, "Airplane mode is enabled", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Airplane mode is disabled", Toast.LENGTH_SHORT).show()
            }
        }
    }
}