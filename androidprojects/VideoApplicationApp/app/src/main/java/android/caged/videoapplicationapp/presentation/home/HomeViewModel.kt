package android.caged.videoapplicationapp.presentation.home

import android.caged.videoapplicationapp.model.Data
import android.caged.videoapplicationapp.model.DataModelType
import android.caged.videoapplicationapp.presentation.navigation.Routes
import android.caged.videoapplicationapp.repository.MainRepository
import android.caged.videoapplicationapp.services.MainService
import android.caged.videoapplicationapp.services.MainServiceRepository
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ListenerRegistration
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val mainServiceRepository: MainServiceRepository
) : ViewModel(), MainService.ServiceListener {

    var username: String? = null
    var currentUser: Pair<String, String>? = null

    var uiState = mutableStateOf(HomeUiState())
        private set

    private val _usersList = mutableStateOf<List<Pair<String, String>>>(emptyList())
    val usersList: State<List<Pair<String, String>>> = _usersList

    init {
        subscribeObservers()
        startListenerService()
    }

    private fun updateUserList(users: List<Pair<String, String>>) {
        _usersList.value = users
    }

    private fun subscribeObservers() {
        MainService.listener = this
        mainRepository.observeUserStatus {
            updateUserList(it)
        }
    }

    fun logout(navigateTo: (String, String) -> Unit) {
        mainRepository.logout()
        mainServiceRepository.stopService()
        navigateTo(Routes.LoginRoute.route, Routes.HomeRoute.route)
    }

    private fun startListenerService() {
        if (username != null) {
            mainServiceRepository.startService(username!!)
        } else {
            Thread {
                while (username == null) {
                    Thread.sleep(1000)
                }
                mainServiceRepository.startService(username!!)
            }.start()
        }
    }

    fun prepareForVideoCall(user: Pair<String, String>) {
        currentUser = user
    }

    fun prepareForAudioCall(user: Pair<String, String>) {
        currentUser = user
    }

    fun notifyTarget(isVideoCall: Boolean) {
        currentUser?.let { userData ->
            val callType = if (isVideoCall) DataModelType.StartVideoCall else DataModelType.StartAudioCall
            mainRepository.sendConnectionRequest(userData.first, isVideoCall) {
                if (it) {
                    uiState.value = uiState.value.copy(isCallReceived = true, callReceiver = userData.first, callReceiveText = "Incoming ${if (isVideoCall) "Video" else "Audio"} Call from ${userData.first}")
                } else {
//                    Toast.makeText(LocalContext.current, "Failed to notify target", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun acceptCall(navigateTo: (String, String) -> Unit) {
//        Log.i("HomeViewModel", "Accepting call from ${currentUser!!.first}") // TODO: currentUser is returning null, fix it
////        val route = Routes.VideoDetailRoute.createRoute()
//        //
    }

    fun rejectCall() {
        Log.i("HomeViewModel", "Rejecting call")
        uiState.value = uiState.value.copy(isCallReceived = false, callReceiveText = null)
    }

    override fun onCallReceived(mode: Data) {
        viewModelScope.launch {
            val isVideoCall = mode.type == DataModelType.StartVideoCall
            val callReceiveText = "Incoming ${if (isVideoCall) "Video" else "Audio"} Call from ${mode.sender}"
            uiState.value = uiState.value.copy(isCallReceived = true, callReceiveText = callReceiveText, callReceiver = mode.target)
        }
    }
}
