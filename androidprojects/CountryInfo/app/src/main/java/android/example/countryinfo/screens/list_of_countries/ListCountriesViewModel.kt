package android.example.countryinfo.screens.list_of_countries

import android.app.Application
import android.example.countryinfo.database.CountryDatabaseDao
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ListCountriesViewModel(
    private val dataSource: CountryDatabaseDao,
    private val application: Application
) : AndroidViewModel(application) {
    private val _navigateToNewCountry = MutableLiveData<Boolean>()
    val navigateToNewCountry: LiveData<Boolean>
        get() = _navigateToNewCountry

    fun onClickNewCountry() {
        _navigateToNewCountry.value = true
    }

    fun doneNavigatingToNewCountry() {
        _navigateToNewCountry.value = false
    }
}