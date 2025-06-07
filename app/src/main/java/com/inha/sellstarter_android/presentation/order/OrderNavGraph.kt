package com.inha.sellstarter_android.presentation.order

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.inha.sellstarter_android.presentation.navigation.route.AppRoute
import com.inha.sellstarter_android.presentation.order.route.OrderConfirmRoute
import com.inha.sellstarter_android.presentation.order.route.OrderDetailRoute

fun NavGraphBuilder.orderNavGraph(
    navController: NavHostController,
    modifier: Modifier
) {
    // 주문 목록
    composable(route = AppRoute.Order.route) {
        OrderConfirmRoute(
            modifier = modifier,
            onNavigateToDetail = { orderId, isFromCompleted ->
                navController.navigate(
                    AppRoute.OrderDetail.createRoute(orderId, isFromCompleted)
                )
            }
        )
    }

    // 주문 상세
    composable(
        route = AppRoute.OrderDetail("", false).route,
        arguments = AppRoute.OrderDetail.navArguments()
    ) { backStackEntry ->
        val orderId = backStackEntry.arguments?.getString(AppRoute.OrderDetail.ORDER_ID) ?: ""
        val isFromCompleted =
            backStackEntry.arguments?.getBoolean(AppRoute.OrderDetail.IS_FROM_COMPLETED) ?: false

        OrderDetailRoute(
            modifier = modifier,
            orderId = orderId,
            isFromCompleted = isFromCompleted,
            onNavigateOrderList = {
                navController.navigate(AppRoute.Order.route) {
                    popUpTo(AppRoute.Order.route) { inclusive = true }
                }
            },
            onNavigateToScan = { orderId, barcodeId ->
                navController.navigate(
                    AppRoute.InventoryScan.createRoute(orderId, barcodeId)
                )
            }
        )
    }
}
