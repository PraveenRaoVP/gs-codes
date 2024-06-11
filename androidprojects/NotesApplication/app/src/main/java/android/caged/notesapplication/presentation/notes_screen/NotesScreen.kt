package android.caged.notesapplication.presentation.notes_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun NotesScreen(
    viewModel: NotesViewModel = hiltViewModel(),
    navigateTo: (String) -> Unit,
    navigateAndPopUp: (String, String) -> Unit
) {
    var uiState by viewModel.uiState
    Surface(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                android.caged.notesapplication.presentation.common.SearchBar(
                    text = uiState.searchQuery,
                    onSearch = { viewModel::onEvent },
                    onValueChange = viewModel::onSearchQueryChange,
                    onClear = { viewModel.onSearchQueryChange("") },
                    readOnly = false,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        ) {innerPadding ->
            Surface(modifier = Modifier.padding(innerPadding)) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    if (uiState.isLoading) {
                        CircularProgressIndicator()
                    } else {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(uiState.notes) { note ->
                                Column {
                                    Text(text = note.title)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotesScreenPreview() {
    NotesScreen(
        navigateTo = {},
        navigateAndPopUp = { _, _ -> }
    )
}