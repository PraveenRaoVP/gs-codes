package android.example.countryinfo.utils

import android.example.countryinfo.network.Flag
import androidx.room.TypeConverter

class FlagToStringConverter {
    @TypeConverter
    fun fromFlag(flag: Flag): String? {
        return flag.png
    }

    @TypeConverter
    fun toFlag(flag: String): Flag {
        return Flag(svg = flag, png = flag, alt = flag)
    }
}