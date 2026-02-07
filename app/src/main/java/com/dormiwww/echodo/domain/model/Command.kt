package com.dormiwww.echodo.domain.model

/**
 * Domain model representing a voice command.
 * This is the business logic representation, independent of UI or data layer.
 */
data class Command(
    val id: String,
    val phrase: String,
    val action: CommandAction,
    val supportedApps: List<String>,
    val category: CommandCategory,
    val examples: List<String> = emptyList(),
    val requiresConfirmation: Boolean = false
)

/**
 * Types of actions that can be performed by voice commands.
 */
enum class CommandAction {
    SCROLL_UP,
    SCROLL_DOWN,
    LIKE,
    UNLIKE,
    COMMENT,
    SHARE,
    PAUSE,
    RESUME,
    BACK,
    OPEN_APP,
    CLOSE_APP
}

/**
 * Categories for organizing commands.
 */
enum class CommandCategory {
    NAVIGATION,
    INTERACTION,
    PLAYBACK,
    SYSTEM
}
