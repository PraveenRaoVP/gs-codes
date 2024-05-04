package android.example.countryinfo.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "country_details")
data class CountryDetails (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "country_name")
    var countryName: String,
    @ColumnInfo(name = "country_capital")
    var countryCapital: String,
    @ColumnInfo(name = "flag_image_url")
    var flagImageUrl: String,
    @ColumnInfo(name = "country_population")
    var countryPopulation: Long,
    @ColumnInfo(name = "languages_spoken")
    var languagesSpoken: List<Map<String, String>>,
    @ColumnInfo(name = "country_currency")
    var countryCurrency: String,
    @ColumnInfo(name = "country_continent")
    var countryContinents: List<String>
)

/*
   @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    @ColumnInfo(name = "country_name")
    var countryName: String = ""
    @ColumnInfo(name = "country_capital")
    var countryCapital: String = ""
    @ColumnInfo(name = "flag_image")
    var flagImage: String = ""
    @ColumnInfo(name = "country_population")
    var countryPopulation: String = ""
    @ColumnInfo(name = "languages_spoken")
    var languagesSpoken: String = ""
    @ColumnInfo(name = "country_currency")
    var countryCurrency: String = ""
    @ColumnInfo(name = "country_continent")
    var countryContinent: String = ""
 */