package com.inha.sellstarter_android.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.inha.sellstarter_android.presentation.analysis.navigation.analysisReportNavGraph
import com.inha.sellstarter_android.presentation.chatbot.navigation.chatbotNavGraph
import com.inha.sellstarter_android.presentation.home.navigation.homeNavGraph
import com.inha.sellstarter_android.presentation.inventory.inventoryNavGraph
import com.inha.sellstarter_android.presentation.mypage.FontSizeViewModel
import com.inha.sellstarter_android.presentation.mypage.navigation.myPageNavGraph
import com.inha.sellstarter_android.presentation.navigation.route.AppRoute
import com.inha.sellstarter_android.presentation.onboarding.onboardingNavGraph
import com.inha.sellstarter_android.presentation.order.orderNavGraph

@Composable
fun MainNavGraph(
    navController: NavHostController,
    fontSizeViewModel: FontSizeViewModel,
    innerPadding: PaddingValues,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = AppRoute.Home.route
    ) {
        onboardingNavGraph(navController, modifier)
        homeNavGraph(navController, modifier)
        myPageNavGraph(navController, fontSizeViewModel, modifier)

        inventoryNavGraph(navController, modifier)

        orderNavGraph(navController, modifier)

        analysisReportNavGraph(navController, modifier)
        chatbotNavGraph(navController,modifier)
    }
}