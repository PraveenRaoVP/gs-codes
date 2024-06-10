package android.caged.notesapplication.presentation.onboarding

sealed class OnboardingEvent {
    data object SaveAppEntry : OnboardingEvent()
}