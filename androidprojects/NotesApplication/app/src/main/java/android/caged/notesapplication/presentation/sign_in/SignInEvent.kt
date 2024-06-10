package android.caged.notesapplication.presentation.sign_in

sealed class SignInEvent {
    data object SigningInByGoogle : SignInEvent()
    data object SignedInByGoogle : SignInEvent()
    data object ErrorSignedInByGoogle : SignInEvent()
}