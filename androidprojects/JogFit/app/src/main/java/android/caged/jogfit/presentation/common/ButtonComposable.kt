package android.caged.jogfit.presentation.common

import android.caged.jogfit.R
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BasicTextButton(
    @StringRes text: Int,
    modifier: Modifier, onClick: () -> Unit,
    style: TextStyle = TextStyle()
) {
    TextButton(onClick = onClick, modifier = Modifier) {
        Text(text = stringResource(text), style = style)
    }
}

@Preview(showBackground = true)
@Composable
fun BasicTextButtonPreview() {
    BasicTextButton(text = R.string.sign_in, modifier = Modifier, onClick = {})
}


@Composable
fun BasicButton(@StringRes text: Int, modifier: Modifier, action: () -> Unit) {
    Button(
        onClick = action,
        modifier = modifier,
        colors =
        ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Text(text = stringResource(text), fontSize = 16.sp)
    }
}

@Preview
@Composable
fun BasicButtonPreview() {
    BasicButton(text = R.string.sign_in, modifier = Modifier, action = {})
}

@Composable
fun SignInWithGoogleBtn(action: () -> Unit) {
    Button(
        onClick = action,
        colors =
        ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        modifier = Modifier.height(40.dp).border(
            width = 1.dp,
            color = if(isSystemInDarkTheme()) Color.White else Color.Black,
            shape = RoundedCornerShape(20.dp)
        ),
        elevation = ButtonDefaults.buttonElevation(1.dp)
    ) {
        Image(painter = painterResource(id = R.drawable.ic_google_logo), contentDescription = null)
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = stringResource(R.string.sign_in_with_google))
    }
}

@Preview
@Composable
fun SignInWithGoogleBtnPreview() {
    SignInWithGoogleBtn {

    }
}

@Composable
fun DialogConfirmButton(@StringRes text: Int, action: () -> Unit) {
    Button(
        onClick = action,
        colors =
        ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Text(text = stringResource(text))
    }
}

@Composable
fun DialogCancelButton(@StringRes text: Int, action: () -> Unit) {
    Button(
        onClick = action,
        colors =
        ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
            contentColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Text(text = stringResource(text))
    }
}