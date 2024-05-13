package android.example.newsifytrial.screens.newslist

import android.app.Application
import android.content.Context
import android.example.newsifytrial.database.NewsDao
import android.example.newsifytrial.database.NewsProperty
import android.example.newsifytrial.models.news.NewsData
import android.example.newsifytrial.network.news.NewsAPIService
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
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

class NewsListViewModel(private val dataSource: NewsDao, private val application: Application) :
    AndroidViewModel(application) {
    val categories = listOf(
        "All",
        "National",
        "Business",
        "Sports",
        "World",
        "Politics",
        "Technology",
        "Startup",
        "Entertainment",
        "Miscellaneous",
        "Hatke",
        "Science",
        "Automobile"
    )

    val currentCategory = MutableLiveData<String>("all")

    private val _categoryClicked = MutableLiveData<Boolean>()
    val categoryClicked: LiveData<Boolean>
        get() = _categoryClicked

    private val _showErrorToast = MutableLiveData<Boolean>()
    val showErrorToast: LiveData<Boolean>
        get() = _showErrorToast

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _hasMoreData = MutableLiveData<Boolean>()
    val hasMoreData: LiveData<Boolean>
        get() = _hasMoreData

    private val _newsList = MutableLiveData<List<NewsProperty>>()
    val newsList: LiveData<List<NewsProperty>>
        get() = _newsList

    private val _isViewModelInitialized = MutableLiveData<Boolean>()
    val isViewModelInitialized: LiveData<Boolean>
        get() = _isViewModelInitialized

    private val _clickedNewsItem = MutableLiveData<Boolean>()
    val clickedNewsItem: LiveData<Boolean>
        get() = _clickedNewsItem

    private val _clickedItemDetails = MutableLiveData<String>()
    val clickedItemDetails: LiveData<String>
        get() = _clickedItemDetails


    private val job: Job = Job()
    private val uiScope = CoroutineScope(job + Dispatchers.Main)

    // pagination
    val pageSize = 3
    var currentPage = 0

    init {
        currentCategory.value = "all"
    }

    fun onClickCategory(category: String) {
        currentCategory.value = category.lowercase()
        Log.i("NewsListViewModel", "Category clicked: $category")
        _categoryClicked.value = true
    }


    private fun fetchAllDataFromApiAndStoreInDb() {
        for (category in categories) {
            fetchDataFromApiAndStoreInDb(category.lowercase())
        }
    }

    fun onCompleteToastShow() {
        _showErrorToast.value = false
    }


    fun fetchDataFromApiAndStoreInDb(category: String) {
        val retroService = NewsAPIService.retrofitService
        val call = retroService.getNewsByCategory(category)
        Log.i("NewsListViewModel", "Fetching data from API for category: $category...")
        uiScope.launch {
            call.enqueue(object : Callback<NewsData> {
                override fun onResponse(p0: Call<NewsData>, p1: Response<NewsData>) {
                    if (!p1.isSuccessful) {
                        _showErrorToast.value = true
                        _isLoading.value = false
                        return
                    }
                    _isLoading.value = false
                    val data = p1.body()?.data ?: listOf()
                    val category = p1.body()?.category ?: ""

                    uiScope.launch {
                        for (news in data) {
                            val newsDataToInsert = NewsProperty(
                                0,
                                category,
                                news.author,
                                news.content,
                                news.date,
                                news.imageUrl,
                                news.readMoreUrl,
                                news.time,
                                news.title,
                                news.url
                            )
                            dataSource.insertNews(newsDataToInsert)
                        }
                    }
                    _hasMoreData.value = data.size >= pageSize
                }

                override fun onFailure(p0: Call<NewsData>, p1: Throwable) {
                    _showErrorToast.value = true
                    _isLoading.value = false
                    Log.i("NewsListViewModel", "Failed to get data from API")
                }
            })
        }
    }

    fun getDataFromDatabase(category: String) {
        val smallCategory = category.lowercase()
//        _newsList.value = mutableListOf()
        uiScope.launch {
            _isLoading.value = true
            val newsData = when (smallCategory) {
                "all" -> {
                    dataSource.getAllNews(pageSize, currentPage * pageSize)
                }

                else -> {
                    dataSource.getPaginatedNewsByCategory(
                        smallCategory,
                        pageSize,
                        currentPage * pageSize
                    )
                }
            }
            _newsList.postValue(newsData)
            _isLoading.value = false
            _hasMoreData.value = _newsList.value?.size ?: 0 >= pageSize
            currentPage++
        }
    }

    fun populatingData() {
        // if network connection is there, delete the contents of the database and fetch from database
        // and then populate newslist with the data from the database. Else if network connection is not
        // present, then just get the data from the database without deleting the contents in the db
        uiScope.launch {
            val isNetworkAvailable = isNetworkAvailable(application)
            if (isNetworkAvailable) {
                Log.i("NewsListViewModel", "Network is available")
                deleteAllNewsFromDatabase()
                fetchAllDataFromApiAndStoreInDb()
                getDataFromDatabase(currentCategory.value ?: "all")
                _isViewModelInitialized.value = true
            } else {
                Log.i("NewsListViewModel", "Network is not available")
                getDataFromDatabase(currentCategory.value ?: "all")
            }
        }
    }

    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val networkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
            return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }
    }

    private suspend fun deleteAllNewsFromDatabase() {
        dataSource.deleteAllNews()
    }

    fun navigateToDetails(readMoreUrl: String) {
        _clickedItemDetails.value = readMoreUrl
        Log.i("NewsListViewModel", "News item clicked: $readMoreUrl...")
        _clickedNewsItem.value = true
    }

    fun onCompleteNavigation() {
        _clickedNewsItem.value = false
    }

    fun loadMoreData() {
        getDataFromDatabase(currentCategory.value ?: "all")
        _isLoading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

}