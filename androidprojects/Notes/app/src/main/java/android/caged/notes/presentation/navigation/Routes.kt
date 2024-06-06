package android.caged.notes.presentation.navigation

sealed class Route(
    val route: String
) {
    data object PreSigningIn: Route("preSigningIn")
    data object OnboardingScreen : Route("onboardingScreen")
    data object SignInScreen : Route("signInScreen")
    data object SignUpScreen : Route("signUpScreen")

    data object PostSigningIn : Route("postSignIn")
    data object NotesScreen : Route("notesScreen")
    data object CreateNoteScreen : Route("createNoteScreen")
    data object NoteDetailScreen : Route("noteDetailScreen")
    data object SettingsScreen : Route("settingsScreen")

    val noteId = "noteID"
    val noteIdArg ="?$noteId={$noteId}"
}