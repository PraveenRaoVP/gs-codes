package android.example.newsify.screens.newslist

import android.app.Application
import android.example.newsify.database.newsdb.NewsDao
import android.example.newsify.database.props.DataEntity
import android.example.newsify.database.props.NewsDataEntity
import android.example.newsify.database.props.NewsDataWithArticles
import android.example.newsify.models.news.NewsData
import android.example.newsify.network.NewsAPIService
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsListViewModel(private val dataSource: NewsDao, application: Application) : AndroidViewModel(application){
    val categories = listOf("All","National","Business","Sports","World","Politics","Technology","Startup","Entertainment","Miscellaneous","Hatke","Science","Automobile")
    val currentCategory = MutableLiveData<String>()

    private val job: Job = Job()
    private val uiScope = CoroutineScope(job+ Dispatchers.Main)

    private var _newsList = MutableLiveData<List<DataEntity>>()
    val newList: LiveData<List<DataEntity>>
        get() = _newsList

    private val _showErrorToast = MutableLiveData<Boolean>()
    val showErrorToast: LiveData<Boolean>
        get() = _showErrorToast

    private val _clickedNewsItem = MutableLiveData<Boolean>()
    val clickedNewsItem: LiveData<Boolean>
        get() = _clickedNewsItem

    private val _clickedItemDetails = MutableLiveData<DataEntity?>()
    val clickedItemDetails: LiveData<DataEntity?>
        get() = _clickedItemDetails

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _hasMoreData = MutableLiveData<Boolean>(true)
    val hasMoreData: LiveData<Boolean>
        get() = _hasMoreData

    fun onShowErrorToastComplete(){
        _showErrorToast.value = false
    }
    val pageSize = 3
    var currentPage = 0

    // function to get all data from all categories from api
    fun fetchAllDataFromApi() {
        _isLoading.value = true
        for (category in categories) {
            fetchDataFromApiAndStoreInDB(category)
        }
    }



    // function to get data for each category in api and store in database
    fun fetchDataFromApiAndStoreInDB(category: String) {
        val retrofitService = NewsAPIService.retrofitService
        val smallCategory = category.lowercase()
        val call = retrofitService.getNewsByCategory(smallCategory)

        uiScope.launch { // coroutine for api call
            call.enqueue(object : Callback<NewsData> {
                override fun onResponse(p0: Call<NewsData>, p1: Response<NewsData>) {
                    if (!p1.isSuccessful) {
                        _showErrorToast.value = true
                        _isLoading.value = false
                        return
                    }

                    _isLoading.value = false
                    val data = p1.body()?.data ?: emptyList()

                    uiScope.launch {// coroutine for database operations
                        val newsDataDB = NewsDataEntity(0, smallCategory, p1.body()?.success ?: false)
                        dataSource.insertNewsData(newsDataDB)

                        val newsDataDBId = dataSource.getNewsByCategory(smallCategory).first().firstOrNull()?.id ?: 0

                        if (newsDataDBId != 0) {
                            // Data already exists, update it
                            for (itr in p1.body()?.data ?: emptyList()) {
                                val entity = DataEntity(0, newsDataDBId, itr.author, itr.content, itr.date, itr.imageUrl, itr.readMoreUrl, itr.time, itr.title, itr.url)
                                dataSource.insertNews(entity)
                            }
                        } else {
                            // Data doesn't exist, insert it
                            for (itr in p1.body()?.data ?: emptyList()) {
                                val entity = DataEntity(0, newsDataDBId, itr.author, itr.content, itr.date, itr.imageUrl, itr.readMoreUrl, itr.time, itr.title, itr.url)
                                dataSource.insertNews(entity)
                            }
                        }
                    }
                    _hasMoreData.value = data.size >= pageSize
                }

                override fun onFailure(p0: Call<NewsData>, p1: Throwable) {
                    _showErrorToast.value = true
                    _isLoading.value = false
                    Log.e("NewsListViewModel", "Failed to get news by category: $smallCategory")
                }
            })
        }
    }

    fun getDataFromDatabase(category: String) {
        // if category is all, get all data in the table
        val smallCategory = category.lowercase()
        if (smallCategory == "all") {
            uiScope.launch {
                val data = dataSource.getAllNews().first()
                _newsList.value = data
            }
        } else {
            // get data by category
            uiScope.launch {
                val data = dataSource.getNewsByCategory(smallCategory).first()
                _newsList.value = listOf()
                _newsList.value = data
            }
        }
    }

    fun onNewsItemClicked(newsData: DataEntity) {
        Log.i("NewsListViewModel", "News item clicked: ${newsData.readMoreUrl}...")
        _clickedNewsItem.value = true
        _clickedItemDetails.value = newsData
    }

    fun onCompleteNavigation() {
        _clickedNewsItem.value = false
    }


    fun loadNextPage(category: String) {
        currentPage += pageSize
        uiScope.launch {
            val data = dataSource.getPaginatedNewsByCategory(category, pageSize, currentPage).first()
            _newsList.value = data
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

}