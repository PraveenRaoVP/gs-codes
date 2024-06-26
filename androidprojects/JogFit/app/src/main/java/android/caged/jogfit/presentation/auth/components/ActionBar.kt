package android.caged.jogfit.presentation.auth.components

import android.caged.jogfit.utils.ThemeManager
import android.caged.jogfit.utils.ThemePreferenceManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActionBar(
    title: String,
    onBackClick: () -> Unit,
    isBackEnabled: Boolean
) {
    TopAppBar(title = { Text(text = title) },
        modifier = Modifier.background(color = MaterialTheme.colorScheme.background),
        navigationIcon = {
            if (isBackEnabled) {
                IconButton(
                    onClick = onBackClick,
                    colors = IconButtonColors(
                        containerColor = MaterialTheme.colorScheme.background,
                        contentColor = toolbarColor(),
                        disabledContentColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent
                    )
                ) {
                    Image(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(toolbarColor())
                    )
                }
            }
        })
}

@Composable
fun toolbarColor(): Color {
    return if (ThemePreferenceManager.isDarkTheme.value) {
        Color.White
    } else {
        Color.Black
    }
}