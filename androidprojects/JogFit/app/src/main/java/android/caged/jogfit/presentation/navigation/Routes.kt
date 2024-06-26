package android.caged.jogfit.presentation.navigation

sealed class Routes(val route: String) {
    data object AppStartRoute : Routes("app_start")
    data object LoginWithGoogleRoute : Routes("login_with_google")
    data object LoginWithEmailPasswordRoute : Routes("login_with_email_password")
    data object ForgotPassword: Routes("forgot_password")
    data object SignUpRoute : Routes("sign_up")
    data object AppNavigatorRoute : Routes("app_navigator")
    data object AppNavigationScreen : Routes("app_navigation")
    data object HomeRoute : Routes("home")
    data object SettingsScreen : Routes("settings")
    data object SettingsDest : Routes("settings_dest")
    data object StatisticsRoute : Routes("statistics")
    data object ProfileRoute : Routes("profile")
    data object ProfileSettings : Routes("profile_settings")
}