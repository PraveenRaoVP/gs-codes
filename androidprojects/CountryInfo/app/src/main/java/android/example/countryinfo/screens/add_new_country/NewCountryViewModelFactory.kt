package android.example.countryinfo.screens.add_new_country

import android.app.Application
import android.example.countryinfo.database.CountryDatabaseDao
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.sql.DataSource

class NewCountryViewModelFactory(
    private val dataSource: CountryDatabaseDao,
    private val application: Application
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(NewCountryViewModel::class.java)) {
            return NewCountryViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}