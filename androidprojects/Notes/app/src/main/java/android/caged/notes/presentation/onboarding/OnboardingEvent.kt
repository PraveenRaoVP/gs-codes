package android.caged.notes.presentation.onboarding

sealed class OnboardingEvent {
    data object SaveAppEntry : OnboardingEvent()
}