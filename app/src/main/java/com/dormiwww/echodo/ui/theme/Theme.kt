package com.dormiwww.echodo.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

/**
 * Dark color scheme with OLED optimization (true black backgrounds).
 * Primary scheme for VoxSwipe, optimized for voice-controlled UI with high contrast.
 */
private val DarkColorScheme = darkColorScheme(
    primary = VoiceViolet80,
    onPrimary = VoiceViolet20,
    primaryContainer = VoiceViolet40,
    onPrimaryContainer = VoiceViolet80,

    secondary = StatusTeal80,
    onSecondary = Color(0xFF003730),
    secondaryContainer = StatusTeal40,
    onSecondaryContainer = StatusTeal80,

    tertiary = AttentionAmber80,
    onTertiary = Color(0xFF3E2E00),
    tertiaryContainer = AttentionAmber40,
    onTertiaryContainer = AttentionAmber80,

    error = ErrorRed80,
    onError = Color(0xFF690005),
    errorContainer = ErrorRed40,
    onErrorContainer = ErrorRed80,

    background = SurfaceBlack, // True black for OLED
    onBackground = Color(0xFFE6E1E5),

    surface = SurfaceDark,
    onSurface = Color(0xFFE6E1E5),
    surfaceVariant = SurfaceDarkElevated,
    onSurfaceVariant = Color(0xFFCAC4D0),

    outline = Color(0xFF938F99),
    outlineVariant = Color(0xFF49454F),

    inverseSurface = Color(0xFFE6E1E5),
    inverseOnSurface = Color(0xFF313033),
    inversePrimary = VoiceViolet40,

    surfaceTint = VoiceViolet80
)

/**
 * Light color scheme for VoxSwipe.
 * Used when user explicitly selects light mode or system is in light mode.
 */
private val LightColorScheme = lightColorScheme(
    primary = VoiceViolet40,
    onPrimary = Color.White,
    primaryContainer = VoiceViolet80,
    onPrimaryContainer = VoiceViolet20,

    secondary = StatusTeal40,
    onSecondary = Color.White,
    secondaryContainer = StatusTeal80,
    onSecondaryContainer = Color(0xFF003730),

    tertiary = AttentionAmber40,
    onTertiary = Color.White,
    tertiaryContainer = AttentionAmber80,
    onTertiaryContainer = Color(0xFF3E2E00),

    error = ErrorRed40,
    onError = Color.White,
    errorContainer = ErrorRed80,
    onErrorContainer = Color(0xFF690005),

    background = SurfaceLight,
    onBackground = Color(0xFF1C1B1F),

    surface = SurfaceLight,
    onSurface = Color(0xFF1C1B1F),
    surfaceVariant = SurfaceLightElevated,
    onSurfaceVariant = Color(0xFF49454F),

    outline = Color(0xFF79747E),
    outlineVariant = Color(0xFFCAC4D0),

    inverseSurface = Color(0xFF313033),
    inverseOnSurface = Color(0xFFF4EFF4),
    inversePrimary = VoiceViolet80,

    surfaceTint = VoiceViolet40
)

/**
 * VoxSwipe Material Theme with dynamic theming support.
 *
 * Features:
 * - Material You dynamic colors on Android 12+ (when enabled)
 * - OLED-optimized dark mode with true black backgrounds
 * - Custom semantic color tokens for voice UI states
 * - Automatic status bar and navigation bar styling
 *
 * @param darkTheme Whether to use dark theme. Defaults to system setting.
 * @param dynamicColor Whether to use Material You dynamic colors. Defaults to true on Android 12+.
 * @param oledMode Whether to use true black backgrounds in dark mode for OLED optimization. Defaults to true.
 * @param content The composable content to wrap with this theme.
 */
@Composable
fun EchodoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    oledMode: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) {
                dynamicDarkColorScheme(context).copy(
                    // Override background with true black for OLED if enabled
                    background = if (oledMode) SurfaceBlack else dynamicDarkColorScheme(context).background
                )
            } else {
                dynamicLightColorScheme(context)
            }
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = Color.Transparent.toArgb()
            window.navigationBarColor = Color.Transparent.toArgb()

            // Set light or dark status bar icons based on theme
            WindowCompat.getInsetsController(window, view).apply {
                isAppearanceLightStatusBars = !darkTheme
                isAppearanceLightNavigationBars = !darkTheme
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
