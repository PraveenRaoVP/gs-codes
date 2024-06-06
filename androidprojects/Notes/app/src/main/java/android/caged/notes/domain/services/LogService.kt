package android.caged.notes.domain.services

interface LogService {
    fun logNonFatalCrash(throwable: Throwable)
}