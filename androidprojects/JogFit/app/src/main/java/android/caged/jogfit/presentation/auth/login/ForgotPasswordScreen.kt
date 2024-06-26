package android.caged.jogfit.presentation.auth.login

import android.caged.notesapplication.presentation.common.EmailField
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@Composable
fun ForgotPasswordScreen(
    state: LoginUiState,
    onEvent: (LoginEvent, Context, (String, String) -> Unit) -> Unit,
    navigateToPopUp: (String, String) -> Unit
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(text = "Enter your email address to reset your password")
        EmailField(
            value = state.email,
            onNewValue = { onEvent(LoginEvent.EmailChanged(it), context, { _, _ -> }) },
            isError = state.isError
        )
        if (!state.isValidEmail) {
            Text(
                text = "Please enter a valid email address",
                color = MaterialTheme.colorScheme.error
            )
        }
        Button(onClick = {
            onEvent(
                LoginEvent.ForgotPassword(state.email),
                context,
                navigateToPopUp
            )
        }) {
            Text(text = "Reset Password")
        }

        if (state.isError) {
            Text(text = state.errorMessage, color = MaterialTheme.colorScheme.error)
        }

        if (state.recoveryEmailSent) {
            Toast.makeText(
                context,
                "Recovery email sent to your email address. Please Check your Inbox",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}