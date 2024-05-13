package android.example.newsify.screens.newslist

import android.app.Application
import android.example.newsify.database.newsdb.NewsDao
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class NewsListViewModelFactory(private val dataSource: NewsDao, private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsListViewModel::class.java)) {
            return NewsListViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}