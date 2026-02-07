package com.dormiwww.echodo.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.dormiwww.echodo.ui.screens.permissions.PermissionStatus
import com.dormiwww.echodo.ui.theme.Dimensions
import com.dormiwww.echodo.ui.theme.EchodoTheme
import com.dormiwww.echodo.ui.theme.PermissionDenied
import com.dormiwww.echodo.ui.theme.PermissionGranted
import com.dormiwww.echodo.ui.theme.PermissionNotRequested

/**
 * Permission card component - Displays a single permission in the setup flow.
 *
 * Features:
 * - Icon, title, description layout
 * - Status badge (Granted/Denied/Not Requested)
 * - Request button (when not granted)
 * - Test button (when granted)
 * - Animated color transitions based on status
 * - Minimum 80dp height for consistent spacing
 *
 * @param permissionName Name of the permission to display.
 * @param description Description explaining why this permission is needed.
 * @param icon Icon representing the permission.
 * @param status Current status of the permission.
 * @param onRequest Callback when user taps the request button.
 * @param onTest Optional callback when user taps the test button.
 * @param modifier Modifier to be applied to the card.
 */
@Composable
fun PermissionCard(
    permissionName: String,
    description: String,
    icon: ImageVector,
    status: PermissionStatus,
    onRequest: () -> Unit,
    onTest: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    val statusColor by animateColorAsState(
        targetValue = when (status) {
            PermissionStatus.Granted -> PermissionGranted
            PermissionStatus.Denied -> PermissionDenied
            PermissionStatus.NotRequested -> PermissionNotRequested
        },
        label = "Permission status color"
    )

    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimensions.spacing4),
            horizontalArrangement = Arrangement.spacedBy(Dimensions.spacing3),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Permission icon
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(Dimensions.iconSizeLarge),
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )

            // Title and description
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(Dimensions.spacing1)
            ) {
                Text(
                    text = permissionName,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                // Status badge
                AssistChip(
                    onClick = { },
                    label = {
                        Text(
                            text = when (status) {
                                PermissionStatus.Granted -> "Granted"
                                PermissionStatus.Denied -> "Denied"
                                PermissionStatus.NotRequested -> "Not Requested"
                            },
                            style = MaterialTheme.typography.labelSmall
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = when (status) {
                                PermissionStatus.Granted -> Icons.Default.Check
                                PermissionStatus.Denied -> Icons.Default.Close
                                PermissionStatus.NotRequested -> Icons.Default.Close
                            },
                            contentDescription = null,
                            modifier = Modifier.size(Dimensions.iconSizeSmall)
                        )
                    },
                    modifier = Modifier.padding(top = Dimensions.spacing1)
                )
            }
        }

        // Action buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimensions.spacing4)
                .padding(bottom = Dimensions.spacing4),
            horizontalArrangement = Arrangement.spacedBy(Dimensions.spacing2)
        ) {
            // Request button (show when not granted)
            AnimatedVisibility(
                visible = status != PermissionStatus.Granted,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Button(
                    onClick = onRequest,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = if (status == PermissionStatus.Denied) "Retry" else "Grant Permission"
                    )
                }
            }

            // Test button (show when granted and onTest is provided)
            AnimatedVisibility(
                visible = status == PermissionStatus.Granted && onTest != null,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                OutlinedButton(
                    onClick = { onTest?.invoke() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Test")
                }
            }
        }
    }
}

@Preview(name = "Permission Card - Not Requested", showBackground = true)
@Composable
private fun PermissionCardNotRequestedPreview() {
    EchodoTheme {
        PermissionCard(
            permissionName = "Microphone",
            description = "Required to capture voice commands",
            icon = Icons.Default.Mic,
            status = PermissionStatus.NotRequested,
            onRequest = {}
        )
    }
}

@Preview(name = "Permission Card - Granted", showBackground = true)
@Composable
private fun PermissionCardGrantedPreview() {
    EchodoTheme {
        PermissionCard(
            permissionName = "Microphone",
            description = "Required to capture voice commands",
            icon = Icons.Default.Mic,
            status = PermissionStatus.Granted,
            onRequest = {},
            onTest = {}
        )
    }
}

@Preview(name = "Permission Card - Denied", showBackground = true)
@Composable
private fun PermissionCardDeniedPreview() {
    EchodoTheme {
        PermissionCard(
            permissionName = "Microphone",
            description = "Required to capture voice commands",
            icon = Icons.Default.Mic,
            status = PermissionStatus.Denied,
            onRequest = {}
        )
    }
}
