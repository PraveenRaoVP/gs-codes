package android.example.newsify

import android.app.Application
import android.example.newsify.database.newsdb.NewsDao
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel(private val dataSource: NewsDao, application: Application) : AndroidViewModel(application) {
    private val job = Job()
    private val uiScope = CoroutineScope(job + Dispatchers.Main)

    fun destroyTables() {
        uiScope.launch {
            dataSource.deleteAllNewsData()
            dataSource.deleteAllNews()
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    fun isNetworkAvailable(application: Application?): Boolean {
        val connectivityManager = application?.getSystemService(android.content.Context.CONNECTIVITY_SERVICE) as android.net.ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}