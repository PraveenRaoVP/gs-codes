package android.caged.jogfit.utils

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.staticCompositionLocalOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object ThemeManager {
    private val _isDarkTheme = MutableStateFlow(false)
    val isDarkTheme: StateFlow<Boolean> = _isDarkTheme

    fun setDarkTheme(isDark: Boolean) {
        _isDarkTheme.value = isDark
    }
}

val LocalThemeManager = staticCompositionLocalOf { ThemeManager }