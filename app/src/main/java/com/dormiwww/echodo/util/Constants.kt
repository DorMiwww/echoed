package com.dormiwww.echodo.util

/**
 * Application-wide constants.
 */
object Constants {
    // Package names for supported apps
    const val PACKAGE_TIKTOK = "com.zhiliaoapp.musically"
    const val PACKAGE_INSTAGRAM = "com.instagram.android"
    const val PACKAGE_YOUTUBE = "com.google.android.youtube"

    // Shared preferences keys
    const val PREFS_NAME = "voxswipe_prefs"
    const val PREF_ONBOARDING_COMPLETED = "onboarding_completed"
    const val PREF_SELECTED_APPS = "selected_apps"
    const val PREF_VOICE_SENSITIVITY = "voice_sensitivity"
    const val PREF_CONTINUOUS_MODE = "continuous_mode"
    const val PREF_WAKE_WORD_ENABLED = "wake_word_enabled"
    const val PREF_HAPTIC_FEEDBACK = "haptic_feedback"
    const val PREF_AUDIO_FEEDBACK = "audio_feedback"
    const val PREF_HIGH_CONTRAST = "high_contrast"
    const val PREF_THEME_MODE = "theme_mode"
    const val PREF_OLED_MODE = "oled_mode"

    // Voice recognition
    const val VOICE_RECOGNITION_TIMEOUT_MS = 5000L
    const val COMMAND_CONFIDENCE_THRESHOLD = 0.7f

    // Animation durations
    const val ANIM_DURATION_SHORT = 200
    const val ANIM_DURATION_MEDIUM = 350
    const val ANIM_DURATION_LONG = 500

    // Accessibility service
    const val ACCESSIBILITY_SERVICE_ID = "com.dormiwww.echodo/.service.VoxSwipeAccessibilityService"

    // Deep links
    const val DEEP_LINK_PERMISSIONS = "echodo://permissions"
    const val DEEP_LINK_SETTINGS = "echodo://settings"
}
