package com.dormiwww.echodo.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.ui.hapticfeedback.HapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType

/**
 * Extension functions for common operations throughout the app.
 */

/**
 * Open accessibility settings for this app.
 */
fun Context.openAccessibilitySettings() {
    val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    startActivity(intent)
}

/**
 * Open overlay permission settings for this app.
 */
fun Context.openOverlaySettings() {
    val intent = Intent(
        Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
        Uri.parse("package:$packageName")
    )
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    startActivity(intent)
}

/**
 * Open app settings screen.
 */
fun Context.openAppSettings() {
    val intent = Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.parse("package:$packageName")
    )
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    startActivity(intent)
}

/**
 * Perform haptic feedback if enabled in preferences.
 */
fun HapticFeedback.performHapticFeedback(
    type: HapticFeedbackType = HapticFeedbackType.LongPress
) {
    performHapticFeedback(type)
}

/**
 * Format timestamp to relative time string (e.g., "2 minutes ago").
 */
fun Long.toRelativeTimeString(): String {
    val now = System.currentTimeMillis()
    val diff = now - this

    return when {
        diff < 60_000 -> "Just now"
        diff < 3600_000 -> "${diff / 60_000} minutes ago"
        diff < 86400_000 -> "${diff / 3600_000} hours ago"
        diff < 604800_000 -> "${diff / 86400_000} days ago"
        else -> "${diff / 604800_000} weeks ago"
    }
}
