package com.dormiwww.echodo.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.VideoLibrary
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.dormiwww.echodo.ui.screens.home.SupportedApp
import com.dormiwww.echodo.ui.theme.Dimensions
import com.dormiwww.echodo.ui.theme.EchodoTheme

/**
 * App selector component - FilterChips for selecting target apps.
 *
 * Features:
 * - Multi-select FilterChips for TikTok, Instagram, YouTube
 * - Brand-colored icons when selected
 * - At least one app must be selected at all times
 * - Horizontal row layout
 *
 * @param selectedApps List of currently selected apps.
 * @param onSelectionChange Callback when selection changes.
 * @param modifier Modifier to be applied to the row.
 */
@Composable
fun AppSelector(
    selectedApps: List<SupportedApp>,
    onSelectionChange: (List<SupportedApp>) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(Dimensions.spacing2)
    ) {
        SupportedApp.entries.forEach { app ->
            val isSelected = selectedApps.contains(app)

            FilterChip(
                selected = isSelected,
                onClick = {
                    // Prevent deselecting if it's the last selected app
                    if (!isSelected || selectedApps.size > 1) {
                        val newSelection = if (isSelected) {
                            selectedApps - app
                        } else {
                            selectedApps + app
                        }
                        onSelectionChange(newSelection)
                    }
                },
                label = {
                    Text(text = app.name)
                },
                leadingIcon = {
                    Icon(
                        imageVector = getAppIcon(app),
                        contentDescription = null,
                        modifier = Modifier.size(Dimensions.iconSizeDefault)
                    )
                }
            )
        }
    }
}

/**
 * Returns the appropriate icon for each supported app.
 * TODO: Replace with actual brand icons/assets.
 */
private fun getAppIcon(app: SupportedApp): ImageVector {
    return when (app) {
        SupportedApp.TikTok -> Icons.Default.VideoLibrary
        SupportedApp.Instagram -> Icons.Default.Photo
        SupportedApp.YouTube -> Icons.Default.PlayArrow
    }
}

@Preview(name = "App Selector - Single Selection", showBackground = true)
@Composable
private fun AppSelectorSinglePreview() {
    EchodoTheme {
        AppSelector(
            selectedApps = listOf(SupportedApp.TikTok),
            onSelectionChange = {}
        )
    }
}

@Preview(name = "App Selector - Multi Selection", showBackground = true)
@Composable
private fun AppSelectorMultiPreview() {
    EchodoTheme {
        AppSelector(
            selectedApps = listOf(SupportedApp.TikTok, SupportedApp.Instagram),
            onSelectionChange = {}
        )
    }
}

@Preview(name = "App Selector - All Selected", showBackground = true)
@Composable
private fun AppSelectorAllPreview() {
    EchodoTheme {
        AppSelector(
            selectedApps = SupportedApp.entries,
            onSelectionChange = {}
        )
    }
}
