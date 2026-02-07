package com.dormiwww.echodo.ui.screens.commands

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
 * Commands list screen - Browse all available voice commands.
 *
 * Features:
 * - SearchBar for filtering commands
 * - Filter chips for categories and apps
 * - LazyColumn with all commands
 * - Each command shows phrase, description, supported apps
 * - Click to see detailed usage examples
 * - Empty state when no commands match search
 *
 * Command categories:
 * - Navigation: scroll, swipe
 * - Interaction: like, comment, share
 * - Playback: pause, resume
 * - System: open app, go back
 *
 * @param onBack Callback when user presses back.
 * @param onCommandClick Callback when user taps a command.
 * @param modifier Modifier for the screen.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommandsListScreen(
    onBack: () -> Unit = {},
    onCommandClick: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Voice Commands",
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
                    text = "Commands List Screen",
                    style = MaterialTheme.typography.displaySmall
                )
                Text(
                    text = "Voice commands library coming soon",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                // TODO: Add SearchBar
                // TODO: Add FilterChips (horizontal scrollable row)
                // TODO: Add LazyColumn with CommandItem composables
                // TODO: Add Empty state when no results
            }
        }
    }
}

@Preview(name = "Commands List Screen - Light")
@Preview(name = "Commands List Screen - Dark", uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CommandsListScreenPreview() {
    EchodoTheme {
        CommandsListScreen()
    }
}
