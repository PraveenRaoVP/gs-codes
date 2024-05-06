package android.example.countryinfo.screens.add_new_country

import android.app.Application
import android.example.countryinfo.database.CountryDatabaseDao
import android.example.countryinfo.network.CountryAPI
import android.example.countryinfo.network.CountryProperty
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewCountryViewModel(
    private val dataSource: CountryDatabaseDao,
    private val application: Application
) : AndroidViewModel(application) {
    private val _countryDetails = MutableLiveData<CountryProperty?>()
    val countryDetails: LiveData<CountryProperty?>
        get() = _countryDetails

    private val _showSnackBarEvent = MutableLiveData<Boolean>()
    val showSnackBarEvent: LiveData<Boolean>
        get() = _showSnackBarEvent

    private val _navigateToCountry = MutableLiveData<Boolean>()
    val navigateToCountry: LiveData<Boolean>
        get() = _navigateToCountry

    private val job: Job = Job()
    private val uiScope = CoroutineScope(job + Dispatchers.Main)

    private val _onSearchClickBtn = MutableLiveData<Boolean>()
    val onSearchClickBtn: LiveData<Boolean>
        get() = _onSearchClickBtn

    fun searchCountry(name: String) {
        val retrofitService = CountryAPI.retrofitService
        val call = retrofitService.getCountryDetailsByName(name)
        uiScope.launch {
            call.enqueue(object : Callback<CountryProperty> {
                override fun onResponse(
                    call: Call<CountryProperty>,
                    response: Response<CountryProperty>
                ) {
                    if (response.isSuccessful) {
                        val countryProperty = response.body()
                        Log.i("NewCountryViewModel", "CountryProperty: $countryProperty")
                        _countryDetails.value = countryProperty
                        _navigateToCountry.value = true
                    } else {
                        Log.i("NewCountryViewModel", "Error: ${response.errorBody()}")
                        _showSnackBarEvent.value = true
                    }
                }

                override fun onFailure(call: Call<CountryProperty>, t: Throwable) {
                    Log.i("NewCountryViewModel", "Failure: ${t.message}")
                    _showSnackBarEvent.value = true
                }
            })
        }
    }

    fun onSearchClick() {
        _onSearchClickBtn.value = true
    }

    fun onShowSnackBarEventFinish() {
        _showSnackBarEvent.value = false
    }

    fun finishNavigatingToCountry() {
        _navigateToCountry.value = false
    }

    fun onDoneSearching() {
        _onSearchClickBtn.value = false
    }
}