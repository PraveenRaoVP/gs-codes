package android.caged.notesapplication.presentation.navigation

sealed class Routes(val route: String) {
    data object OnboardingRoute : Routes("onboarding")
    data object SignInRoute : Routes("sign_in")
    data object SignUpRoute : Routes("sign_up")
    data object NotesRoute : Routes("notes")
    data object EditNoteRoute : Routes("edit_note")
    data object ProfileRoute : Routes("profile")
    data object SettingsRoute : Routes("settings")
}