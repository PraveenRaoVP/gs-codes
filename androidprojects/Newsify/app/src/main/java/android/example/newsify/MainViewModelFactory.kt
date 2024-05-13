package android.example.newsify

import android.app.Application
import android.example.newsify.database.newsdb.NewsDao
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.sql.DataSource

class MainViewModelFactory(private val dataSource: NewsDao, private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}