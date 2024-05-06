package android.example.countryinfo.network

import com.squareup.moshi.Json

data class CountryProperty(
    val name: Map<String, String>,
    val capital: List<String>,
    @Json(name = "flags") val flag: Map<String, String>,
    val population: Long,
    val languages: Map<String, String>?,
    val currencies: Map<String, Map<String, String>>?, // Change to Map<String, Map<String, String>>
    val continents: List<String>
) {
    val commonName: String
        get() = name["common"] ?: ""

    val officialName: String
        get() = name["official"] ?: ""

    val flagImageUrl: String
        get() = flag["svg"] ?: ""
}