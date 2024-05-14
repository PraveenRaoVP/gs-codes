package android.example.newsapp.screens.newslist

import android.app.Application
import android.content.Intent
import android.example.newsapp.R
import android.example.newsapp.database.NewsDao
import android.example.newsapp.database.NewsProperty
import android.example.newsapp.models.Location
import android.example.newsapp.models.NewsData
import android.example.newsapp.models.Values
import android.example.newsapp.models.WeatherData
import android.example.newsapp.network.RetroInstance
import android.example.newsapp.network.news.NewsAPIService
import android.example.newsapp.network.weather.WeatherAPIService
import android.example.newsapp.utils.WeatherCondition
import android.util.Log
import androidx.core.app.ShareCompat
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


    private val _weatherData = MutableLiveData<Values?>()
    val weatherData: LiveData<Values?>
        get() = _weatherData

    private val _isFirstTime = MutableLiveData<Boolean>(true)
    val isFirstTime: LiveData<Boolean>
        get() = _isFirstTime

    private val _shareNews = MutableLiveData<Boolean>()
    val shareNews: LiveData<Boolean>
        get() = _shareNews

    private val _shareNewsTitle = MutableLiveData<String>()
    val shareNewsTitle: LiveData<String>
        get() = _shareNewsTitle

    private val _shareNewsUrl = MutableLiveData<String>()
    val shareNewsUrl: LiveData<String>
        get() = _shareNewsUrl

    val _location = MutableLiveData<String>("delhi")

    var values: Values? = null
    var location: Location? = null

    var currentPage = 0
    val pageSize = 4

    /**
     * Triggered when the categories are clicked
     * @param category The category that was clicked
     */
    fun onClickCategory(category: String) {
        currentCategory.value = category
        _categoryClicked.value = true
        Log.i("NewsListViewModel", "Category clicked: $category")
    }

    /**
     * Fetch data from the API for all categories and store it in the database
     */
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

    /**
     * Get the weather condition based on the temperature, humidity, and precipitation probability
     * @param data The weather data
     * @return The weather condition
     */
    fun getWeatherCondition(data: Values): WeatherCondition {
        val temperature = data.temperature
        val humidity = data.humidity
        val precipitationProbability = data.precipitationProbability

        return when {
            temperature > 25 && humidity > 60 -> WeatherCondition.HOT_AND_HUMID
            temperature > 25 && precipitationProbability > 50 -> WeatherCondition.WARM_WITH_HIGH_CHANCE_OF_RAIN
            temperature > 25 -> WeatherCondition.WARM
            temperature in 20.0..25.0 -> WeatherCondition.MILD
            temperature in 10.0..20.0 -> WeatherCondition.COOL
            temperature < 10 && precipitationProbability > 50 -> WeatherCondition.COLD_WITH_HIGH_CHANCE_OF_RAIN
            temperature < 10 -> WeatherCondition.COLD
            else -> WeatherCondition.UNKNOWN
        }
    }

    /**
     * Get the weather image based on the weather condition
     * @param condition The weather condition
     * @return The weather image
     */
    fun getWeatherImage(condition: WeatherCondition): Int {
        return when (condition) {
            WeatherCondition.HOT_AND_HUMID -> R.drawable.sun
            WeatherCondition.WARM_WITH_HIGH_CHANCE_OF_RAIN -> R.drawable.cloudy_with_rain
            WeatherCondition.WARM -> R.drawable.sun
            WeatherCondition.MILD -> R.drawable.cloudy
            WeatherCondition.COOL -> R.drawable.cloudy
            WeatherCondition.COLD_WITH_HIGH_CHANCE_OF_RAIN -> R.drawable.rain
            WeatherCondition.COLD -> R.drawable.cloudy
            WeatherCondition.UNKNOWN -> R.drawable.unknown
        }
    }

    /**
     * Fetch weather data from the API
     * @return A deferred object representing the completion of the fetch operation
     */
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

    /**
     * Fetch data from the API for a specific category and store it in the database
     * @param category The category to fetch data for
     */
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

    /**
     * Check if the network is available
     * @return boolean
     */
    private fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            application.getSystemService(android.content.Context.CONNECTIVITY_SERVICE) as android.net.ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    /**
     * Populate data from the database. Uses deferred objects to fetch data from the API and the database in parallel
     * @param category The category to populate data for
     */
    fun populateDataFromDatabase(category: String) {
        synchronized(this) {
            if (isNetworkAvailable()) {
                uiScope.launch {
                    _isLoadingWeather.value = true
                    val weatherDeferred = fetchWeatherApi()
                    weatherDeferred.await()
                    _isLoadingWeather.value = false
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


    /**
     * Get paginated data from the database
     *
     * Calls the appropriate database function based on the category
     *
     * @param category The category to get data for
     */
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

    /**
     * Load more data from the database. Increases the current page and fetches more data
     * @param category The category to load more data for
     */
    fun loadMoreData(category: String) {
        if (!_isLoading.value!!) {
            _isLoading.value = true
            currentPage++
            getDataFromDatabase(category)
            _isLoading.value = false
        }
    }

    /**
     * Search for news in the database
     * Experimental feature: Search is case-insensitive and uses the "%" wildcard for partial matches
     * @param searchQuery The search query
     */
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

    /**
     * Before populating data with a new category, reset the current page and clear the existing data
     */
    fun prePopulating() {
        currentPage = 0
        _newsData.value = emptyList()
    }

    /**
     * Triggered when the news item is clicked
     * @param readMoreUrl The URL to open when the news item is clicked
     * @param title The title of the news item
     */
    fun onClickNewsItem(readMoreUrl: String, title: String) {
        _newsItemUrl.value = readMoreUrl
        _newsItemTitle.value = title
        _newsItemClicked.value = true
    }

    /**
     * Triggered when the news item is clicked and completed
     */
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

    /**
     * Triggered when the category is clicked and completed
     */
    fun onCompleteCategoryClicked() {
//        currentCategory.value = ""
        _categoryClicked.value = false
    }

    fun onCompletedShowNoNewsToast() {
        _showNoNewsToast.value = false
    }

    fun shareNews(title: String, readMoreUrl: String) {
        _shareNewsTitle.value = title
        _shareNewsUrl.value = readMoreUrl
        _shareNews.value = true
    }

    fun onCompletedShareNews() {
        _shareNews.value = false
    }

}