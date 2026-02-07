package com.dormiwww.echodo.domain.repository

import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for permission management.
 * Defines the contract for checking and requesting app permissions.
 */
interface PermissionRepository {
    /**
     * Check if accessibility service is enabled.
     * @return Flow emitting true when enabled, false otherwise.
     */
    fun isAccessibilityServiceEnabled(): Flow<Boolean>

    /**
     * Check if overlay permission is granted.
     * @return Flow emitting true when granted, false otherwise.
     */
    fun isOverlayPermissionGranted(): Flow<Boolean>

    /**
     * Check if microphone permission is granted.
     * @return Flow emitting true when granted, false otherwise.
     */
    fun isMicrophonePermissionGranted(): Flow<Boolean>

    /**
     * Check if notification permission is granted (Android 13+).
     * @return Flow emitting true when granted, false otherwise.
     */
    fun isNotificationPermissionGranted(): Flow<Boolean>

    /**
     * Check if all required permissions are granted.
     * @return Flow emitting true when all permissions granted, false otherwise.
     */
    fun areAllPermissionsGranted(): Flow<Boolean>

    /**
     * Request accessibility service permission.
     * This will typically launch the system settings screen.
     */
    suspend fun requestAccessibilityService()

    /**
     * Request overlay permission.
     * This will typically launch the system settings screen.
     */
    suspend fun requestOverlayPermission()

    /**
     * Request microphone permission.
     * This will typically show the standard Android permission dialog.
     */
    suspend fun requestMicrophonePermission()

    /**
     * Request notification permission (Android 13+).
     * This will typically show the standard Android permission dialog.
     */
    suspend fun requestNotificationPermission()
}
