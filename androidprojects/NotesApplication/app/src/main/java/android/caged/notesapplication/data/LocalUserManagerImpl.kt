package android.caged.notesapplication.data

import android.caged.notesapplication.domain.manager.LocalUserManager
import android.caged.notesapplication.utils.Constants
import android.caged.notesapplication.utils.Constants.USER_SETTINGS
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore

class LocalUserManagerImpl(private val context: Context) : LocalUserManager {
    override suspend fun saveAppEntry() {
        context.dataStore.edit {settings ->
            settings[PreferencesKeys.APP_ENTRY] = true
        }
    }

    // Read the app entry value from the DataStore. If the value is not found, return false.
    override fun readAppEntry(): Flow<Boolean> {
        return context.dataStore.data.map { settings ->
            settings[PreferencesKeys.APP_ENTRY] ?: false
        }
    }
}

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_SETTINGS)

private object PreferencesKeys {
    val APP_ENTRY = booleanPreferencesKey(Constants.APP_ENTRY)
}