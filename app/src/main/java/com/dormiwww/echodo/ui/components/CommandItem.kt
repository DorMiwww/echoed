package com.dormiwww.echodo.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import com.dormiwww.echodo.ui.screens.commands.CommandCategory
import com.dormiwww.echodo.ui.screens.commands.VoiceCommand
import com.dormiwww.echodo.ui.screens.home.SupportedApp
import com.dormiwww.echodo.ui.theme.Dimensions
import com.dormiwww.echodo.ui.theme.EchodoTheme

/**
 * Command item component - Displays a single voice command in a list.
 *
 * Features:
 * - Leading icon for the command
 * - Headline showing the voice phrase (in quotes)
 * - Supporting text with description
 * - Trailing badges showing supported apps
 * - Clickable to show detail dialog
 *
 * @param command The voice command to display.
 * @param onClick Callback when the item is clicked.
 * @param modifier Modifier to be applied to the item.
 */
@Composable
fun CommandItem(
    command: VoiceCommand,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ListItem(
        headlineContent = {
            Text(
                text = "\"${command.phrase}\"",
                style = MaterialTheme.typography.titleMedium,
                fontStyle = FontStyle.Italic
            )
        },
        supportingContent = {
            Text(
                text = command.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        leadingContent = {
            Icon(
                imageVector = Icons.Default.Mic,
                contentDescription = null,
                modifier = Modifier.size(Dimensions.iconSizeLarge),
                tint = MaterialTheme.colorScheme.primary
            )
        },
        trailingContent = {
            // Show supported app badges
            Row(
                horizontalArrangement = Arrangement.spacedBy(Dimensions.spacing1)
            ) {
                command.supportedApps.take(3).forEach { app ->
                    Text(
                        text = when (app) {
                            SupportedApp.TikTok -> "TT"
                            SupportedApp.Instagram -> "IG"
                            SupportedApp.YouTube -> "YT"
                        },
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        },
        modifier = modifier.clickable(onClick = onClick)
    )
}

@Preview(name = "Command Item - All Apps", showBackground = true)
@Composable
private fun CommandItemAllAppsPreview() {
    EchodoTheme {
        CommandItem(
            command = VoiceCommand(
                id = "scroll_down",
                phrase = "Scroll down",
                description = "Scroll down to see the next video or post",
                category = CommandCategory.Navigation,
                supportedApps = listOf(
                    SupportedApp.TikTok,
                    SupportedApp.Instagram,
                    SupportedApp.YouTube
                )
            ),
            onClick = {}
        )
    }
}

@Preview(name = "Command Item - Single App", showBackground = true)
@Composable
private fun CommandItemSingleAppPreview() {
    EchodoTheme {
        CommandItem(
            command = VoiceCommand(
                id = "like",
                phrase = "Like",
                description = "Like the current video or post",
                category = CommandCategory.Interaction,
                supportedApps = listOf(SupportedApp.TikTok)
            ),
            onClick = {}
        )
    }
}
