package com.inha.sellstarter_android.presentation.onboarding

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.inha.sellstarter_android.presentation.navigation.route.AppRoute

fun NavGraphBuilder.onboardingNavGraph(
    navController: NavHostController,
    modifier: Modifier
) {
    composable(route = AppRoute.Onboarding.route) {
        OnboardingScreen(
            onClickStart = {
                navController.navigate(AppRoute.ProfileSetup.route)
            },
            modifier = modifier
        )
    }

    composable(route = AppRoute.ProfileSetup.route) {
        ProfileSetupScreen(
            onClickNext = {
                navController.navigate(AppRoute.Home.route) {
                    popUpTo(AppRoute.Onboarding.route) { inclusive = true }
                    launchSingleTop = true
                }
            },
            modifier = modifier
        )
    }
}