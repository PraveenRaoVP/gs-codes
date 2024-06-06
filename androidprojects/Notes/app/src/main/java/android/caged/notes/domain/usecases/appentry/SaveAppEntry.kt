package android.caged.notes.domain.usecases.appentry

import android.caged.notes.domain.manager.LocalUserManager

class SaveAppEntry(
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke() {
        localUserManager.saveAppEntry() // Save the app entry value to the DataStore.
    }
}