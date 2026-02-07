package com.dormiwww.echodo.domain.model

/**
 * Domain model representing a target application for voice control.
 */
data class AppTarget(
    val id: String,
    val name: String,
    val packageName: String,
    val isInstalled: Boolean = false,
    val isSupported: Boolean = true
)

/**
 * Predefined app targets supported by VoxSwipe.
 */
object AppTargets {
    val TIKTOK = AppTarget(
        id = "tiktok",
        name = "TikTok",
        packageName = "com.zhiliaoapp.musically"
    )

    val INSTAGRAM = AppTarget(
        id = "instagram",
        name = "Instagram",
        packageName = "com.instagram.android"
    )

    val YOUTUBE = AppTarget(
        id = "youtube",
        name = "YouTube",
        packageName = "com.google.android.youtube"
    )

    fun getAll(): List<AppTarget> = listOf(TIKTOK, INSTAGRAM, YOUTUBE)
}
