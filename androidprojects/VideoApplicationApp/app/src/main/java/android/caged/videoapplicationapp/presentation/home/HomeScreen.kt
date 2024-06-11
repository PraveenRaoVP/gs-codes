package android.caged.videoapplicationapp.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreen(
    username: String,
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigate: (String, String) -> Unit
) {
    viewModel.username = username
    Box(modifier = Modifier.fillMaxSize()) {

    }
}
