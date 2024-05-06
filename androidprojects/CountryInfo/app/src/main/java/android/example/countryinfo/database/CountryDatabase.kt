package android.example.countryinfo.database

import android.content.Context
import android.example.countryinfo.models.CountryDetails
import android.example.countryinfo.utils.ListStringConverter
import android.example.countryinfo.utils.MapStringStringConverter
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [CountryDetails::class], version = 1, exportSchema = false)
@TypeConverters(MapStringStringConverter::class, ListStringConverter::class)
abstract class CountryDatabase : RoomDatabase() {
    abstract fun countryDatabaseDao(): CountryDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: CountryDatabase? = null

        fun getInstance(context: Context) : CountryDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = androidx.room.Room.databaseBuilder(
                        context.applicationContext,
                        CountryDatabase::class.java,
                        "country_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}