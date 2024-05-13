package android.example.newsifytrial.network.news

import android.example.newsifytrial.models.news.NewsData
import android.example.newsifytrial.network.RetroInstance
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://inshortsapi.vercel.app/"

private val retrofit = RetroInstance.getRetroInstance(BASE_URL)

interface NewsAPIServiceInterface {
    @GET("news")
    fun getNewsByCategory(@Query("category") category: String): retrofit2.Call<NewsData>
}

object NewsAPIService {
    val retrofitService: NewsAPIServiceInterface by lazy {
        retrofit.create(NewsAPIServiceInterface::class.java)
    }
}