package android.example.countryinfo.models

import android.example.countryinfo.utils.ListStringConverter
import android.example.countryinfo.utils.MapStringStringConverter
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "country_details")
@TypeConverters(MapStringStringConverter::class, ListStringConverter::class)
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
    var languagesSpoken: Map<String, String>,
    @ColumnInfo(name = "country_currency")
    var countryCurrencies: Map<String, String>,
    @ColumnInfo(name = "country_continent")
    var countryContinents: List<String>
)