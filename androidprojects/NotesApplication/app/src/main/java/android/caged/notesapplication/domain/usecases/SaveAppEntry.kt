package android.caged.notesapplication.domain.usecases

import android.caged.notesapplication.domain.manager.LocalUserManager

class SaveAppEntry(private val localUserManager: LocalUserManager) {
    suspend operator fun invoke() {
        localUserManager.saveAppEntry()
    }
}