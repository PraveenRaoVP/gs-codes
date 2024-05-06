package android.example.countryinfo.screens.list_of_countries

import android.app.Application
import android.example.countryinfo.database.CountryDatabaseDao
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ListCountriesViewModelFactory(
    private val dataSource : CountryDatabaseDao,
    private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ListCountriesViewModel::class.java)) {
            return ListCountriesViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}