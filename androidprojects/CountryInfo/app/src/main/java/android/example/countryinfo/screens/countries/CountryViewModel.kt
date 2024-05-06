package android.example.countryinfo.screens.countries

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

class CountryViewModel(
    private val dataSource: CountryDatabaseDao,
    private val application: Application
) : AndroidViewModel(application) {

    private val job: Job = Job()
    private val uiScope = CoroutineScope(job + Dispatchers.Main)

    var countryId = -1

    private val _backButtonPressed = MutableLiveData<Boolean>()
    val backButtonPressed: LiveData<Boolean>
        get() = _backButtonPressed

    private val _countryDetail = MutableLiveData<CountryDetails>()
    val countryDetail: LiveData<CountryDetails>
        get() = _countryDetail

    init {
        uiScope.launch {
            _countryDetail.value = dataSource.getCountryDetailsById(countryId)
        }
    }

    fun onBackClicked() {
        _backButtonPressed.value = true
    }

    fun doneNavigatingBack() {
        _backButtonPressed.value = false
    }
}