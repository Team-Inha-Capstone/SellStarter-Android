package com.inha.sellstarter_android.presentation.mypage.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.inha.sellstarter_android.presentation.mypage.FontSizeViewModel
import com.inha.sellstarter_android.presentation.navigation.route.AppRoute

fun NavGraphBuilder.myPageNavGraph(
    navController: NavHostController,
    fontSizeViewModel: FontSizeViewModel,
    modifier: Modifier,
) {
    composable(route = AppRoute.MyPage.route) {
        MyPageRoute(
            modifier = modifier,
            fontSizeViewModel = fontSizeViewModel
        )
    }
}