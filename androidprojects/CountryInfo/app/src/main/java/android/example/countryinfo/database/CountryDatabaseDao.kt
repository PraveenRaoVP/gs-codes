package android.example.countryinfo.database

import android.example.countryinfo.models.CountryDetails
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CountryDatabaseDao {
    @Insert
    suspend fun insert(countryDetails: CountryDetails)

    @Query("SELECT * FROM country_details ORDER BY id DESC")
    suspend fun getAllCountryDetails(): List<CountryDetails>

    @Query("SELECT * FROM country_details WHERE country_name = :countryName")
    suspend fun getCountryDetailsByName(countryName: String): CountryDetails

    @Query("SELECT * FROM country_details WHERE id = :id")
    suspend fun getCountryDetailsById(id: Int): CountryDetails

    @Query("DELETE FROM country_details")
    suspend fun deleteAll()
}