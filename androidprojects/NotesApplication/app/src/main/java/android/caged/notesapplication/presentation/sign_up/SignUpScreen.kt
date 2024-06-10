package android.caged.notesapplication.presentation.sign_up

import android.caged.notesapplication.R
import android.caged.notesapplication.ext.textButton
import android.caged.notesapplication.presentation.common.BasicTextButton
import android.caged.notesapplication.presentation.common.EmailField
import android.caged.notesapplication.presentation.common.PasswordField
import android.caged.notesapplication.presentation.common.RepeatPasswordField
import android.caged.notesapplication.presentation.navigation.Routes
import android.caged.notesapplication.ui.theme.NotesApplicationTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
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
fun SignUpScreen(
    navigateAndPopUp: (String, String) -> Unit,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    var uiState by viewModel.uiState

    SignUpScreenContent(
        uiState = uiState,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onRepeatPasswordChange = viewModel::onRepeatPasswordChange,
        onSignUpClick = { viewModel.onSignUpClick(navigateAndPopUp) },
        onSignInClick = { navigateAndPopUp(Routes.SignInRoute.route, Routes.SignUpRoute.route) }
    )
}

@Composable
fun SignUpScreenContent(
    uiState: SignUpUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onRepeatPasswordChange: (String) -> Unit,
    onSignUpClick: () -> Unit,
    onSignInClick: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            EmailField(value = uiState.email, onNewValue = onEmailChange)
            Spacer(modifier = Modifier.height(16.dp))

            PasswordField(value = uiState.password, onNewValue = onPasswordChange)
            Spacer(modifier = Modifier.height(16.dp))

            RepeatPasswordField(value = uiState.repeatPassword, onNewValue = onRepeatPasswordChange)
            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = onSignUpClick) {
                Text("Sign Up", style = MaterialTheme.typography.bodyMedium)
            }
            Spacer(modifier = Modifier.height(16.dp))
            BasicTextButton(R.string.sign_in_nav, Modifier.textButton()) {
                onSignInClick()
            }
            Spacer(modifier = Modifier.height(16.dp))

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview
@Composable
fun PreviewSignUpScreen() {
    NotesApplicationTheme {
        SignUpScreenContent(
            uiState = SignUpUiState(),
            onEmailChange = {},
            onPasswordChange = {},
            onRepeatPasswordChange = {},
            onSignUpClick = {},
            onSignInClick = {}
        )
    }
}