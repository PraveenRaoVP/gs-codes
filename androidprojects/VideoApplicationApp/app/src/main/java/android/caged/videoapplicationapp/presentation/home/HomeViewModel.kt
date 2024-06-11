package android.caged.videoapplicationapp.presentation.home

import android.caged.videoapplicationapp.firebase.FirebaseClient
import android.caged.videoapplicationapp.presentation.navigation.Routes
import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val firebaseClient: FirebaseClient
) : ViewModel(){
    var username: String? = null

    init {
        subscribeObservers()
        startListenerService()
    }

    private fun subscribeObservers() {
        firebaseClient.observeUserStatus {
            Log.d("HomeViewModel", "subscribe observer: $it")
        }
    }

    fun logout(navigateTo: (String, String) -> Unit) {
        firebaseClient.logout()
        navigateTo(Routes.LoginRoute.route, Routes.HomeRoute.route)
    }

    private fun startListenerService() {
        //
    }

}