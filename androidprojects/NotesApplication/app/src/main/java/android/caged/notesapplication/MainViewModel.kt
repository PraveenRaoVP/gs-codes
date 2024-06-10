package android.caged.notesapplication

import android.caged.notesapplication.domain.services.LogService
import android.caged.notesapplication.presentation.snackbar.SnackbarManager
import android.caged.notesapplication.presentation.snackbar.SnackbarMessage.Companion.toSnackbarMessage
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class MainViewModel @Inject constructor(
    private val logService: LogService
): ViewModel(){
        fun launchCatching(snackbar: Boolean = true, block: suspend CoroutineScope.() -> Unit) =
            viewModelScope.launch(
                CoroutineExceptionHandler { _, throwable ->
                    if (snackbar) {
                        SnackbarManager.showMessage(throwable.toSnackbarMessage())
                    }
                    logService.logNonFatalCrash(throwable)
                },
                block = block
            )
}