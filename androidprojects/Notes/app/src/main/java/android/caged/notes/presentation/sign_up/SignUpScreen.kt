package android.caged.notes.presentation.sign_up

import android.app.Activity
import android.caged.notes.R
import android.caged.notes.data.ext.basicButton
import android.caged.notes.data.ext.fieldModifier
import android.caged.notes.data.ext.textButton
import android.caged.notes.presentation.common.BasicButton
import android.caged.notes.presentation.common.BasicTextButton
import android.caged.notes.presentation.common.BasicToolbar
import android.caged.notes.presentation.common.EmailField
import android.caged.notes.presentation.common.PasswordField
import android.caged.notes.presentation.common.RepeatPasswordField
import android.caged.notes.presentation.navigation.Route
import android.caged.notes.presentation.sign_in.SignInState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import android.caged.notes.R.string as AppText

@Composable
fun SignUpScreen(
    openAndPopUp : (String, String) -> Unit,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState

    SignUpScreenContent(
        uiState = uiState,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onRepeatPasswordChange = viewModel::onRepeatPasswordChange,
        onSignUpClick = { viewModel.onSignUpClick(openAndPopUp) },
        onSignInClick = { openAndPopUp(Route.SignInScreen.route, Route.SignUpScreen.route)}
    )
}

@Composable
fun SignUpScreenContent(
    modifier: Modifier = Modifier,
    uiState: SignUpState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onRepeatPasswordChange: (String) -> Unit,
    onSignUpClick: () -> Unit,
    onSignInClick: () -> Unit
) {
    BasicToolbar(title = R.string.login_details)
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EmailField(uiState.email, onEmailChange, Modifier.fieldModifier())
        PasswordField(uiState.password, onPasswordChange, Modifier.fieldModifier())
        RepeatPasswordField(uiState.repeatPassword, onRepeatPasswordChange, Modifier.fieldModifier())

        BasicButton(AppText.create_account, Modifier.basicButton()) {
            onSignUpClick()
        }

        BasicTextButton(AppText.nav_to_sign_in, Modifier.textButton()) {
            onSignInClick()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    SignUpScreenContent(
        uiState = SignUpState(),
        onEmailChange = {},
        onPasswordChange = {},
        onRepeatPasswordChange = {},
        onSignUpClick = {},
        onSignInClick = {}
    )
}