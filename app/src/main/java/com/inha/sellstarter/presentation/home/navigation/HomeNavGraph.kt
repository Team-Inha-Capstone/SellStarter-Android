package com.inha.sellstarter.presentation.home.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.inha.sellstarter.presentation.navigation.route.AppRoute

fun NavGraphBuilder.homeNavGraph(
    navController: NavHostController,
    modifier: Modifier,
) {
    composable(route = AppRoute.Home.route) {
        HomeRoute(
            modifier = modifier,
            onNavigateToReport = {
                navController.navigate(AppRoute.DataAnalysisReport.route)
            },
            onNavigateToInventoryRegister = {
                navController.navigate(AppRoute.InventoryRegister.route)
            },
            onNavigateToChatbot = {
                navController.navigate(AppRoute.Chatbot.route)
            },
            onNavigateToOrder = {
                navController.navigate(AppRoute.Order.route)
            },
        )
    }
}
