package com.dormiwww.echodo.ui.screens.commands

import androidx.compose.runtime.Immutable
import com.dormiwww.echodo.ui.screens.home.SupportedApp

/**
 * Represents the UI state of the Commands List screen.
 */
@Immutable
data class CommandsListUiState(
    val commands: List<VoiceCommand> = emptyList(),
    val filteredCommands: List<VoiceCommand> = emptyList(),
    val searchQuery: String = "",
    val selectedCategory: CommandCategory? = null,
    val selectedApp: SupportedApp? = null,
    val isLoading: Boolean = false
)

/**
 * Represents a voice command.
 */
@Immutable
data class VoiceCommand(
    val id: String,
    val phrase: String,
    val description: String,
    val category: CommandCategory,
    val supportedApps: List<SupportedApp>,
    val examples: List<String> = emptyList(),
    val iconName: String = "mic" // Icon identifier
)

/**
 * Categories of voice commands.
 */
@Immutable
enum class CommandCategory(val displayName: String) {
    Navigation("Navigation"),
    Interaction("Interaction"),
    Playback("Playback"),
    System("System")
}

/**
 * Predefined voice commands for VoxSwipe.
 */
object VoiceCommands {
    // Navigation commands
    val ScrollDown = VoiceCommand(
        id = "scroll_down",
        phrase = "Scroll down",
        description = "Scroll down to see the next video or post",
        category = CommandCategory.Navigation,
        supportedApps = listOf(SupportedApp.TikTok, SupportedApp.Instagram, SupportedApp.YouTube),
        examples = listOf("Scroll down", "Next", "Swipe up")
    )

    val ScrollUp = VoiceCommand(
        id = "scroll_up",
        phrase = "Scroll up",
        description = "Scroll up to see the previous video or post",
        category = CommandCategory.Navigation,
        supportedApps = listOf(SupportedApp.TikTok, SupportedApp.Instagram, SupportedApp.YouTube),
        examples = listOf("Scroll up", "Previous", "Go back", "Swipe down")
    )

    // Interaction commands
    val Like = VoiceCommand(
        id = "like",
        phrase = "Like",
        description = "Like the current video or post",
        category = CommandCategory.Interaction,
        supportedApps = listOf(SupportedApp.TikTok, SupportedApp.Instagram, SupportedApp.YouTube),
        examples = listOf("Like", "Heart", "Love it")
    )

    val Comment = VoiceCommand(
        id = "comment",
        phrase = "Comment",
        description = "Open the comments section",
        category = CommandCategory.Interaction,
        supportedApps = listOf(SupportedApp.TikTok, SupportedApp.Instagram, SupportedApp.YouTube),
        examples = listOf("Comment", "Comments", "Show comments")
    )

    val Share = VoiceCommand(
        id = "share",
        phrase = "Share",
        description = "Open the share menu",
        category = CommandCategory.Interaction,
        supportedApps = listOf(SupportedApp.TikTok, SupportedApp.Instagram, SupportedApp.YouTube),
        examples = listOf("Share", "Send")
    )

    // Playback commands
    val Pause = VoiceCommand(
        id = "pause",
        phrase = "Pause",
        description = "Pause the current video",
        category = CommandCategory.Playback,
        supportedApps = listOf(SupportedApp.TikTok, SupportedApp.Instagram, SupportedApp.YouTube),
        examples = listOf("Pause", "Stop")
    )

    val Resume = VoiceCommand(
        id = "resume",
        phrase = "Resume",
        description = "Resume playback",
        category = CommandCategory.Playback,
        supportedApps = listOf(SupportedApp.TikTok, SupportedApp.Instagram, SupportedApp.YouTube),
        examples = listOf("Resume", "Play", "Continue")
    )

    // System commands
    val OpenApp = VoiceCommand(
        id = "open_app",
        phrase = "Open [app name]",
        description = "Open the specified app",
        category = CommandCategory.System,
        supportedApps = listOf(SupportedApp.TikTok, SupportedApp.Instagram, SupportedApp.YouTube),
        examples = listOf("Open TikTok", "Open Instagram", "Open YouTube")
    )

    val GoBack = VoiceCommand(
        id = "go_back",
        phrase = "Go back",
        description = "Navigate back to the previous screen",
        category = CommandCategory.System,
        supportedApps = listOf(SupportedApp.TikTok, SupportedApp.Instagram, SupportedApp.YouTube),
        examples = listOf("Go back", "Back")
    )

    fun getAllCommands(): List<VoiceCommand> = listOf(
        ScrollDown,
        ScrollUp,
        Like,
        Comment,
        Share,
        Pause,
        Resume,
        OpenApp,
        GoBack
    )
}
