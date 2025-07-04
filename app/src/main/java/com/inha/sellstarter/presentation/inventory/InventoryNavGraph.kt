package com.inha.sellstarter.presentation.inventory

import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.inha.sellstarter.presentation.inventory.route.InventoryConfirmRoute
import com.inha.sellstarter.presentation.inventory.route.InventoryDetailRoute
import com.inha.sellstarter.presentation.inventory.route.InventoryGridRoute
import com.inha.sellstarter.presentation.inventory.route.InventoryRegisterRoute
import com.inha.sellstarter.presentation.inventory.route.InventoryScanDialogRoute
import com.inha.sellstarter.presentation.inventory.route.InventoryScanErrorRoute
import com.inha.sellstarter.presentation.inventory.route.InventoryScanRoute
import com.inha.sellstarter.presentation.navigation.route.AppRoute

fun NavGraphBuilder.inventoryNavGraph(
    navController: NavHostController,
    modifier: Modifier,
) {
    // 재고 목록
    composable(route = AppRoute.Inventory.route) {
        InventoryGridRoute(
            modifier = modifier,
            onNavigateToDetail = { barcodeId ->
                navController.navigate(AppRoute.InventoryDetail.createRoute(barcodeId))
            },
        )
    }

    // 재고 상세
    composable(
        route = AppRoute.InventoryDetail("").route,
        arguments = AppRoute.InventoryDetail.navArguments(),
    ) { backStackEntry ->
        val barcodeId =
            backStackEntry.arguments?.getString(AppRoute.InventoryDetail.BARCODE_ID) ?: ""

        InventoryDetailRoute(
            barcodeId = barcodeId,
            onBack = { navController.popBackStack() },
            modifier = modifier,
        )
    }

    // 재고 등록
    composable(route = AppRoute.InventoryRegister.route) {
        InventoryRegisterRoute(
            modifier = modifier,
            onRegisterSuccess = {
                navController.navigate(AppRoute.InventoryConfirm.route)
            },
        )
    }

    // 등록 확인 다이얼로그
    composable(route = AppRoute.InventoryConfirm.route) {
        val parentEntry =
            remember {
                navController.getBackStackEntry(AppRoute.InventoryRegister.route)
            }
        InventoryConfirmRoute(
            parentEntry = parentEntry,
            onDismiss = { navController.popBackStack() },
            onConfirm = {
                navController.navigate(AppRoute.Inventory.route) {
                    popUpTo(AppRoute.InventoryRegister.route) { inclusive = true }
                }
            },
        )
    }

    // 스캔
    composable(
        route = AppRoute.InventoryScan("", "").route,
        arguments = AppRoute.InventoryScan.navArguments(),
    ) { backStackEntry ->
        val orderId = backStackEntry.arguments?.getString(AppRoute.InventoryScan.ORDER_ID) ?: ""
        val barcodeId = backStackEntry.arguments?.getString(AppRoute.InventoryScan.BARCODE_ID) ?: ""

        InventoryScanRoute(
            orderId = orderId,
            barcodeId = barcodeId,
            modifier = modifier,
            onNavigateToDialog = {
                navController.navigate(AppRoute.InventoryScanDialog.createRoute(orderId, barcodeId))
            },
            onError = {
                navController.navigate(AppRoute.InventoryScanError.route)
            },
        )
    }

    // 스캔 완료 다이얼로그
    composable(
        route = AppRoute.InventoryScanDialog("", "").route,
        arguments = AppRoute.InventoryScanDialog.navArguments(),
    ) { backStackEntry ->
        val orderId =
            backStackEntry.arguments?.getString(AppRoute.InventoryScanDialog.ORDER_ID) ?: ""
        val barcodeId =
            backStackEntry.arguments?.getString(AppRoute.InventoryScanDialog.BARCODE_ID) ?: ""
        val parentRoute = AppRoute.InventoryScan.createRoute(orderId, barcodeId)
        val parentEntry = remember { navController.getBackStackEntry(parentRoute) }

        InventoryScanDialogRoute(
            orderId = orderId,
            barcodeId = barcodeId,
            modifier = modifier,
            navController = navController,
            parentEntry = parentEntry,
            onBackToOrder = {
                navController.navigate(AppRoute.Order.route) {
                    popUpTo(0) { inclusive = true }
                }
            },
        )
    }

    // 스캔 실패
    composable(AppRoute.InventoryScanError.route) {
        InventoryScanErrorRoute(
            onDismiss = {
                navController.navigate(AppRoute.Order.route) {
                    popUpTo(0) { inclusive = true }
                }
            },
        )
    }
}
