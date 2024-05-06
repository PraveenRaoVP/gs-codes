package android.example.countryinfo.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MapStringMapConverter {
    @TypeConverter
    fun fromString(value: String): Map<String, Map<String, String>> {
        val mapType = object : TypeToken<Map<String, Map<String, String>>>() {}.type
        return Gson().fromJson(value, mapType)
    }

    @TypeConverter
    fun fromMap(map: Map<String, Map<String, String>>): String {
        return Gson().toJson(map)
    }
}