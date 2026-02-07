package com.dormiwww.echodo.ui.screens.settings

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
 * Settings screen - App configuration interface.
 *
 * Features:
 * - General: Language, theme, OLED mode
 * - Voice: Sensitivity, continuous mode, wake word
 * - Accessibility: Haptic feedback, audio feedback, high contrast, text size
 * - Permissions: Quick access to permission management
 * - About: Version, privacy policy, licenses, rate app
 *
 * @param onBack Callback when user presses back.
 * @param onNavigateToPermissions Callback when user taps permissions.
 * @param modifier Modifier for the screen.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onBack: () -> Unit = {},
    onNavigateToPermissions: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Settings",
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
                    text = "Settings Screen",
                    style = MaterialTheme.typography.displaySmall
                )
                Text(
                    text = "Configuration options coming soon",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                // TODO: Add SearchBar
                // TODO: Add LazyColumn with settings categories
                // TODO: Add General settings section
                // TODO: Add Voice settings section
                // TODO: Add Accessibility settings section
                // TODO: Add Permissions section
                // TODO: Add About section
            }
        }
    }
}

@Preview(name = "Settings Screen - Light")
@Preview(name = "Settings Screen - Dark", uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SettingsScreenPreview() {
    EchodoTheme {
        SettingsScreen()
    }
}
