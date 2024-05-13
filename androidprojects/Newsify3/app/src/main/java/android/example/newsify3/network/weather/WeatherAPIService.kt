package android.example.newsify3.network.weather

import android.example.newsify3.models.weather.WeatherData
import android.example.newsify3.network.RetroInstance
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "ef608426d3224246a7571540241305"

//http://api.weatherapi.com/v1/current.json?key=ef608426d3224246a7571540241305&q=Chennai&aqi=no

private const val BASE_URL = "http://api.weatherapi.com/v1/"

private val retrofit = RetroInstance.getRetroInstance(BASE_URL)

interface WeatherAPIServiceInterface {
    @GET("current.json")
    fun getWeatherByCity(@Query("key") key: String = API_KEY, @Query("q") city: String, @Query("aqi") aqi: String): retrofit2.Call<WeatherData>
}

object WeatherAPIService {
    val retrofitService: WeatherAPIServiceInterface by lazy {
        retrofit.create(WeatherAPIServiceInterface::class.java)
    }
}