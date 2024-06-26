package android.caged.jogfit.presentation.auth.signup

import android.caged.jogfit.R
import android.caged.jogfit.presentation.auth.components.ActionBar
import android.caged.jogfit.presentation.common.BasicButton
import android.caged.jogfit.presentation.navigation.Routes
import android.caged.jogfit.ui.theme.JogFitTheme
import android.caged.notesapplication.presentation.common.EmailField
import android.caged.notesapplication.presentation.common.PasswordField
import android.caged.notesapplication.presentation.common.RepeatPasswordField
import android.caged.notesapplication.presentation.common.UsernameField
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SignUpScreen(
    state: SignUpUiState,
    onEvent: (SignUpEvent, Context, (String, String)->Unit) -> Unit,
    navigatePopUp: (String, String) -> Unit
) {


    Scaffold(
        topBar = {
            ActionBar(
                title = "Sign Up",
                onBackClick = {
                    navigatePopUp(
                        Routes.LoginWithEmailPasswordRoute.route,
                        Routes.SignUpRoute.route
                    )
                },
                isBackEnabled = true
            )
        }
    ) { innerPadding ->
        println(innerPadding)

        val focusManager = LocalFocusManager.current
        val context = LocalContext.current

        Surface(modifier = Modifier.padding(innerPadding)) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Enter the following details")
                Spacer(modifier = Modifier.height(16.dp))
                UsernameField(
                    value = state.username,
                    onNewValue = { onEvent(SignUpEvent.UsernameChanged(it), context, { _, _ -> }) },
                    isError = state.isUsernameError,
                    modifier = Modifier.onFocusChanged {
                        onEvent(SignUpEvent.ClearAllErrors, context, { _, _ -> })
                    }
                )
                if (state.isUsernameError) {
                    Text(
                        text = state.usernameError,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.error
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                EmailField(
                    value = state.email,
                    onNewValue = { onEvent(SignUpEvent.EmailChanged(it), context, { _, _ -> }) },
                    isError = state.isEmailError,
                    modifier = Modifier.onFocusChanged {
                        onEvent(SignUpEvent.ClearAllErrors, context, { _, _ -> })
                    }
                )
                if (state.isEmailError) {
                    Text(
                        text = state.emailError,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.error
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                PasswordField(
                    value = state.password,
                    onNewValue = { onEvent(SignUpEvent.PasswordChanged(it), context, { _, _ -> }) },
                    isError = state.isPasswordError,
                    modifier = Modifier.onFocusChanged {
                        onEvent(SignUpEvent.ClearAllErrors, context, { _, _ -> })
                    }
                )
                if (state.isPasswordError) {
                    Text(
                        text = state.passwordError,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                RepeatPasswordField(
                    value = state.repeatPassword,
                    onNewValue = { onEvent(SignUpEvent.ConfirmPasswordChanged(it), context, { _, _ -> }) },
                    modifier = Modifier.onFocusChanged {
                        onEvent(SignUpEvent.ClearAllErrors, context, { _, _ -> })
                    }
                )
                if (state.isRepeatPasswordError) {
                    Text(
                        text = state.repeatPasswordError,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.error
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                BasicButton(text = R.string.sign_up_label, modifier = Modifier, action = {
                    focusManager.clearFocus()
                    onEvent(SignUpEvent.OnSignUpClick, context, navigatePopUp)
                })
            }
        }
    }
}

@Preview
@Composable
fun SignUpScreenPreview() {
    JogFitTheme {
        SignUpScreen(
            state = SignUpUiState(),
            onEvent = { _, _, _ -> },
            navigatePopUp = { _, _ -> }
        )
    }
}