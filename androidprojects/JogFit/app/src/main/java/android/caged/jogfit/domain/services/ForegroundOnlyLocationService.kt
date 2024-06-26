package android.caged.jogfit.domain.services
//
//import android.Manifest
//import android.app.Notification
//import android.app.NotificationChannel
//import android.app.NotificationManager
//import android.app.PendingIntent
//import android.app.Service
//import android.caged.jogfit.MainActivity
//import android.caged.jogfit.R
//import android.caged.jogfit.utils.SharedPreferenceUtil
//import android.caged.jogfit.utils.toText
//import android.content.Context
//import android.content.Intent
//import android.content.pm.PackageManager
//import android.content.res.Configuration
//import android.location.Location
//import android.location.LocationManager
//import android.os.Binder
//import android.os.Build
//import android.os.IBinder
//import android.os.Looper
//import android.util.Log
//import androidx.annotation.RequiresApi
//import androidx.core.app.ActivityCompat
//import androidx.core.app.NotificationCompat
//import androidx.core.content.getSystemService
//import androidx.localbroadcastmanager.content.LocalBroadcastManager
//import com.google.android.gms.location.FusedLocationProviderClient
//import com.google.android.gms.location.LocationCallback
//import com.google.android.gms.location.LocationRequest
//import com.google.android.gms.location.LocationResult
//import com.google.android.gms.location.LocationServices
//import com.google.android.gms.location.Priority
//import com.google.android.gms.maps.model.LatLng
//import dagger.hilt.android.AndroidEntryPoint
//import dagger.hilt.android.qualifiers.ApplicationContext
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.channels.awaitClose
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.callbackFlow
//import kotlinx.coroutines.launch
//import java.util.concurrent.TimeUnit
//import javax.inject.Inject
//
//interface LocationObserver {
//    fun observeLocation(interval: Long): Flow<LatLng>
//}
//
//@AndroidEntryPoint
//class ForegroundOnlyLocationService : Service() {
//
//    private lateinit var fusedLocationClient: FusedLocationProviderClient
//    private lateinit var locationCallback: LocationCallback
//
//    override fun onCreate() {
//        super.onCreate()
//
//        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
//
//        locationCallback = object : LocationCallback() {
//            override fun onLocationResult(locationResult: LocationResult) {
//                val location: Location? = locationResult.lastLocation
//                if (location != null) {
//                    // Broadcast the location update
//                    val intent = Intent(ACTION_LOCATION_BROADCAST).apply {
//                        putExtra(EXTRA_LOCATION, location)
//                    }
//                    sendBroadcast(intent)
//                } else {
//                    Log.e(TAG, "Location is null")
//                }
//            }
//        }
//        Log.i("Service", "Service started")
//        startLocationUpdates()
//    }
//
//    private fun startLocationUpdates() {
//        val locationRequest = LocationRequest.create().apply {
//            interval = 2000
//            fastestInterval = 1000
//            priority = Priority.PRIORITY_HIGH_ACCURACY // Use GPS only
//        }
//
//        try {
//            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null)
//        } catch (e: SecurityException) {
//            Log.e(TAG, "Location permission is not granted", e)
//        }
//    }
//
//    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//        startForeground(NOTIFICATION_ID, createNotification())
//        return START_STICKY
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        fusedLocationClient.removeLocationUpdates(locationCallback)
//    }
//
//    override fun onBind(intent: Intent?): IBinder? = null
//
//    private fun createNotification(): Notification {
//        val channelId = "location_service_channel"
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val channel = NotificationChannel(
//                channelId,
//                "Location Service",
//                NotificationManager.IMPORTANCE_LOW
//            )
//            val manager = getSystemService(NotificationManager::class.java)
//            manager.createNotificationChannel(channel)
//        }
//
//        val notificationIntent = Intent(this, MainActivity::class.java)
//        val pendingIntent =
//            PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE)
//
//        return NotificationCompat.Builder(this, channelId)
//            .setContentTitle("JogFit")
//            .setContentText("Tracking location")
//            .setSmallIcon(R.drawable.ic_launcher_foreground)
//            .setContentIntent(pendingIntent)
//            .build()
//    }
//
//    companion object {
//        const val ACTION_LOCATION_BROADCAST = "jogfit_location_broadcast"
//        const val EXTRA_LOCATION = "jogfit_location"
//        const val NOTIFICATION_ID = 1
//        private const val TAG = "ForegroundLocationService"
//    }
//
////    override fun observeLocation(interval: Long): Flow<LatLng> {
////        return callbackFlow {
////            val locationManager = applicationContext.getSystemService<LocationManager>()!!
////            var isGpsEnabled = false
////            var isNetworkEnabled = false
////            while(!isGpsEnabled && !isNetworkEnabled) {
////                isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
////                isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
////
////                if(!isGpsEnabled && !isNetworkEnabled) {
////                    delay(3000L)
////                }
////            }
////
////            if (ActivityCompat.checkSelfPermission(
////                    applicationContext,
////                    Manifest.permission.ACCESS_FINE_LOCATION
////                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
////                    applicationContext,
////                    Manifest.permission.ACCESS_COARSE_LOCATION
////                ) != PackageManager.PERMISSION_GRANTED
////            ) {
////                close()
////            } else {
////                fusedLocationClient.lastLocation.addOnSuccessListener {
////                    it?.let { location ->
////                        trySend(location.toLatLng())
////                    }
////                }
////
////                val request = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, interval)
////                    .build()
////
////                val locationCallback = object : LocationCallback() {
////                    override fun onLocationResult(result: LocationResult) {
////                        super.onLocationResult(result)
////                        result.locations.lastOrNull()?.let { location ->
////                            trySend(location.toLatLng())
////                        }
////                    }
////                }
////
////                fusedLocationClient.requestLocationUpdates(request, locationCallback, Looper.getMainLooper())
////
////                awaitClose {
////                    fusedLocationClient.removeLocationUpdates(locationCallback)
////                }
////            }
////        }
////    }
//}
//
//fun Location.toLatLng() : LatLng {
//    return LatLng(latitude, longitude)
//}
//
//
//
//
//
//
//
//
////
////
////class ForegroundOnlyLocationService : Service() {
////    private var configurationChange = false
////
////    private var serviceRunningInForeground = false
////
////    private val localBinder = LocalBinder()
////
////    private lateinit var notificationManager: NotificationManager
////
////
////    // TODO: Step 1.1, Review variables (no changes).
////    // FusedLocationProviderClient - Main class for receiving location updates.
////    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
////
////    // LocationRequest - Requirements for the location updates, i.e., how often you should receive
////    // updates, the priority, etc.
////    private lateinit var locationRequest: LocationRequest
////
////    // LocationCallback - Called when FusedLocationProviderClient has a new Location.
////    private lateinit var locationCallback: LocationCallback
////
////    // Used only for local storage of the last known location. Usually, this would be saved to your
////    // database, but because this is a simplified sample without a full database, we only need the
////    // last location to create a Notification if the user navigates away from the app.
////    private var currentLocation: Location? = null
////
////
////
////
////    override fun onCreate() {
////        Log.d(TAG, "onCreate()")
////
////        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
////
////        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
////
////        locationRequest = LocationRequest
////            .Builder(LocationRequest.PRIORITY_HIGH_ACCURACY, TimeUnit.SECONDS.toMillis(10))
////            .setMaxUpdateDelayMillis(TimeUnit.MINUTES.toMillis(2))
////            .build()
////
////        // TODO: Step 1.4, Initialize the LocationCallback.
////        locationCallback = object : LocationCallback() {
////            override fun onLocationResult(locationResult: LocationResult) {
////                super.onLocationResult(locationResult)
////
////                // Normally, you want to save a new location to a database. We are simplifying
////                // things a bit and just saving it as a local variable, as we only need it again
////                // if a Notification is created (when the user navigates away from app).
////                currentLocation = locationResult.lastLocation
////
////                // Notify our Activity that a new location was added. Again, if this was a
////                // production app, the Activity would be listening for changes to a database
////                // with new locations, but we are simplifying things a bit to focus on just
////                // learning the location side of things.
////                val intent = Intent(ACTION_FOREGROUND_ONLY_LOCATION_BROADCAST)
////                intent.putExtra(EXTRA_LOCATION, currentLocation)
////                LocalBroadcastManager.getInstance(applicationContext).sendBroadcast(intent)
////
////                // Updates notification content if this service is running as a foreground
////                // service.
////                if (serviceRunningInForeground) {
////                    notificationManager.notify(
////                        NOTIFICATION_ID,
////                        generateNotification(location = currentLocation))
////                }
////            }
////        }
////    }
////
////    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
////        Log.d(TAG, "onStartCommand()")
////
////        val cancelLocationTrackingFromNotification =
////            intent.getBooleanExtra(EXTRA_CANCEL_LOCATION_TRACKING_FROM_NOTIFICATION, false)
////
////        if (cancelLocationTrackingFromNotification) {
////            unsubscribeToLocationUpdates()
////            stopSelf()
////        }
////        // Tells the system not to recreate the service after it's been killed.
////        return START_NOT_STICKY
////    }
////
////    override fun onBind(intent: Intent): IBinder {
////        Log.d(TAG, "onBind()")
////
////        // MainActivity (client) comes into foreground and binds to service, so the service can
////        // become a background services.
////        stopForeground(true)
////        serviceRunningInForeground = false
////        configurationChange = false
////        return localBinder
////    }
////
////    override fun onRebind(intent: Intent) {
////        Log.d(TAG, "onRebind()")
////
////        // MainActivity (client) returns to the foreground and rebinds to service, so the service
////        // can become a background services.
////        stopForeground(true)
////        serviceRunningInForeground = false
////        configurationChange = false
////        super.onRebind(intent)
////    }
////
////    override fun onUnbind(intent: Intent): Boolean {
////        Log.d(TAG, "onUnbind()")
////
////        // MainActivity (client) leaves foreground, so service needs to become a foreground service
////        // to maintain the 'while-in-use' label.
////        // NOTE: If this method is called due to a configuration change in MainActivity,
////        // we do nothing.
////        if (!configurationChange && SharedPreferenceUtil.getLocationTrackingPref(this)) {
////            Log.d(TAG, "Start foreground service")
////            val notification = generateNotification(currentLocation)
////            startForeground(NOTIFICATION_ID, notification)
////            serviceRunningInForeground = true
////        }
////
////        // Ensures onRebind() is called if MainActivity (client) rebinds.
////        return true
////    }
////
////
////    override fun onDestroy() {
////        Log.d(TAG, "onDestroy()")
////    }
////
////    override fun onConfigurationChanged(newConfig: Configuration) {
////        super.onConfigurationChanged(newConfig)
////        configurationChange = true
////    }
////
////    fun subscribeToLocationUpdates() {
////        Log.d(TAG, "subscribeToLocationUpdates()")
////
////        SharedPreferenceUtil.saveLocationTrackingPref(this, true)
////
////        // Binding to this service doesn't actually trigger onStartCommand(). That is needed to
////        // ensure this Service can be promoted to a foreground service, i.e., the service needs to
////        // be officially started (which we do here).
////        Log.i(TAG, "Look at this: ${this.toString()}")
////
////        startService(Intent(this, ForegroundOnlyLocationService::class.java))
////
////        try {
////            // TODO: Step 1.5, Subscribe to location changes.
////            fusedLocationProviderClient.requestLocationUpdates(
////                locationRequest, locationCallback, Looper.getMainLooper())
////        } catch (unlikely: SecurityException) {
////            SharedPreferenceUtil.saveLocationTrackingPref(this, false)
////            Log.e(TAG, "Lost location permissions. Couldn't remove updates. $unlikely")
////        }
////    }
////
////    fun unsubscribeToLocationUpdates() {
////        Log.d(TAG, "unsubscribeToLocationUpdates()")
////
////        try {
////            // TODO: Step 1.6, Unsubscribe to location changes.
////            val removeTask = fusedLocationProviderClient.removeLocationUpdates(locationCallback)
////            removeTask.addOnCompleteListener { task ->
////                if (task.isSuccessful) {
////                    Log.d(TAG, "Location Callback removed.")
////                    stopSelf()
////                } else {
////                    Log.d(TAG, "Failed to remove Location Callback.")
////                }
////            }
////            SharedPreferenceUtil.saveLocationTrackingPref(this, false)
////        } catch (unlikely: SecurityException) {
////            SharedPreferenceUtil.saveLocationTrackingPref(this, true)
////            Log.e(TAG, "Lost location permissions. Couldn't remove updates. $unlikely")
////        }
////    }
////
////    /*
////     * Generates a BIG_TEXT_STYLE Notification that represent latest location.
////     */
////    private fun generateNotification(location: Location?): Notification {
////        Log.d(TAG, "generateNotification()")
////
////        // Main steps for building a BIG_TEXT_STYLE notification:
////        //      0. Get data
////        //      1. Create Notification Channel for O+
////        //      2. Build the BIG_TEXT_STYLE
////        //      3. Set up Intent / Pending Intent for notification
////        //      4. Build and issue the notification
////
////        // 0. Get data
////        val mainNotificationText = location?.toText() ?: getString(R.string.no_location_text)
////        val titleText = getString(R.string.app_name)
////
////        // 1. Create Notification Channel for O+ and beyond devices (26+).
////        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
////
////            val notificationChannel = NotificationChannel(
////                NOTIFICATION_CHANNEL_ID, titleText, NotificationManager.IMPORTANCE_DEFAULT)
////
////            // Adds NotificationChannel to system. Attempting to create an
////            // existing notification channel with its original values performs
////            // no operation, so it's safe to perform the below sequence.
////            notificationManager.createNotificationChannel(notificationChannel)
////        }
////
////        // 2. Build the BIG_TEXT_STYLE.
////        val bigTextStyle = NotificationCompat.BigTextStyle()
////            .bigText(mainNotificationText)
////            .setBigContentTitle(titleText)
////
////        // 3. Set up main Intent/Pending Intents for notification.
////        val launchActivityIntent = Intent(this, MainActivity::class.java)
////
////        val cancelIntent = Intent(this, ForegroundOnlyLocationService::class.java)
////        cancelIntent.putExtra(EXTRA_CANCEL_LOCATION_TRACKING_FROM_NOTIFICATION, true)
////
////        val servicePendingIntent = PendingIntent.getService(
////            this, 0, cancelIntent, PendingIntent.FLAG_UPDATE_CURRENT)
////
////        val activityPendingIntent = PendingIntent.getActivity(
////            this, 0, launchActivityIntent, PendingIntent.FLAG_IMMUTABLE)
////
////        // 4. Build and issue the notification.
////        // Notification Channel Id is ignored for Android pre O (26).
////        val notificationCompatBuilder =
////            NotificationCompat.Builder(applicationContext, NOTIFICATION_CHANNEL_ID)
////
////        return notificationCompatBuilder
////            .setStyle(bigTextStyle)
////            .setContentTitle(titleText)
////            .setContentText(mainNotificationText)
////            .setSmallIcon(R.mipmap.ic_launcher)
////            .setDefaults(NotificationCompat.DEFAULT_ALL)
////            .setOngoing(true)
////            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
////            .addAction(
////                R.drawable.ic_launch, getString(R.string.launch_activity),
////                activityPendingIntent
////            )
////            .build()
////    }
////
////    /**
////     * Class used for the client Binder.  Since this service runs in the same process as its
////     * clients, we don't need to deal with IPC.
////     */
////    inner class LocalBinder : Binder() {
////        internal val service: ForegroundOnlyLocationService
////            get() = this@ForegroundOnlyLocationService
////    }
////
////    companion object {
////        private const val TAG = "ForegroundOnlyLocationService"
////
////        private const val PACKAGE_NAME = "android.caged.jogfit"
////
////        internal const val ACTION_FOREGROUND_ONLY_LOCATION_BROADCAST =
////            "$PACKAGE_NAME.action.FOREGROUND_ONLY_LOCATION_BROADCAST"
////
////        internal const val EXTRA_LOCATION = "$PACKAGE_NAME.extra.LOCATION"
////
////        private const val EXTRA_CANCEL_LOCATION_TRACKING_FROM_NOTIFICATION =
////            "$PACKAGE_NAME.extra.CANCEL_LOCATION_TRACKING_FROM_NOTIFICATION"
////
////        private const val NOTIFICATION_ID = 12345678
////
////        private const val NOTIFICATION_CHANNEL_ID = "while_in_use_channel_01"
////    }
////}