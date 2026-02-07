package com.dormiwww.echodo.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.dormiwww.echodo.ui.theme.Dimensions
import com.dormiwww.echodo.ui.theme.EchodoTheme
import com.dormiwww.echodo.ui.theme.VoiceActive
import com.dormiwww.echodo.ui.theme.VoiceInactive

/**
 * Status indicator component - Shows current service state with animated dot.
 *
 * Features:
 * - 8dp colored dot (green when running, gray when stopped)
 * - Pulsing animation when active
 * - Smooth color transition
 * - Text label showing service name
 *
 * @param isRunning Whether the service is currently running.
 * @param serviceName Name of the service to display.
 * @param modifier Modifier to be applied to the component.
 */
@Composable
fun StatusIndicator(
    isRunning: Boolean,
    serviceName: String = "VoxSwipe",
    modifier: Modifier = Modifier
) {
    val dotColor by animateColorAsState(
        targetValue = if (isRunning) VoiceActive else VoiceInactive,
        animationSpec = tween(300),
        label = "Status dot color"
    )

    val textColor by animateColorAsState(
        targetValue = if (isRunning) {
            MaterialTheme.colorScheme.onSurface
        } else {
            MaterialTheme.colorScheme.onSurfaceVariant
        },
        animationSpec = tween(300),
        label = "Status text color"
    )

    val alpha by animateFloatAsState(
        targetValue = if (isRunning) 1f else 0.6f,
        animationSpec = tween(300),
        label = "Status alpha"
    )

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(Dimensions.spacing2),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Status dot
        Canvas(
            modifier = Modifier.size(Dimensions.statusDotSize)
        ) {
            drawCircle(
                color = dotColor,
                alpha = alpha
            )
        }

        // Service status text
        Text(
            text = if (isRunning) "Listening" else "Stopped",
            style = MaterialTheme.typography.bodyMedium,
            color = textColor
        )
    }

    // TODO: Add pulsing animation to dot when isRunning = true
    // TODO: Use InfiniteTransition for continuous pulse effect
}

@Preview(name = "Status Indicator - Running", showBackground = true)
@Composable
private fun StatusIndicatorRunningPreview() {
    EchodoTheme {
        StatusIndicator(
            isRunning = true,
            serviceName = "VoxSwipe"
        )
    }
}

@Preview(name = "Status Indicator - Stopped", showBackground = true)
@Composable
private fun StatusIndicatorStoppedPreview() {
    EchodoTheme {
        StatusIndicator(
            isRunning = false,
            serviceName = "VoxSwipe"
        )
    }
}
