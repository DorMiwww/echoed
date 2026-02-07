package com.dormiwww.echodo.ui.screens.settings

import androidx.compose.runtime.Immutable

/**
 * Represents the UI state of the Settings screen.
 */
@Immutable
data class SettingsUiState(
    // General settings
    val selectedLanguage: String = "English",
    val themeMode: ThemeMode = ThemeMode.System,
    val oledMode: Boolean = true,

    // Voice settings
    val voiceSensitivity: Float = 0.7f, // 0.0 to 1.0
    val continuousMode: Boolean = false,
    val wakeWordEnabled: Boolean = false,

    // Accessibility settings
    val hapticFeedback: Boolean = true,
    val audioFeedback: Boolean = true,
    val highContrastMode: Boolean = false,
    val textSizeMultiplier: Float = 1.0f, // 0.85 to 1.3

    // App info
    val appVersion: String = "1.0.0",
    val buildNumber: Int = 1
)

/**
 * Theme mode options for the app.
 */
@Immutable
enum class ThemeMode {
    System,
    Light,
    Dark
}

/**
 * Available languages for the app.
 */
@Immutable
enum class AppLanguage(val displayName: String, val code: String) {
    English("English", "en"),
    Spanish("Español", "es"),
    French("Français", "fr"),
    German("Deutsch", "de"),
    Japanese("日本語", "ja"),
    Korean("한국어", "ko"),
    Chinese("中文", "zh")
}
