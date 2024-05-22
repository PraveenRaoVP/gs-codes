package android.example.broadcastreceivertrial

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var connectivityReceiver: ConnectivityReceiver
    private lateinit var airplaneModeReceiver: AirplaneModeReceiver


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        airplaneModeReceiver = AirplaneModeReceiver()
        connectivityReceiver = ConnectivityReceiver(this)
    }

    override fun onResume() {
        super.onResume()
        val airplaneFilter = IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        registerReceiver(airplaneModeReceiver, airplaneFilter)
        connectivityReceiver.register()
    }

    override fun onPause() {
        super.onPause()
        // Unregister the receiver
        unregisterReceiver(airplaneModeReceiver)
        connectivityReceiver.unregister()
    }
}