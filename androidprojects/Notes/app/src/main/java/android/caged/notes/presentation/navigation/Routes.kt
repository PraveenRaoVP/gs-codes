package android.caged.notes.presentation.navigation

sealed class Route(
    val route: String
) {
    data object OnboardingScreen : Route("onboardingScreen")
    data object NotesScreen : Route("notesScreen")
    data object SignInScreen : Route("signInScreen")
    data object SignUpScreen : Route("signUpScreen")
    data object CreateNoteScreen : Route("createNoteScreen")
    data object NoteDetailScreen : Route("noteDetailScreen")
    data object SettingsScreen : Route("settingsScreen")

    val noteId = "noteID"
    val noteIdArg ="?$noteId={$noteId}"
}