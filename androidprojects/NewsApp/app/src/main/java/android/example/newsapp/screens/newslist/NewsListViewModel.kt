package android.example.newsapp.screens.newslist

import android.app.Application
import android.example.newsapp.database.NewsDao
import android.example.newsapp.database.NewsProperty
import android.example.newsapp.models.Location
import android.example.newsapp.models.NewsData
import android.example.newsapp.models.Values
import android.example.newsapp.network.RetroInstance
import android.example.newsapp.network.news.NewsAPIService
import android.example.newsapp.network.weather.WeatherAPIService
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class NewsListViewModel(private val dataSource: NewsDao, private val application: Application) :
    AndroidViewModel(application) {

    private val job: Job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)

    private val _newsData = MutableLiveData<List<NewsProperty>>()
    val newsData: LiveData<List<NewsProperty>>
        get() = _newsData

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

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

    private val fetchingDeferred = CompletableDeferred<Unit>()


    private val _categoryClicked = MutableLiveData<Boolean>()
    val categoryClicked: MutableLiveData<Boolean>
        get() = _categoryClicked

    private val _newsItemClicked = MutableLiveData<Boolean>()
    val newsItemClicked: MutableLiveData<Boolean>
        get() = _newsItemClicked

    private val _newsItemUrl = MutableLiveData<String>()
    val newsItemUrl: MutableLiveData<String>
        get() = _newsItemUrl

    private val _newsItemTitle = MutableLiveData<String>()
    val newsItemTitle: MutableLiveData<String>
        get() = _newsItemTitle

    private val _showErrorToast = MutableLiveData<Boolean>()
    val showErrorToast: MutableLiveData<Boolean>
        get() = _showErrorToast

    private val _isLoadingWeather = MutableLiveData<Boolean>()
    val isLoadingWeather: LiveData<Boolean>
        get() = _isLoadingWeather

    private val _showNoNewsToast = MutableLiveData<Boolean>()
    val showNoNewsToast: LiveData<Boolean>
        get() = _showNoNewsToast

    private val _addWeatherData = MutableLiveData<Boolean>()
    val addWeatherData: LiveData<Boolean>
        get() = _addWeatherData

    private val _weatherData = MutableLiveData<Values?>()
    val weatherData: LiveData<Values?>
        get() = _weatherData

    private val _isFirstTime = MutableLiveData<Boolean>(true)
    val isFirstTime: LiveData<Boolean>
        get() = _isFirstTime

    val _location = MutableLiveData<String>("chennai")

    var values: Values? = null
    var location: Location? = null

    var currentPage = 0
    val pageSize = 4


    fun onClickCategory(category: String) {
        currentCategory.value = category
        _categoryClicked.value = true
        Log.i("NewsListViewModel", "Category clicked: $category")
    }

    private suspend fun fetchDataFromAllCategories() {
        _isLoading.value = true
        val deferredList = mutableListOf<Deferred<Unit>>()
        for (category in categories) {
            val deferred = uiScope.async {
                fetchDataFromAPIAndStoreInDB(category.lowercase())
            }
            deferredList.add(deferred)
        }
        // Wait for all fetchDataFromAPIAndStoreInDB coroutines to complete
        deferredList.awaitAll()
        // Signal that all categories have been fetched
        fetchingDeferred.complete(Unit)
        _isLoading.value = false
    }

    fun fetchWeatherApi(): Deferred<Unit> {
        return uiScope.async {
            _isLoadingWeather.value = true
            val retrofit = WeatherAPIService.retrofitService
            try {
                val weatherData = withContext(Dispatchers.IO) {
                    retrofit.getWeather(_location.value!!, RetroInstance.WEATHER_API_KEY)
                }
                // Handle the response here
                if (weatherData != null) {
                    Log.i(
                        "NewsListViewModel",
                        "Weather data fetched: ${weatherData.data} ${weatherData.location}"
                    )
                    _weatherData.value = weatherData?.data?.values
                } else {
                    Log.i("NewsListViewModel", "Failed to get weather data from API")
                }
            } catch (e: Exception) {
                Log.e("NewsListViewModel", "Error fetching weather data from API: ${e.message}")
            } finally {
                _isLoadingWeather.value = false
            }
        }
    }

    private suspend fun fetchDataFromAPIAndStoreInDB(category: String) {
        val retrofit = NewsAPIService.retrofitService
        try {
            val newsData = withContext(Dispatchers.IO) {
                retrofit.getNewsByCategory(category)
            }
            // Handle the response here
            if (newsData != null) {
                for (news in newsData.data) {
                    val newsProperty = NewsProperty(
                        0,
                        newsData.category,
                        news.author,
                        news.content,
                        news.date,
                        news.imageUrl,
                        news.readMoreUrl,
                        news.time,
                        news.title,
                        news.url
                    )
                    dataSource.insert(newsProperty)
                    Log.i(
                        "NewsListViewModel",
                        "Data inserted in DB with category ${newsData.category}"
                    )
                }
            } else {
                Log.i("NewsListViewModel", "Failed to get data from API")
            }
        } catch (e: Exception) {
            Log.e("NewsListViewModel", "Error fetching data from API: ${e.message}")
        }
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            application.getSystemService(android.content.Context.CONNECTIVITY_SERVICE) as android.net.ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    fun populateDataFromDatabase(category: String) {
        synchronized(this) {
            if (isNetworkAvailable()) {
                uiScope.launch {
                    _isLoadingWeather.value = true
                    val weatherDeferred = fetchWeatherApi()
                    weatherDeferred.await()
                    _isLoadingWeather.value = false
                    _addWeatherData.value = true
                    dataSource.clear()
                    fetchDataFromAllCategories()
                    // Wait for fetchDataFromAllCategories() to complete
                    fetchingDeferred.await()
                    // Now that fetchDataFromAllCategories() has completed, fetch data from the database
                    getDataFromDatabase(currentCategory.value ?: "all")
                }
            } else {
                getDataFromDatabase(currentCategory.value ?: "all")
            }
        }
    }


//    fun getDataFromDatabase(category: String) {
//        if(category.lowercase() == "all") {
//            uiScope.launch {
//                withContext(Dispatchers.IO) {
//                    _newsData.postValue(dataSource.getAllNews())
//                }
//            }
//        } else {
//            uiScope.launch {
//                withContext(Dispatchers.IO) {
//                    _newsData.postValue(dataSource.getNewsByCategory(category.lowercase()))
//                }
//            }
//        }
//        Log.i("NewsListViewModel", "Data fetched from DB with category $category with size ${_newsData.value?.size}")
//    }

    fun getDataFromDatabase(category: String) {
        val limit = pageSize // Number of items to fetch per page
        val offset = currentPage * limit // Calculate the offset based on the current page

        if (category.lowercase() == "all") {
            uiScope.launch {
                val newData = withContext(Dispatchers.IO) {
                    dataSource.getAllNewsWithLimit(limit, offset)
                }
                val mergedData = _newsData.value.orEmpty().toMutableList().apply {
                    addAll(newData)
                }
                _newsData.postValue(mergedData)
            }
        } else {
            uiScope.launch {
                val newData = withContext(Dispatchers.IO) {
                    dataSource.getNewsByCategoryWithLimit(category.lowercase(), limit, offset)
                }
                val mergedData = _newsData.value.orEmpty().toMutableList().apply {
                    addAll(newData)
                }
                _newsData.postValue(mergedData)
            }
        }

        Log.i(
            "NewsListViewModel",
            "Data fetched from DB with category $category with size ${_newsData.value?.size}"
        )
    }

    fun loadMoreData(category: String) {
        if (!_isLoading.value!!) {
            _isLoading.value = true
            currentPage++
            getDataFromDatabase(category)
            _isLoading.value = false
        }
    }

    fun searchNews(searchQuery: String) {
        val limit = pageSize // Number of items to fetch per page
        val offset = currentPage * limit // Calculate the offset based on the current page

        if (searchQuery.isEmpty()) {
            getDataFromDatabase(currentCategory.value ?: "all")
        }

        uiScope.launch {
            val searchData = withContext(Dispatchers.IO) {
                dataSource.searchNewsWithLimit(
                    "%$searchQuery%",
                    limit,
                    offset
                ) // Use "%" for wildcard search
            }
            if (searchData.isEmpty()) {
                _showNoNewsToast.value = true
            }
            _newsData.postValue(searchData)
        }
    }

    fun onCompleteCategoryClicked() {
//        currentCategory.value = ""
        _categoryClicked.value = false
    }

    fun onClickNewsItem(readMoreUrl: String, title: String) {
        _newsItemUrl.value = readMoreUrl
        _newsItemTitle.value = title
        _newsItemClicked.value = true
    }

    fun onCompletedNavigation() {
        _newsItemUrl.value = ""
        _newsItemClicked.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    fun onCompletedShowErrorToast() {
        _showErrorToast.value = false
    }

    fun onCompletedAddWeatherData() {
        _addWeatherData.value = false
    }

    fun prePopulating() {
        currentPage = 0
        _newsData.value = emptyList()
    }

    fun onCompletedShowNoNewsToast() {
        _showNoNewsToast.value = false
    }

}