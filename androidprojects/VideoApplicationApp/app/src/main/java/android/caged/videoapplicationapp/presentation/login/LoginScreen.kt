package android.caged.videoapplicationapp.presentation.login

import android.caged.videoapplicationapp.presentation.common.EmailField
import android.caged.videoapplicationapp.presentation.common.PasswordField
import android.caged.videoapplicationapp.ui.theme.VideoApplicationAppTheme
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onNavigate: (String, String, String) -> Unit
) {
    var uiState by viewModel.uiState
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EmailField(value = uiState.email, onNewValue = viewModel::onEmailChange)
        Spacer(modifier = Modifier.height(16.dp))
        PasswordField(value = uiState.password, onNewValue = viewModel::onPasswordChange)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { viewModel.onClick(onNavigate) }) {
            Text("Login")
        }
        if(uiState.error?.isNotEmpty() == true) {
            Toast.makeText(LocalContext.current, uiState.error, Toast.LENGTH_SHORT).show()
            uiState.error = null
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    VideoApplicationAppTheme {
        LoginScreen(
            viewModel = hiltViewModel(),
            onNavigate = { _, _, _ -> }
        )
    }
}