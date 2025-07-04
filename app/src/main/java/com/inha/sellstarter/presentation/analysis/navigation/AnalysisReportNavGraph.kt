package com.inha.sellstarter.presentation.analysis.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.inha.sellstarter.presentation.navigation.route.AppRoute

fun NavGraphBuilder.analysisReportNavGraph(
    navController: NavHostController,
    modifier: Modifier,
) {
    composable(route = AppRoute.DataAnalysisReport.route) {
        AnalysisReportRoute(modifier = modifier)
    }
}
