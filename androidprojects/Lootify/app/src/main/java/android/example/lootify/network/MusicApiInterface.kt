package android.example.lootify.network

import android.example.lootify.data.Songs
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MusicApiInterface {
    @Headers("X-RapidAPI-Key: 48c1975001msh3c43ea9cfe9db58p108103jsn7255e4effd62",
            "X-RapidAPI-Host: deezerdevs-deezer.p.rapidapi.com"
    )
    @GET("search")
    fun getData(@Query("q") query : String) : Call<List<Songs>>
}