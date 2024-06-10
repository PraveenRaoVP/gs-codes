package android.caged.notesapplication.presentation.note_detail.components

import android.caged.notesapplication.R
import android.caged.notesapplication.presentation.note_detail.NoteDetailViewModel
import android.caged.notesapplication.ui.theme.NotesApplicationTheme
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDetailToolbar(
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit,
    onHideClicked: () -> Unit,
    onDeleteClicked: () -> Unit,
    onCompleteClicked: () -> Unit,
    viewModel: NoteDetailViewModel
) {
    TopAppBar(title = { Text("") },
        modifier = modifier.background(MaterialTheme.colorScheme.surface),
        actions = {
            // back, hide, delete and tick icons
            Box(modifier) {
                Row(modifier = modifier.fillMaxWidth()) {
                    IconButton(onClick = onBackClicked) {
                        Icon(painter = painterResource(id = R.drawable.arrow_back_24px), contentDescription = "Back")
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    if(viewModel.isHidden) {
                        IconButton(onClick = onHideClicked) {
                            Icon(painter = painterResource(id = R.drawable.visibility_off_24px), contentDescription = "Visible")
                        }
                    } else {
                        IconButton(onClick = onHideClicked) {
                            Icon(painter = painterResource(id = R.drawable.visibility_24px), contentDescription = "Hidden")
                        }
                    }
                    IconButton(onClick = onDeleteClicked) {
                        Icon(painter = painterResource(id = R.drawable.delete_24px), contentDescription = "Delete")
                    }
                    IconButton(onClick = onCompleteClicked) {
                        Icon(painter = painterResource(id = R.drawable.check_24px), contentDescription = "Complete")
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun NoteDetailToolbarPreview() {
    NotesApplicationTheme {
        NoteDetailToolbar(
            onBackClicked = {},
            onHideClicked = {},
            onDeleteClicked = {},
            onCompleteClicked = {},
            viewModel = hiltViewModel()
        )
    }
}