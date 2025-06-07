package com.inha.sellstarter_android.presentation.inventory.route

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.inha.sellstarter_android.data.model.request.order.OrderInventoryPickingRequestDto
import com.inha.sellstarter_android.presentation.common.screen.ErrorScreen
import com.inha.sellstarter_android.presentation.common.screen.LoadingScreen
import com.inha.sellstarter_android.presentation.inventory.InventoryViewModel
import com.inha.sellstarter_android.presentation.inventory.scan.component.ValidBarcodeScanDialog
import com.inha.sellstarter_android.presentation.navigation.route.AppRoute
import com.inha.sellstarter_android.presentation.order.detail.OrderDetailViewModel
import com.inha.sellstarter_android.util.base.UiState

@Composable
fun InventoryScanDialogRoute(
    orderId: String,
    barcodeId: String,
    modifier: Modifier = Modifier,
    navController: NavController,
    onBackToOrder: () -> Unit,
    parentEntry: NavBackStackEntry = remember {
        navController.getBackStackEntry(AppRoute.InventoryScan.createRoute(orderId, barcodeId))
    }
) {
    val inventoryViewModel = hiltViewModel<InventoryViewModel>(parentEntry)
    val orderViewModel = hiltViewModel<OrderDetailViewModel>(parentEntry)

    val inventoryDetailState by inventoryViewModel.inventoryDetailState.collectAsState()

    LaunchedEffect(barcodeId) {
        inventoryViewModel.getInventoryDetail(barcodeId)
    }

    when (val state = inventoryDetailState) {
        is UiState.Success -> {
            val inventory = state.data
            ValidBarcodeScanDialog(
                inventory = inventory,
                quantity = state.data.quantity,
                onDismiss = onBackToOrder,
                onConfirm = {
                    orderViewModel.completeSinglePicking(
                        orderId = orderId,
                        request = OrderInventoryPickingRequestDto(barcodeId = barcodeId)
                    )
                    onBackToOrder()
                }
            )
        }

        is UiState.Loading -> {
            LoadingScreen(
                loadingText = "재고 스캔 결과를 불러오는 중입니다...",
                modifier = modifier.fillMaxSize()
            )
        }

        is UiState.Failure -> {
            ErrorScreen("재고 정보를 불러오지 못했습니다.")
        }
    }
}
