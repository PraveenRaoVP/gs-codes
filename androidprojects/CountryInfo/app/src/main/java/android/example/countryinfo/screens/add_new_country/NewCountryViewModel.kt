package android.example.countryinfo.screens.add_new_country

import android.app.Application
import android.example.countryinfo.database.CountryDatabaseDao
import android.example.countryinfo.models.CountryDetails
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

enum class CountryAPIStatus { IDLE, LOADING, ERROR, DONE }

class NewCountryViewModel(
    private val dataSource: CountryDatabaseDao,
    application: Application
) : AndroidViewModel(application) {
    private val _countryDetails = MutableLiveData<List<CountryProperty>?>()

    private val _showSnackBarEvent = MutableLiveData<Boolean>()
    val showSnackBarEvent: LiveData<Boolean>
        get() = _showSnackBarEvent

    private val _navigateToCountry = MutableLiveData<Boolean>()
    val navigateToCountry: LiveData<Boolean>
        get() = _navigateToCountry

    private val _status = MutableLiveData<CountryAPIStatus>()
    val status: LiveData<CountryAPIStatus>
        get() = _status

    init {
        _status.value = CountryAPIStatus.IDLE
    }

    private val job: Job = Job()
    private val uiScope = CoroutineScope(job + Dispatchers.Main)

    private val _onSearchClickBtn = MutableLiveData<Boolean>()
    val onSearchClickBtn: LiveData<Boolean>
        get() = _onSearchClickBtn

    fun searchCountry(name: String) {
        val retrofitService = CountryAPI.retrofitService
        val call = retrofitService.getCountryDetailsByName(name)
        uiScope.launch {
            call.enqueue(object : Callback<List<CountryProperty>> {
                override fun onResponse(
                    call: Call<List<CountryProperty>>,
                    response: Response<List<CountryProperty>>
                ) {
                    _status.value = CountryAPIStatus.LOADING
                    if (response.isSuccessful) {
                        val countryProperty = response.body()
                        _countryDetails.value = countryProperty
                        _status.value = CountryAPIStatus.DONE
                        uiScope.launch {
                            if (countryProperty != null) {
                                val name: String? = countryProperty[0].name.common
                                val capital: List<String> = countryProperty[0].capital
                                val flag: String? = countryProperty[0].flagImageUrl ?: ""
                                val population: Long = countryProperty[0].population
                                val languages: Map<String, String>? = countryProperty[0].languages
                                val currencies: Map<String, Map<String, String>>? = countryProperty[0].currencies
                                val continents: List<String> = countryProperty[0].continents
                                val countryDetails = CountryDetails(0,name!!, capital[0], flag, population, languages!!, currencies, continents)
                                dataSource.insert(countryDetails)
                            }
                        }
                        _navigateToCountry.value = true
                    } else {
                        _status.value = CountryAPIStatus.ERROR
                        _showSnackBarEvent.value = true
                    }
                }

                override fun onFailure(call: Call<List<CountryProperty>>, t: Throwable) {
                    _status.value = CountryAPIStatus.ERROR
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

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}