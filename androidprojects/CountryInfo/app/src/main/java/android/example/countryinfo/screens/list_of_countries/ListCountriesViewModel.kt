package android.example.countryinfo.screens.list_of_countries

import android.app.Application
import android.example.countryinfo.database.CountryDatabaseDao
import android.example.countryinfo.models.CountryDetails
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ListCountriesViewModel(
    private val dataSource: CountryDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    val countries = MutableLiveData<List<CountryDetails>>()

    private val job: Job = Job()
    private val uiScope = CoroutineScope(job + Dispatchers.Main)

    private val _navigateToCountryDetail = MutableLiveData<Boolean>()
    val navigateToCountryDetail: LiveData<Boolean>
        get() = _navigateToCountryDetail

    private val _countryId = MutableLiveData<Int>()
    val countryId: LiveData<Int>
        get() = _countryId

    init {
        uiScope.launch {
            getCountries()
        }
    }

    private fun getCountries() {
        uiScope.launch {
            countries.value = dataSource.getAllCountryDetails()
        }
    }

    private val _navigateToNewCountry = MutableLiveData<Boolean>()
    val navigateToNewCountry: LiveData<Boolean>
        get() = _navigateToNewCountry


    fun onDeleteBtnClicked(id: Int) {
        uiScope.launch {
            dataSource.deleteCountryDetailsById(id)
            countries.value = dataSource.getAllCountryDetails()
        }
    }

    fun onClickNewCountry() {
        _navigateToNewCountry.value = true
    }

    fun doneNavigatingToNewCountry() {
        _navigateToNewCountry.value = false
    }

    fun onCountryItemClicked(id: Int) {
        _countryId.value = id
        _navigateToCountryDetail.value = true
    }

    fun doneNavigatingToCountryDetail() {
        _countryId.value = -1
        _navigateToCountryDetail.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}