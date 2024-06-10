package android.caged.notesapplication

import android.caged.notesapplication.presentation.snackbar.SnackbarManager
import android.caged.notesapplication.presentation.snackbar.SnackbarMessage.Companion.toMessage
import android.content.res.Resources
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Stable
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Stable
class NotesApplicationState @OptIn(ExperimentalMaterial3Api::class) constructor(
    val scaffoldState: BottomSheetScaffoldState,
    private val snackbarManager: SnackbarManager,
    private val resources: Resources,
    coroutineScope: CoroutineScope
) {

    init {
        coroutineScope.launch {
            snackbarManager.snackbarMessages.filterNotNull().collect { snackbarMessage ->
                val text = snackbarMessage.toMessage(resources)
                scaffoldState.snackbarHostState.showSnackbar(text)
                snackbarManager.clearSnackbarState()
            }
        }
    }
}