package android.caged.notesapplication.presentation.note_detail

import android.caged.notesapplication.presentation.common.NoOutlineField
import android.caged.notesapplication.presentation.note_detail.components.NoteDetailToolbar
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import android.caged.notesapplication.R.string as AppText

@Composable
fun NoteDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: NoteDetailViewModel,
    onNavigate: (String, String) -> Unit,
    noteId: String
) {
    var uiState by viewModel.uiState

    uiState.id = noteId

    NoteDetailScreenContent(
        uiState = uiState,
        viewModel = viewModel,
        onNavigate = onNavigate,
        noteId = noteId
    )
}

@Composable
fun NoteDetailScreenContent(
    uiState: NoteDetailUiState,
    viewModel: NoteDetailViewModel,
    onNavigate: (String, String) -> Unit,
    noteId: String
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        NoteDetailToolbar(
            onBackClicked = { viewModel.onBackClicked(onNavigate) },
            onHideClicked = { viewModel.onEvent(NoteDetailEvent.ToggleVisibility) },
            onDeleteClicked = { viewModel.onDeleteClicked(noteId = noteId, onDeleted = onNavigate) },
            onCompleteClicked = { viewModel.onComplete(onNavigate = onNavigate) },
            viewModel = viewModel
        )
        NoOutlineField(text = AppText.title, value = uiState.title, onNewValue = viewModel::onTitleChange)
        NoOutlineField(text = AppText.content, value = uiState.content, onNewValue = viewModel::onContentChange)
    }
}

@Preview(showBackground = true)
@Composable
fun NoteDetailScreenPreview() {
    NoteDetailScreen(
        onNavigate = { _, _ -> },
        noteId = "1",
        viewModel = hiltViewModel()
    )
}