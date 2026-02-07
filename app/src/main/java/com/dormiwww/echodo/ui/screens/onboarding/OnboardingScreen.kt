package com.dormiwww.echodo.ui.screens.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dormiwww.echodo.ui.theme.Dimensions
import com.dormiwww.echodo.ui.theme.EchodoTheme

/**
 * Onboarding screen - First time user experience.
 *
 * Features:
 * - HorizontalPager with 5 pages
 * - Page indicators
 * - Welcome message
 * - How it works explanation
 * - Supported apps showcase
 * - Permissions introduction
 * - Ready to start CTA
 *
 * Pages:
 * 1. Welcome - Introduction to VoxSwipe
 * 2. How It Works - Voice command examples
 * 3. Supported Apps - TikTok, Instagram, YouTube
 * 4. Permissions - Why permissions are needed
 * 5. Ready - Get started button
 *
 * @param onComplete Callback when user completes onboarding.
 * @param modifier Modifier for the screen.
 */
@Composable
fun OnboardingScreen(
    onComplete: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Scaffold(
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
                    text = "Onboarding Screen",
                    style = MaterialTheme.typography.displaySmall
                )
                Text(
                    text = "Welcome experience coming soon",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                // TODO: Add HorizontalPager
                // TODO: Add page indicators
                // TODO: Add Lottie animations for each page
                // TODO: Add Next/Back buttons
                // TODO: Add Skip button (conditional)
                // TODO: Add Get Started button on final page
            }
        }
    }
}

@Preview(name = "Onboarding Screen - Light")
@Preview(name = "Onboarding Screen - Dark", uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun OnboardingScreenPreview() {
    EchodoTheme {
        OnboardingScreen()
    }
}
