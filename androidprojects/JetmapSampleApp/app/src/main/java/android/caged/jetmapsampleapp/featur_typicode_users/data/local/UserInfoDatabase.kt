package android.caged.jetmapsampleapp.featur_typicode_users.data.local

import android.caged.jetmapsampleapp.featur_typicode_users.data.local.Converters
import android.caged.jetmapsampleapp.featur_typicode_users.data.local.UserInfoDao
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import android.caged.jetmapsampleapp.featur_typicode_users.data.local.entity.UserInfoEntity

@Database(
    entities = [UserInfoEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class UserInfoDatabase: RoomDatabase() {
    abstract val dao: UserInfoDao

}