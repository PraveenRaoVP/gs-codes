package android.caged.jogfit.presentation.auth.login

import android.caged.jogfit.R
import android.caged.jogfit.presentation.auth.components.ActionBar
import android.caged.jogfit.presentation.navigation.Routes
import android.caged.jogfit.ui.theme.JogFitTheme
import android.caged.jogfit.presentation.common.BasicTextButton
import android.caged.jogfit.presentation.common.SignInWithGoogleBtn
import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun LoginScreen(
    onEvent: (LoginEvent, Context, (String, String)-> Unit) -> Unit,
    navigateTo: (String) -> Unit,
    navigateToPopUp: (String, String) -> Unit
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            ActionBar(title = "Sign In", onBackClick = { /*TODO*/ }, isBackEnabled = false)
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
//                SignInWithGoogleBtn(action = { viewModel.signInWithGoogle(navigateTo = navigateToPopUp, context) })
                SignInWithGoogleBtn(action = { onEvent(LoginEvent.OnLoginWithGoogleClick, context, navigateToPopUp)})

                Spacer(modifier = Modifier.height(20.dp))
                Text(text = "or", style = MaterialTheme.typography.titleSmall)
                Spacer(modifier = Modifier.height(30.dp))
                BasicTextButton(
                    text = R.string.sign_in_with_email,
                    modifier = Modifier.width(200.dp),
                    onClick = { navigateTo(Routes.LoginWithEmailPasswordRoute.route) },
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    JogFitTheme {
        LoginScreen(
//            viewModel = hiltViewModel(),
            onEvent = { _, _, _ ->},
            navigateTo = {},
            navigateToPopUp = { _, _ -> }
        )
    }
}