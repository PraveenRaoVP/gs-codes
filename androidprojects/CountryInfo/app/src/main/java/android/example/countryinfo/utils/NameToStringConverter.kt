package android.example.countryinfo.utils

import android.example.countryinfo.network.Name
import androidx.room.TypeConverter
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

class NameToStringConverter {
    @TypeConverter
    fun fromName(name: Name): String? {
        return name.common
    }

    @TypeConverter
    fun toName(name: String): Name {
        return Name(common = name, official = name, nativeName = null)
    }
}