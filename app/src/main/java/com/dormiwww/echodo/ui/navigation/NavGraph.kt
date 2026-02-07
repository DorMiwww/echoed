package com.dormiwww.echodo.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.dormiwww.echodo.ui.screens.commands.CommandsListScreen
import com.dormiwww.echodo.ui.screens.home.HomeScreen
import com.dormiwww.echodo.ui.screens.onboarding.OnboardingScreen
import com.dormiwww.echodo.ui.screens.permissions.PermissionsScreen
import com.dormiwww.echodo.ui.screens.settings.SettingsScreen

/**
 * Navigation animation duration in milliseconds.
 */
private const val ANIMATION_DURATION = 350

/**
 * Main navigation graph for VoxSwipe.
 * Handles all screen transitions with appropriate animations.
 *
 * @param navController The navigation controller managing app navigation.
 * @param modifier Modifier to be applied to the NavHost.
 * @param startDestination The initial destination route. Typically Onboarding or Home based on first launch.
 */
@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = Routes.Onboarding.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
        enterTransition = { slideInEnterTransition() },
        exitTransition = { slideOutExitTransition() },
        popEnterTransition = { slideInPopEnterTransition() },
        popExitTransition = { slideOutPopExitTransition() }
    ) {
        // Onboarding flow
        composable(
            route = Routes.Onboarding.route,
            enterTransition = { fadeIn(tween(ANIMATION_DURATION)) },
            exitTransition = { fadeOut(tween(ANIMATION_DURATION)) }
        ) {
            OnboardingScreen(
                onComplete = {
                    navController.navigate(Routes.Permissions.route) {
                        // Clear onboarding from back stack
                        popUpTo(Routes.Onboarding.route) { inclusive = true }
                    }
                }
            )
        }

        // Permissions setup
        composable(
            route = Routes.Permissions.route,
            enterTransition = { slideInEnterTransition() },
            exitTransition = { slideOutExitTransition() }
        ) {
            PermissionsScreen(
                onComplete = {
                    navController.navigate(Routes.Home.route) {
                        // Clear permissions and onboarding from back stack
                        popUpTo(Routes.Onboarding.route) { inclusive = true }
                    }
                },
                onBack = {
                    navController.navigateUp()
                }
            )
        }

        // Home screen
        composable(
            route = Routes.Home.route,
            enterTransition = { fadeIn(tween(ANIMATION_DURATION)) },
            exitTransition = { fadeOut(tween(ANIMATION_DURATION / 2)) }
        ) {
            HomeScreen(
                onNavigateToSettings = {
                    navController.navigate(Routes.Settings.route)
                },
                onNavigateToCommands = {
                    navController.navigate(Routes.Commands.route)
                },
                onNavigateToPermissions = {
                    navController.navigate(Routes.Permissions.route)
                }
            )
        }

        // Settings screen
        composable(
            route = Routes.Settings.route,
            enterTransition = { slideInEnterTransition() },
            exitTransition = { slideOutExitTransition() },
            popExitTransition = { slideOutPopExitTransition() }
        ) {
            SettingsScreen(
                onBack = {
                    navController.navigateUp()
                },
                onNavigateToPermissions = {
                    navController.navigate(Routes.Permissions.route)
                }
            )
        }

        // Commands list screen
        composable(
            route = Routes.Commands.route,
            enterTransition = { slideInEnterTransition() },
            exitTransition = { slideOutExitTransition() },
            popExitTransition = { slideOutPopExitTransition() }
        ) {
            CommandsListScreen(
                onBack = {
                    navController.navigateUp()
                },
                onCommandClick = { commandId ->
                    navController.navigate(Routes.CommandDetail(commandId).route)
                }
            )
        }

        // Command detail screen (with argument)
        composable(
            route = Routes.CommandDetail.ROUTE_WITH_ARG,
            arguments = listOf(
                navArgument(Routes.CommandDetail.ARG_COMMAND_ID) {
                    type = NavType.StringType
                }
            ),
            enterTransition = { slideInEnterTransition() },
            exitTransition = { slideOutExitTransition() },
            popExitTransition = { slideOutPopExitTransition() }
        ) { backStackEntry ->
            val commandId = backStackEntry.arguments?.getString(Routes.CommandDetail.ARG_COMMAND_ID)
            // TODO: Implement CommandDetailScreen
            // CommandDetailScreen(
            //     commandId = commandId ?: "",
            //     onBack = { navController.navigateUp() }
            // )
        }
    }
}

/**
 * Slide in from right transition for forward navigation.
 */
private fun AnimatedContentTransitionScope<*>.slideInEnterTransition(): EnterTransition {
    return slideInHorizontally(
        initialOffsetX = { fullWidth -> fullWidth },
        animationSpec = tween(ANIMATION_DURATION)
    ) + fadeIn(tween(ANIMATION_DURATION))
}

/**
 * Slide out to left transition for forward navigation.
 */
private fun AnimatedContentTransitionScope<*>.slideOutExitTransition(): ExitTransition {
    return slideOutHorizontally(
        targetOffsetX = { fullWidth -> -fullWidth / 4 },
        animationSpec = tween(ANIMATION_DURATION)
    ) + fadeOut(tween(ANIMATION_DURATION))
}

/**
 * Slide in from left transition for back navigation.
 */
private fun AnimatedContentTransitionScope<*>.slideInPopEnterTransition(): EnterTransition {
    return slideInHorizontally(
        initialOffsetX = { fullWidth -> -fullWidth / 4 },
        animationSpec = tween(ANIMATION_DURATION)
    ) + fadeIn(tween(ANIMATION_DURATION))
}

/**
 * Slide out to right transition for back navigation.
 */
private fun AnimatedContentTransitionScope<*>.slideOutPopExitTransition(): ExitTransition {
    return slideOutHorizontally(
        targetOffsetX = { fullWidth -> fullWidth },
        animationSpec = tween(ANIMATION_DURATION)
    ) + fadeOut(tween(ANIMATION_DURATION))
}
