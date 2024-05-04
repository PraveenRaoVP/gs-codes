package android.example.country.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://restcountries.com/v3.1/"

private val retroFit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface CountryAPIService {
    @GET("name/{name}")
    suspend fun getCountryByName(@Path("name") name: String): String
}

object CountryAPI {
        val retrofitService: CountryAPIService = retroFit.create(CountryAPIService::class.java)
}
