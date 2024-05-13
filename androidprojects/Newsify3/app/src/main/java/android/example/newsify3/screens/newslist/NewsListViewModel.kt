package android.example.newsify3.screens.newslist

import android.app.Application
import android.content.Context
import android.example.newsify3.database.NewsDao
import android.example.newsify3.database.NewsProperty
import android.example.newsify3.models.news.NewsData
import android.example.newsify3.models.weather.WeatherData
import android.example.newsify3.network.news.NewsAPIService
import android.example.newsify3.network.weather.API_KEY
import android.example.newsify3.network.weather.WeatherAPIService
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsListViewModel(private val dataSource: NewsDao, application: Application) : AndroidViewModel(application) {

    private val _allDataListFromRoom = MutableLiveData<List<NewsProperty?>>().apply { value = emptyList() }
    var category: String = "all"
    val allDataListFromRoom: LiveData<List<NewsProperty?>>
        get() = _allDataListFromRoom
    val _categoryDataListFromRoom = MutableLiveData<List<NewsProperty?>>()
    var currentPage = 0
    private val pageSize = 4 // Number of items per page
    var searchFlag: Boolean = false;

    val weatherData = MutableLiveData<WeatherData>()

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
        "Automobile")

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun getMyData() {
        for (c in categories) {
            fetchDataFromApiAndStoreInDb(c.lowercase())
        }
    }


    private fun fetchDataFromApiAndStoreInDb(category: String) {
        val retroService = NewsAPIService.retrofitService
        val call = retroService.getNewsByCategory(category)
        Log.i("NewsListViewModel", "Fetching data from API for category: $category...")
        uiScope.launch {
            call.enqueue(object : Callback<NewsData> {
                override fun onResponse(p0: Call<NewsData>, p1: Response<NewsData>) {
                    if (!p1.isSuccessful) {
                        return
                    }
                    val data = p1.body()?.data ?: listOf()
                    val category = p1.body()?.category ?: ""

                    uiScope.launch {
                        for (news in data) {
                            val newsDataToInsert = NewsProperty(
                                newsId = 0,
                                category=category,
                                author = news.author,
                                content = news.content,
                                date = news.date,
                                imageUrl = news.imageUrl,
                                readMoreUrl = news.readMoreUrl,
                                time = news.time,
                                title = news.title,
                                url = news.url,
                            )
                            dataSource.insert(newsDataToInsert)
                        }
                    }
                }

                override fun onFailure(p0: Call<NewsData>, p1: Throwable) {
                    Log.i("NewsListViewModel", "Failed to get data from API")
                }
            })
        }
    }

    fun showWeather() {
        val weatherService = WeatherAPIService.retrofitService
        val call = weatherService.getWeatherByCity(API_KEY,city = "Chennai", aqi = "no")
        call.enqueue(object : Callback<WeatherData> {
            override fun onResponse(call: Call<WeatherData>, response: Response<WeatherData>) {
                if (!response.isSuccessful) {
                    Log.i("WeatherData", "Failed to get weather data")
                    return
                }
                weatherData.value = response.body()
                Log.i("WeatherData", weatherData.toString())
            }

            override fun onFailure(call: Call<WeatherData>, t: Throwable) {
                Log.i("WeatherData", "Failed to get weather data")
            }
        })
    }


    fun dbRetrieve(){
        uiScope.launch {
            get()
        }
    }

    private suspend fun get() {
        return withContext(Dispatchers.IO) {
            // Reset pagination state when retrieving all data
            currentPage = 0
            val newData = dataSource.getAll(pageSize, currentPage * pageSize)
            _allDataListFromRoom.postValue(_allDataListFromRoom.value?.plus(newData))
            currentPage++
            Log.i("newviewmodel", "get all is running")
        }
    }

    fun dbRetrieveCategory(category: String, whereToAdd: String) {
        uiScope.launch {
            getByCategory(category, whereToAdd)
        }
    }

    private suspend fun getByCategory(category: String, whereToAdd: String) {
        return withContext(Dispatchers.IO) {
            // Reset pagination state when retrieving category-specific data
            currentPage = 0
            val newData = if (whereToAdd == "allData") {
                dataSource.getAllByCategory(category, pageSize, currentPage * pageSize)
            } else {
                if (category == "all") {
                    dataSource.getAll()
                } else {
                    dataSource.getAllByCategory(category, pageSize, currentPage * pageSize)
                }
            }
            if (whereToAdd == "allData") {
                _allDataListFromRoom.postValue(newData)
            } else {
                _categoryDataListFromRoom.postValue(newData)
            }
            currentPage++
            Log.i("newviewmodel", "getbycategory is running")
        }
    }

    fun dbRetrieveBySearch(query: String?, flag: String){
        uiScope.launch {
            search(query, flag)
        }
    }


    private suspend fun search(query: String?, flag: String){
        return withContext(Dispatchers.IO){
            if(flag == "scrollview"){
                _allDataListFromRoom.postValue(dataSource.search(query, pageSize, currentPage * pageSize))
            }else{
                _categoryDataListFromRoom.postValue(dataSource.search(query, pageSize, currentPage * pageSize))
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


}
