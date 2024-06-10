package android.caged.notesapplication.presentation.sign_in

import android.caged.notesapplication.ext.textButton
import android.caged.notesapplication.presentation.common.BasicTextButton
import android.caged.notesapplication.presentation.common.EmailField
import android.caged.notesapplication.presentation.common.PasswordField
import android.caged.notesapplication.presentation.navigation.Routes
import android.caged.notesapplication.ui.theme.NotesApplicationTheme
import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import android.caged.notesapplication.R.string as AppText
import android.caged.notesapplication.R.drawable as AppIcon

@Composable
fun SignInScreen(
    navigateAndPopUp: (String, String) -> Unit,
    viewModel: SignInViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState
    val context = LocalContext.current
    SignInScreenContent(
        uiState = uiState,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onGoogleSignIn = { viewModel.onGoogleSignIn(context, navigateAndPopUp) },
        onSignInClick = { viewModel.onSignInClick(navigateAndPopUp)  },
        onForgotPasswordClick = viewModel::onForgotPasswordClick,
        onSignUpClick = { navigateAndPopUp(Routes.SignUpRoute.route, Routes.SignInRoute.route) },
        emptyErrorMessage = viewModel::emptyErrorMessage
    )
}

@Composable
fun SignInScreenContent(
    uiState: SignInUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onGoogleSignIn: () -> Unit,
    onSignInClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onSignUpClick: () -> Unit,
    emptyErrorMessage: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { onGoogleSignIn() }) {
//                Image(painter = painterResource(id = AppIcon.google_icon), contentDescription = null)
                Text(stringResource(id = AppText.sign_in_with_google), style = MaterialTheme.typography.bodyMedium)
            }
            Spacer(modifier = Modifier.height(16.dp))
            EmailField(value = uiState.email, onNewValue = onEmailChange)
            Spacer(modifier = Modifier.height(12.dp))
            PasswordField(value = uiState.password, onNewValue = onPasswordChange)
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = { onSignInClick() }) {
                if(uiState.isLoading) {
                    CircularProgressIndicator()
                } else {
                    Text(stringResource(id = AppText.sign_in), style = MaterialTheme.typography.bodyMedium)
                }
            }
            BasicTextButton(AppText.forgot_password, Modifier.textButton()) {
                onForgotPasswordClick()
            }

            BasicTextButton(AppText.new_user, Modifier.textButton()) {
                onSignUpClick()
            }
            if(uiState.errorMessage != "") {
                Toast.makeText(LocalContext.current, uiState.errorMessage, Toast.LENGTH_SHORT).show()
                emptyErrorMessage()
            }

        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun SignInScreenPreview() {
    NotesApplicationTheme {
        SignInScreenContent(
            uiState = SignInUiState(),
            onEmailChange = {},
            onPasswordChange = {},
            onGoogleSignIn = {},
            onSignInClick = {},
            onForgotPasswordClick = {},
            onSignUpClick = {},
            emptyErrorMessage = {}
        )
    }
}