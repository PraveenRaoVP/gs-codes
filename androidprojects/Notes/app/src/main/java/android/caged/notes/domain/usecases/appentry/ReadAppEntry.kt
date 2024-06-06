package android.caged.notes.domain.usecases.appentry

import android.caged.notes.domain.manager.LocalUserManager

class ReadAppEntry(
    private val localUserManager: LocalUserManager
) {
    operator fun invoke() = localUserManager.readAppEntry()
}
