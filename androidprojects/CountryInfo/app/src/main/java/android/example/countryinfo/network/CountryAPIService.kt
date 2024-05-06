package android.example.countryinfo.network

import android.example.countryinfo.utils.CountryPropertyAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://restcountries.com/v3.1/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .add(CountryPropertyAdapter())
    .build()

private val retroFit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface CountryAPIService {
    @GET("name/{name}")
    fun getCountryDetailsByName(@Path("name") name: String): Call<CountryProperty>
}

object CountryAPI {
    val retrofitService: CountryAPIService by lazy {
        retroFit.create(CountryAPIService::class.java)
    }
}
