package com.inha.sellstarter.presentation.chatbot.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.inha.sellstarter.presentation.navigation.route.AppRoute

fun NavGraphBuilder.chatbotNavGraph(
    navController: NavHostController,
    modifier: Modifier,
) {
    composable(route = AppRoute.Chatbot.route) {
        ChatbotRoute(
            modifier = modifier,
        )
    }
}
