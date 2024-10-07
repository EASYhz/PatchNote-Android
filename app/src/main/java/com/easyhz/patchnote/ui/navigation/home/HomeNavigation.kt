package com.easyhz.patchnote.ui.navigation.home

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.easyhz.patchnote.core.designSystem.util.transition.SlideDirection
import com.easyhz.patchnote.core.designSystem.util.transition.enterSlide
import com.easyhz.patchnote.core.designSystem.util.transition.exitSlide
import com.easyhz.patchnote.ui.navigation.dataManagement.navigateToDataManagement
import com.easyhz.patchnote.ui.navigation.defect.navigateToDefectEntry
import com.easyhz.patchnote.ui.screen.filter.FilterScreen
import com.easyhz.patchnote.ui.screen.home.HomeScreen

internal fun NavGraphBuilder.homeGraph(
    navController: NavController
) {
    composable<Home>(
        enterTransition = { fadeIn(animationSpec = tween(300)) },
        exitTransition = { fadeOut(animationSpec = tween(300)) },
        popEnterTransition = { fadeIn(animationSpec = tween(300)) },
        popExitTransition = { exitSlide(SlideDirection.End) }
    ) {
        HomeScreen(
            navigateToDataManagement = navController::navigateToDataManagement,
            navigateToDefectEntry = navController::navigateToDefectEntry
        )
    }
    composable<Filter>(
        enterTransition = { enterSlide(SlideDirection.Up) },
        exitTransition = { exitSlide(SlideDirection.Down) },
        popEnterTransition = { enterSlide(SlideDirection.Up) },
        popExitTransition = { exitSlide(SlideDirection.Down) }
    ) {
        FilterScreen(
            navigateToUp = navController::navigateUp
        )
    }
}

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    navigate(Home, navOptions)
}