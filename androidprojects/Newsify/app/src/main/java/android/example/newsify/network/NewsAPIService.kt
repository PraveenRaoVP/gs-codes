package android.example.newsify.network

import android.example.newsify.models.news.NewsData
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query



private val retrofit = RetroInstance.getRetroInstance()

interface NewsAPIServiceInterface {
    @GET("news")
    fun getNewsByCategory(@Query("category") category: String): Call<NewsData>
}


object NewsAPIService {
    val retrofitService : NewsAPIServiceInterface by lazy {
        retrofit.create(NewsAPIServiceInterface::class.java)
    }
}