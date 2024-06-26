package android.caged.jogfit.presentation.auth.login

import android.caged.jogfit.R
import android.caged.jogfit.presentation.auth.components.ActionBar
import android.caged.jogfit.presentation.navigation.Routes
import android.caged.jogfit.ui.theme.JogFitTheme
import android.caged.jogfit.presentation.common.BasicButton
import android.caged.jogfit.presentation.common.BasicTextButton
import android.caged.notesapplication.presentation.common.EmailField
import android.caged.notesapplication.presentation.common.PasswordField
import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun LoginAlternate(
    state: LoginUiState,
    onEvent : (LoginEvent, Context, (String, String) -> Unit) -> Unit,
    navigateTo: (String) -> Unit,
    navigateToPopUp: (String, String) -> Unit,
) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current


    Scaffold(
        topBar = {
            ActionBar(
                title = "Sign In",
                onBackClick = {
                    navigateToPopUp(
                        Routes.LoginWithGoogleRoute.route,
                        Routes.LoginWithEmailPasswordRoute.route
                    )
                },
                isBackEnabled = true
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = innerPadding.calculateTopPadding()),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Sign In With Email And Password")
                Spacer(modifier = Modifier.height(16.dp))
                EmailField(
                    value = state.email,
                    onNewValue = { onEvent(LoginEvent.EmailChanged(it), context) { _, _ -> } },
                    isError = state.isError
                )
                Spacer(modifier = Modifier.height(16.dp))
                PasswordField(
                    value = state.password,
                    onNewValue = { password -> onEvent(LoginEvent.PasswordChanged(password), context) { _, _ -> } },
//                    modifier = Modifier.onFocusEvent {
//                        viewModel.uiState.value = uiState.copy(isError = false)
//                    }
                    isError = state.isError
                )
                Spacer(modifier = Modifier.height(16.dp))
                BasicButton(
                    text = R.string.sign_in,
                    modifier = Modifier,
                    action = {
                        focusManager.clearFocus()
                        onEvent(LoginEvent.SignInEvent, context, navigateToPopUp)
                    }
                )
                if (state.isError) {
                    Text(
                        text = state.errorMessage,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                BasicTextButton(
                    text = R.string.sign_up,
                    modifier = Modifier.height(16.dp),
                    onClick = { navigateTo(Routes.SignUpRoute.route) }
                )
                BasicTextButton(
                    text = R.string.forgot_password_,
                    modifier = Modifier.height(16.dp),
                    onClick = { navigateTo(Routes.ForgotPassword.route) }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun LoginAlternatePreview() {
    JogFitTheme {
        LoginAlternate(
            onEvent = { _,_,_ -> },
            navigateTo = {},
            navigateToPopUp = { _, _ -> },
            state = LoginUiState(),
            )
    }
}