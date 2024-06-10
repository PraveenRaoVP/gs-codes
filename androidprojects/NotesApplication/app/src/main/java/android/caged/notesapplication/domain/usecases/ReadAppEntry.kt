package android.caged.notesapplication.domain.usecases

import android.caged.notesapplication.domain.manager.LocalUserManager

class ReadAppEntry(private val localUserManager: LocalUserManager) {
    operator fun invoke() = localUserManager.readAppEntry()
}