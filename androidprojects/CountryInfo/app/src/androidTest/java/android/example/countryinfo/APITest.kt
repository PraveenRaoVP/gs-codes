package android.example.countryinfo

import android.example.countryinfo.network.CountryAPI
import android.example.countryinfo.network.CountryAPIService
import android.example.countryinfo.network.CountryProperty
import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@RunWith(AndroidJUnit4::class)
class APITest{

    // test if api is working for a request
    @Test
    fun testApi() {
        val countryApiService: CountryAPIService = CountryAPI.retrofitService
        val call = countryApiService.getCountryDetailsByName("India")
        call.enqueue(object : Callback<CountryProperty> {
            override fun onResponse(
                call: Call<CountryProperty>,
                response: Response<CountryProperty>
            ) {
                if (response.isSuccessful) {
                    val countryProperty = response.body()
                    Assert.assertNotNull(countryProperty)
                    Log.i("NewCountryViewModel", "CountryProperty: $countryProperty")
//                    _countryDetails.value = countryProperty
//                    _navigateToCountry.value = true
                } else {
                    Log.i("NewCountryViewModel", "Error: ${response.errorBody()}")
//                    _showSnackBarEvent.value = true
                }
            }

            override fun onFailure(call: Call<CountryProperty>, t: Throwable) {
                Log.i("NewCountryViewModel", "Failure: ${t.message}")
//                _showSnackBarEvent.value = true
            }
        })
    }

}