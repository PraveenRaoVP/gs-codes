package android.example.countryinfo.network

import android.example.countryinfo.utils.CountryPropertyAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private val BASE_URL = "https://restcountries.com/v3.1/"


private val retroFit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface CountryAPIService {
    @GET("name/{name}?fullText=true&fields=name,capital,flags,population,languages,currencies,continents")
    fun getCountryDetailsByName(@Path("name") name: String): Call<List<CountryProperty>> // Change return type
}

object CountryAPI {
    val retrofitService: CountryAPIService by lazy {
        retroFit.create(CountryAPIService::class.java)
    }
}
