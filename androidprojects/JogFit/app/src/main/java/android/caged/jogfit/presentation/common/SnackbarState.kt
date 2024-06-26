package android.caged.jogfit.presentation.common

sealed class SnackbarState {
    data object Action : SnackbarState()
    data object Dismissed : SnackbarState()
}