package com.dormiwww.echodo.ui.screens.permissions

import androidx.compose.runtime.Immutable

/**
 * Represents the UI state of the Permissions screen.
 */
@Immutable
data class PermissionsUiState(
    val permissions: List<PermissionItem> = emptyList(),
    val allGranted: Boolean = false,
    val isLoading: Boolean = false
)

/**
 * Represents a single permission item in the permissions flow.
 */
@Immutable
data class PermissionItem(
    val type: PermissionType,
    val name: String,
    val description: String,
    val status: PermissionStatus,
    val isRequired: Boolean = true,
    val stepNumber: Int
)

/**
 * Types of permissions required by VoxSwipe.
 */
@Immutable
enum class PermissionType {
    /**
     * Accessibility Service permission.
     * Required to interact with TikTok, Instagram, and YouTube.
     */
    AccessibilityService,

    /**
     * Display over other apps permission.
     * Required to show voice control overlay.
     */
    OverlayPermission,

    /**
     * Microphone permission.
     * Required to capture voice commands.
     */
    Microphone,

    /**
     * Notification permission (Android 13+).
     * Used to show feedback and status updates.
     */
    Notification
}

/**
 * Status of a permission.
 */
@Immutable
enum class PermissionStatus {
    /**
     * Permission has not been requested yet.
     */
    NotRequested,

    /**
     * Permission has been granted.
     */
    Granted,

    /**
     * Permission has been denied.
     */
    Denied
}
