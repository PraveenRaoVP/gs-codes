package android.caged.notesapplication.presentation.note_detail

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun NoteDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: NoteDetailViewModel = hiltViewModel()
) {
    var uiState = viewModel.uiState

    NoteDetailScreenContent(

    )
}

@Composable
fun NoteDetailScreenContent() {

}