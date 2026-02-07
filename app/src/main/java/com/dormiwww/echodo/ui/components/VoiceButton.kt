package com.dormiwww.echodo.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.tooling.preview.Preview
import com.dormiwww.echodo.ui.theme.Dimensions
import com.dormiwww.echodo.ui.theme.EchodoTheme

/**
 * Voice button component - Large microphone button for activating voice input.
 *
 * States:
 * - Idle: Static mic icon, ready to be pressed
 * - Listening: Pulsing rings animation, icon color changes
 * - Processing: Circular progress indicator
 * - Success: Checkmark morphing animation
 * - Error: Shake animation + X icon
 *
 * Animations:
 * - InfiniteTransition for pulse effect (scale 1.0→1.15→1.0, alpha 1.0→0.6→1.0)
 * - Multiple delayed rings expanding from center
 * - Scale bump on command recognition (1.0→1.1→1.0, 300ms)
 * - Horizontal shake on error (Spring dampingRatio = 0.3f)
 *
 * @param onClick Callback when button is pressed.
 * @param isListening Whether the button is actively listening for voice input.
 * @param isProcessing Whether the button is processing recognized speech.
 * @param modifier Modifier to be applied to the button.
 */
@Composable
fun VoiceButton(
    onClick: () -> Unit,
    isListening: Boolean = false,
    isProcessing: Boolean = false,
    modifier: Modifier = Modifier
) {
    val state = when {
        isProcessing -> "Processing voice command"
        isListening -> "Listening for voice input"
        else -> "Press to start voice control"
    }

    Box(
        modifier = modifier
            .size(Dimensions.voiceButtonDefault)
            .semantics {
                contentDescription = "Voice control button"
                stateDescription = state
            },
        contentAlignment = Alignment.Center
    ) {
        // TODO: Add pulsing ring animations when listening
        // TODO: Add circular progress indicator when processing
        // TODO: Implement scale animations
        // TODO: Implement shake animation on error

        FilledIconButton(
            onClick = onClick,
            modifier = Modifier.size(Dimensions.voiceButtonDefault),
            colors = IconButtonDefaults.filledIconButtonColors(
                containerColor = if (isListening) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.primaryContainer
                },
                contentColor = if (isListening) {
                    MaterialTheme.colorScheme.onPrimary
                } else {
                    MaterialTheme.colorScheme.onPrimaryContainer
                }
            )
        ) {
            Icon(
                imageVector = Icons.Default.Mic,
                contentDescription = null,
                modifier = Modifier.size(Dimensions.iconSizeExtraLarge)
            )
        }
    }
}

@Preview(name = "Voice Button - Idle")
@Composable
private fun VoiceButtonIdlePreview() {
    EchodoTheme {
        VoiceButton(
            onClick = {},
            isListening = false,
            isProcessing = false
        )
    }
}

@Preview(name = "Voice Button - Listening")
@Composable
private fun VoiceButtonListeningPreview() {
    EchodoTheme {
        VoiceButton(
            onClick = {},
            isListening = true,
            isProcessing = false
        )
    }
}

@Preview(name = "Voice Button - Processing")
@Composable
private fun VoiceButtonProcessingPreview() {
    EchodoTheme {
        VoiceButton(
            onClick = {},
            isListening = false,
            isProcessing = true
        )
    }
}
