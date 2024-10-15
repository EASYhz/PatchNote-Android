package com.easyhz.patchnote.ui.navigation.home

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import androidx.navigation.toRoute
import com.easyhz.patchnote.core.designSystem.util.transition.SlideDirection
import com.easyhz.patchnote.core.designSystem.util.transition.enterSlide
import com.easyhz.patchnote.core.designSystem.util.transition.exitSlide
import com.easyhz.patchnote.core.model.filter.FilterParam
import com.easyhz.patchnote.ui.navigation.dataManagement.navigateToDataManagement
import com.easyhz.patchnote.ui.navigation.defect.navigateToDefectDetail
import com.easyhz.patchnote.ui.navigation.defect.navigateToDefectEntry
import com.easyhz.patchnote.ui.screen.filter.FilterScreen
import com.easyhz.patchnote.ui.screen.home.HomeScreen

internal fun NavGraphBuilder.homeGraph(
    navController: NavController
) {
    composable<Home>(
        typeMap = Home.typeMap,
        enterTransition = { fadeIn(animationSpec = tween(300)) },
        exitTransition = { fadeOut(animationSpec = tween(300)) },
        popEnterTransition = { fadeIn(animationSpec = tween(300)) },
        popExitTransition = { exitSlide(SlideDirection.End) }
    ) {
        val args = it.toRoute<Home>()
        HomeScreen(
            searchParam = args.searchParam,
            navigateToDataManagement = navController::navigateToDataManagement,
            navigateToDefectEntry = navController::navigateToDefectEntry,
            navigateToFilter = navController::navigateToFilter,
            navigateToDefectDetail = navController::navigateToDefectDetail
        )
    }
    composable<Filter>(
        typeMap = Filter.typeMap,
        enterTransition = { enterSlide(SlideDirection.Up) },
        exitTransition = { exitSlide(SlideDirection.Down) },
        popEnterTransition = { enterSlide(SlideDirection.Up) },
        popExitTransition = { exitSlide(SlideDirection.Down) }
    ) {
        val navOptions = navOptions {
            popUpTo(navController.graph.id) { inclusive = true }
        }
        val args = it.toRoute<Filter>()
        FilterScreen(
            filterParam = args.filterParam,
            navigateToUp = navController::navigateUp,
            navigateToHome = { item -> navController.navigateToHome(searchParam = item, navOptions = navOptions) }
        )
    }
}

fun NavController.navigateToHome(searchParam: LinkedHashMap<String, String> = linkedMapOf(), navOptions: NavOptions? = null) {
    navigate(Home(searchParam), navOptions)
}

fun NavController.navigateToFilter(filterParam: FilterParam) {
    navigate(Filter(filterParam = filterParam))
}