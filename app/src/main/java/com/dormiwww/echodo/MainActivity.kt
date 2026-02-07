package com.dormiwww.echodo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.dormiwww.echodo.ui.navigation.NavGraph
import com.dormiwww.echodo.ui.navigation.Routes
import com.dormiwww.echodo.ui.theme.EchodoTheme

/**
 * Main entry point for VoxSwipe application.
 *
 * Responsibilities:
 * - Enable edge-to-edge display
 * - Set up Material 3 theming
 * - Initialize navigation
 * - Determine start destination based on onboarding status
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            EchodoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    // TODO: Check if onboarding is completed from SharedPreferences
                    // val hasCompletedOnboarding = checkOnboardingStatus()
                    val startDestination = Routes.Onboarding.route // or Routes.Home.route

                    NavGraph(
                        navController = navController,
                        startDestination = startDestination
                    )
                }
            }
        }
    }

    /**
     * Check if user has completed onboarding.
     * @return true if onboarding is completed, false otherwise.
     */
    private fun checkOnboardingStatus(): Boolean {
        // TODO: Implement SharedPreferences check
        val prefs = getSharedPreferences("voxswipe_prefs", MODE_PRIVATE)
        return prefs.getBoolean("onboarding_completed", false)
    }
}
