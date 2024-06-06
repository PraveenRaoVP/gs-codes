package android.caged.notes.presentation.sign_in

import android.app.Activity
import android.caged.notes.data.ext.basicButton
import android.caged.notes.data.ext.fieldModifier
import android.caged.notes.data.ext.textButton
import android.caged.notes.presentation.common.BasicButton
import android.caged.notes.presentation.common.BasicTextButton
import android.caged.notes.presentation.common.BasicToolbar
import android.caged.notes.presentation.common.EmailField
import android.caged.notes.presentation.common.PasswordField
import android.caged.notes.presentation.navigation.Route
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import android.caged.notes.R.string as AppText

@Composable
fun SignInScreen(
    openAndPopUp : (String, String) -> Unit,
    viewModel: SignInViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState
    val activity = LocalContext.current as Activity

    SignInScreenContent(
        uiState = uiState,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onGoogleSignInClick = { viewModel.onGoogleSignInClick(activity) },
        onSignInClick = { viewModel.onSignInClick(openAndPopUp) },
        onForgotPasswordClick = viewModel::onForgotPasswordClick,
        onSignUpClick = { openAndPopUp(Route.SignUpScreen.route, Route.SignInScreen.route)}
    )
}

@Composable
fun SignInScreenContent(
    modifier: Modifier = Modifier,
    uiState: SignInState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onGoogleSignInClick: () -> Unit,
    onSignInClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onSignUpClick: () -> Unit
) {
    BasicToolbar(title = AppText.login_details)
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { onGoogleSignInClick }, modifier =
        Modifier
            .padding(16.dp)
        ) {
            Text("Sign In With Google")
        }
        Text(text = "or", style = MaterialTheme.typography.bodySmall)

        EmailField(uiState.email!!, onEmailChange, Modifier.fieldModifier())
        PasswordField(uiState.password!!, onPasswordChange, Modifier.fieldModifier())

        BasicButton(AppText.sign_in, Modifier.basicButton()) { onSignInClick() }

        BasicTextButton(AppText.forgot_password, Modifier.textButton()) {
            onForgotPasswordClick()
        }

        BasicTextButton(AppText.new_user_text, Modifier.textButton()) {
            onSignUpClick()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignInScreenPreview() {
    SignInScreenContent(
        uiState = SignInState(),
        onEmailChange = {},
        onPasswordChange = {},
        onGoogleSignInClick = {},
        onSignInClick = {},
        onForgotPasswordClick = {},
        onSignUpClick = {}
    )
}