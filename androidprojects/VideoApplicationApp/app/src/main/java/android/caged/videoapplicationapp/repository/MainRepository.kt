package android.caged.videoapplicationapp.repository

import android.caged.videoapplicationapp.firebase.FirebaseClient
import android.caged.videoapplicationapp.model.Data
import android.caged.videoapplicationapp.model.DataModelType
import android.util.Log
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val firebaseClient: FirebaseClient
){
    var listener: Listener? = null

    fun login(username: String, password: String, done: (Boolean, String?) -> Unit) {
        firebaseClient.login(username, password, done)
    }

    fun logout() {
        firebaseClient.logout()
    }

    fun observeUserStatus(update: (List<Pair<String, String>>) -> Unit) {
        firebaseClient.observeUserStatus(update)
    }

    fun initFirebase() {
        Log.i("MainRepository", "Initializing Firebase for latest events")
        firebaseClient.subscribeForLatestEvent(object : FirebaseClient.Listener {
            override fun onLatestEventReceived(event: Data) {
                Log.i("MainRepository", "Event received in repository: $event")
                listener?.onLatestEventReceived(event)
                when (event.type) {
                    else -> Unit
                }
            }
        })
    }


    fun sendConnectionRequest(target: String, isVideoCall: Boolean, success: (Boolean) -> Unit) {
        firebaseClient.sendMessageToOtherClient(
            Data(
                type = if(isVideoCall) DataModelType.StartVideoCall else DataModelType.StartAudioCall,
                target = target
            ),
            success
        )
    }

    interface Listener {
        fun onLatestEventReceived(event: Data)
    }
}