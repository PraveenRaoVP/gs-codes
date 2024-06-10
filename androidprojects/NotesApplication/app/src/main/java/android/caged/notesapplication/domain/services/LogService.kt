package android.caged.notesapplication.domain.services

interface LogService {
    fun logNonFatalCrash(throwable: Throwable)
}