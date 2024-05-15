package android.example.newsapp.screens.newsdetails

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class NewsDetailsViewModel(private val application: Application) : AndroidViewModel(application) {

    private val _isShareClicked = MutableLiveData<Boolean>()
    val isShareClicked: MutableLiveData<Boolean>
        get() = _isShareClicked

    private val _isLinkClicked = MutableLiveData<Boolean>()
    val isLinkClicked: MutableLiveData<Boolean>
        get() = _isLinkClicked

    fun onShareClicked() {
        _isShareClicked.value = true
    }

    fun onLinkClicked() {
        _isLinkClicked.value = true
    }

    fun onShareComplete() {
        _isShareClicked.value = false
    }

    fun onLinkComplete() {
        _isLinkClicked.value = false
    }
}