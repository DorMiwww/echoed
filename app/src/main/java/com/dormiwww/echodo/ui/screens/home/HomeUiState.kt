package com.dormiwww.echodo.ui.screens.home

import androidx.compose.runtime.Immutable

/**
 * Represents the UI state of the Home screen.
 * Sealed interface ensures exhaustive when expressions and type safety.
 */
@Immutable
sealed interface HomeUiState {
    /**
     * Initial loading state while initializing services.
     */
    data object Loading : HomeUiState

    /**
     * Ready state - Voice service is idle and ready to accept commands.
     * @param isServiceRunning Whether the accessibility service is active.
     * @param selectedApps List of currently selected apps for voice control.
     * @param recentCommands List of recently executed commands.
     */
    data class Ready(
        val isServiceRunning: Boolean = false,
        val selectedApps: List<SupportedApp> = listOf(SupportedApp.TikTok),
        val recentCommands: List<RecentCommand> = emptyList()
    ) : HomeUiState

    /**
     * Listening state - Microphone is active and listening for voice input.
     * @param isServiceRunning Whether the accessibility service is active.
     * @param selectedApps List of currently selected apps for voice control.
     * @param recentCommands List of recently executed commands.
     */
    data class Listening(
        val isServiceRunning: Boolean = true,
        val selectedApps: List<SupportedApp> = listOf(SupportedApp.TikTok),
        val recentCommands: List<RecentCommand> = emptyList()
    ) : HomeUiState

    /**
     * Command recognized state - Voice input was successfully recognized.
     * @param command The recognized command text.
     * @param isServiceRunning Whether the accessibility service is active.
     * @param selectedApps List of currently selected apps for voice control.
     * @param recentCommands List of recently executed commands.
     */
    data class CommandRecognized(
        val command: String,
        val isServiceRunning: Boolean = true,
        val selectedApps: List<SupportedApp> = listOf(SupportedApp.TikTok),
        val recentCommands: List<RecentCommand> = emptyList()
    ) : HomeUiState

    /**
     * Error state - Something went wrong.
     * @param message User-friendly error message.
     * @param isServiceRunning Whether the accessibility service is active.
     * @param selectedApps List of currently selected apps for voice control.
     * @param recentCommands List of recently executed commands.
     */
    data class Error(
        val message: String,
        val isServiceRunning: Boolean = false,
        val selectedApps: List<SupportedApp> = listOf(SupportedApp.TikTok),
        val recentCommands: List<RecentCommand> = emptyList()
    ) : HomeUiState
}

/**
 * Supported social media applications for voice control.
 */
@Immutable
enum class SupportedApp {
    TikTok,
    Instagram,
    YouTube
}

/**
 * Represents a recently executed voice command.
 * @param command The command text that was executed.
 * @param timestamp Timestamp in milliseconds when the command was executed.
 * @param app The app the command was executed in.
 * @param success Whether the command executed successfully.
 */
@Immutable
data class RecentCommand(
    val command: String,
    val timestamp: Long,
    val app: SupportedApp,
    val success: Boolean = true
)
