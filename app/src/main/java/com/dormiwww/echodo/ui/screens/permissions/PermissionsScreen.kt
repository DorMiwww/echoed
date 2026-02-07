package com.dormiwww.echodo.ui.screens.permissions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dormiwww.echodo.ui.theme.Dimensions
import com.dormiwww.echodo.ui.theme.EchodoTheme

/**
 * Permissions screen - Guides users through granting required permissions.
 *
 * Features:
 * - Vertical stepper pattern showing permission flow
 * - Each permission shows: icon, title, description, status badge, action button
 * - Test button appears after permission is granted
 * - Connected line between steps showing progress
 * - Bottom CTA enabled only when all permissions granted
 *
 * Required permissions:
 * 1. Accessibility Service - Interact with apps
 * 2. Overlay Permission - Show voice control overlay
 * 3. Microphone - Capture voice commands
 * 4. Notifications - Show feedback (Android 13+)
 *
 * @param onComplete Callback when all permissions are granted and user confirms.
 * @param onBack Callback when user presses back.
 * @param modifier Modifier for the screen.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PermissionsScreen(
    onComplete: () -> Unit = {},
    onBack: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Permissions",
                        style = MaterialTheme.typography.headlineSmall
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Navigate back"
                        )
                    }
                }
            )
        },
        modifier = modifier
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(Dimensions.spacing4),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(Dimensions.spacing6)
            ) {
                Text(
                    text = "Permissions Screen",
                    style = MaterialTheme.typography.displaySmall
                )
                Text(
                    text = "Permission setup flow coming soon",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                // TODO: Add LazyColumn with vertical stepper
                // TODO: Add PermissionCard for Accessibility Service
                // TODO: Add PermissionCard for Overlay Permission
                // TODO: Add PermissionCard for Microphone
                // TODO: Add PermissionCard for Notifications
                // TODO: Add Continue button (enabled when allGranted = true)
            }
        }
    }
}

@Preview(name = "Permissions Screen - Light")
@Preview(name = "Permissions Screen - Dark", uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PermissionsScreenPreview() {
    EchodoTheme {
        PermissionsScreen()
    }
}
