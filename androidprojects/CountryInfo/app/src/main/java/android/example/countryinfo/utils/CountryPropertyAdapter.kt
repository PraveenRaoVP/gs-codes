package android.example.countryinfo.utils

import android.example.countryinfo.network.CountryProperty
import android.example.countryinfo.network.Flag
import android.example.countryinfo.network.Name
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

class CountryPropertyAdapter {
    @FromJson
    fun fromJson(countryPropertyJson: Map<String, Any?>): CountryProperty {
        val nameJson = countryPropertyJson["name"] as Map<String, String>
        val name = Name(
            common = nameJson["common"],
            official = nameJson["official"],
            nativeName = nameJson["nativeName"] as Map<String, Map<String, String>>?
        )
        val capital = countryPropertyJson["capital"] as List<String>
        val flagJson = countryPropertyJson["flags"] as Map<String, String>
        val flag = Flag(
            png = flagJson["png"],
            svg = flagJson["svg"],
            alt = flagJson["alt"]
        )
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
            "flags" to mapOf(
                "png" to countryProperty.flags.png,
                "svg" to countryProperty.flags.svg,
                "alt" to countryProperty.flags.alt
            ),
            "population" to countryProperty.population,
            "languages" to countryProperty.languages,
            "currencies" to countryProperty.currencies,
            "continents" to countryProperty.continents
        )
    }
}

