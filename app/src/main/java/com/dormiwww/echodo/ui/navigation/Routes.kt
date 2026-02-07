package com.dormiwww.echodo.ui.navigation

/**
 * Type-safe navigation routes for VoxSwipe.
 * Sealed class ensures compile-time safety and prevents typos in navigation.
 */
sealed class Routes(val route: String) {
    /**
     * Onboarding flow - First time user experience.
     * Shows welcome, how it works, supported apps, and permissions introduction.
     */
    data object Onboarding : Routes("onboarding")

    /**
     * Permissions setup screen.
     * Guides users through granting required permissions:
     * - Accessibility Service
     * - Overlay Permission
     * - Microphone
     * - Notifications
     */
    data object Permissions : Routes("permissions")

    /**
     * Home screen - Main voice control interface.
     * Shows voice button, app selector, status indicator, and recent commands.
     */
    data object Home : Routes("home")

    /**
     * Settings screen - App configuration.
     * Categories: General, Voice, Accessibility, Permissions, About.
     */
    data object Settings : Routes("settings")

    /**
     * Commands list screen - Browse all available voice commands.
     * Filter by app (TikTok, Instagram, YouTube) or category.
     */
    data object Commands : Routes("commands")

    /**
     * Command detail screen with usage examples.
     * @param commandId The unique identifier of the command to display.
     */
    data class CommandDetail(val commandId: String) : Routes("command/$commandId") {
        companion object {
            const val ROUTE_WITH_ARG = "command/{commandId}"
            const val ARG_COMMAND_ID = "commandId"
        }
    }
}
