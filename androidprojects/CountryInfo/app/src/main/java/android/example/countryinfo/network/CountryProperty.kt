package android.example.countryinfo.network

import androidx.room.ColumnInfo
import com.squareup.moshi.Json

data class CountryProperty(
    val name: Name,
    val capital: List<String>,
    val flags: Flag,
    val population: Long,
    val languages: Map<String, String>?,
    val currencies: Map<String, Map<String, String>>?,
    val continents: List<String>
) {
    val commonName: String
        get() = name.common ?: ""

    val officialName: String
        get() = name.official ?: ""

    val flagImageUrl: String?
        get() = flags.png ?: ""
}

data class Name(
    @Json(name = "common") val common: String?,
    @Json(name = "official") val official: String?,
    val nativeName: Map<String, Map<String, String>>?
)

data class Flag(
    val svg: String?,
    val png: String?,
    val alt: String?,
)