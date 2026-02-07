package com.dormiwww.echodo.ui.screens.onboarding

import androidx.compose.runtime.Immutable

/**
 * Represents the UI state of the Onboarding screen.
 */
@Immutable
data class OnboardingUiState(
    val currentPage: Int = 0,
    val totalPages: Int = 5,
    val canSkip: Boolean = true
)

/**
 * Represents a single onboarding page.
 */
@Immutable
data class OnboardingPage(
    val title: String,
    val description: String,
    val animationRes: Int? = null, // Lottie animation resource
    val canSkip: Boolean = true
)

/**
 * Predefined onboarding pages for VoxSwipe.
 */
object OnboardingPages {
    val Welcome = OnboardingPage(
        title = "Welcome to VoxSwipe",
        description = "Control TikTok, Instagram, and YouTube with your voice. Hands-free scrolling has never been easier.",
        canSkip = true
    )

    val HowItWorks = OnboardingPage(
        title = "How It Works",
        description = "Simply speak commands like 'scroll down', 'like', or 'next video' and VoxSwipe will do the rest.",
        canSkip = true
    )

    val SupportedApps = OnboardingPage(
        title = "Supported Apps",
        description = "Works seamlessly with TikTok, Instagram Reels, and YouTube Shorts. More apps coming soon.",
        canSkip = true
    )

    val PermissionsIntro = OnboardingPage(
        title = "Permissions Needed",
        description = "To work properly, VoxSwipe needs a few permissions. We'll guide you through each one.",
        canSkip = false
    )

    val Ready = OnboardingPage(
        title = "You're All Set!",
        description = "Let's set up your permissions and start controlling your apps with your voice.",
        canSkip = false
    )

    fun getPages(): List<OnboardingPage> = listOf(
        Welcome,
        HowItWorks,
        SupportedApps,
        PermissionsIntro,
        Ready
    )
}
