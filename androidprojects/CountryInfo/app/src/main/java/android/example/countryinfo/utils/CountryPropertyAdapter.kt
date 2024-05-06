package android.example.countryinfo.utils

import android.example.countryinfo.network.CountryProperty
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

class CountryPropertyAdapter {
    @FromJson
    fun fromJson(countryPropertyJson: Map<String, Any?>): CountryProperty {
        val name = countryPropertyJson["name"] as Map<String, String>
        val capital = countryPropertyJson["capital"] as List<String>
        val flag = countryPropertyJson["flags"] as Map<String, String>
        val population = countryPropertyJson["population"] as Long
        val languages = countryPropertyJson["languages"] as Map<String, String>
        val currenciesJson = countryPropertyJson["currencies"] as Map<String, Map<String, Any>>
        val currencies = currenciesJson.mapValues { (_, value) ->
            (value as Map<String, String>).toMap()
        }
        val continents = countryPropertyJson["continents"] as List<String>

        return CountryProperty(name, capital, flag, population, languages, currencies, continents)
    }

    @ToJson
    fun toJson(countryProperty: CountryProperty): Map<String, Any?> {
        return mapOf(
            "name" to countryProperty.name,
            "capital" to countryProperty.capital,
            "flags" to countryProperty.flag,
            "population" to countryProperty.population,
            "languages" to countryProperty.languages,
            "currencies" to countryProperty.currencies,
            "continents" to countryProperty.continents
        )
    }
}