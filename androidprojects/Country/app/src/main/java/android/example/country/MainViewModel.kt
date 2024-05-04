package android.example.country

import android.app.Application
import android.example.country.network.CountryAPI
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainViewModel(private val application: Application) : AndroidViewModel(application) {
    private val _countryDetails = MutableLiveData<String>()
    private val job = Job()

    private val uiScope = CoroutineScope(job + Dispatchers.Main)

    val countryDetails: LiveData<String>
        get() = _countryDetails

    fun setCountryDetails(name: String) {
        _countryDetails.value = name
    }

    private val _onSubmitBtnClick = MutableLiveData<Boolean>()
    val onSubmitBtnClick: LiveData<Boolean>
        get() = _onSubmitBtnClick

    fun setOnSubmitBtnClick() {
        _onSubmitBtnClick.value = true
    }

    fun getCountryDetails(name: String) {
         uiScope.launch {
            try {
                val response = CountryAPI.retrofitService.getCountryByName(name)
                Log.i("Response", response)
                setCountryDetails(response)
            } catch (e: Exception) {
                setCountryDetails("There was an error fetching the data. Please try again later.")
            }
        }
    }

    fun doneFetching() {
        _onSubmitBtnClick.value = false
    }
}