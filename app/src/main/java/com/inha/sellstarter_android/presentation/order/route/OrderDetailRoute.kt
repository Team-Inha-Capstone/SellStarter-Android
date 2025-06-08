package com.inha.sellstarter_android.presentation.order.route

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.inha.sellstarter_android.presentation.common.screen.ErrorScreen
import com.inha.sellstarter_android.presentation.common.screen.LoadingLottieScreen
import com.inha.sellstarter_android.presentation.order.detail.OrderDetailScreen
import com.inha.sellstarter_android.presentation.order.detail.OrderDetailViewModel
import com.inha.sellstarter_android.util.base.UiState

@Composable
fun OrderDetailRoute(
    modifier: Modifier = Modifier,
    orderId: String,
    isFromCompleted: Boolean,
    onNavigateOrderList: () -> Unit,
    onNavigateToScan: (orderId: String, barcode: String) -> Unit,
    viewModel: OrderDetailViewModel = hiltViewModel()
) {

    val detailState by viewModel.orderDetailState.collectAsState()

    LaunchedEffect(orderId) {
        viewModel.loadOrderDetail(orderId)
    }

    when (val state = detailState) {
        is UiState.Loading -> LoadingLottieScreen(
            loadingText = "주문 상세 정보를 불러오는 중입니다...",
            modifier = Modifier.fillMaxSize()
        )

        is UiState.Failure -> ErrorScreen(
            errorText = "주문 상세 정보를 불러오지 못했습니다."
        )

        is UiState.Success -> {
            val orderDetailInfo = state.data

            OrderDetailScreen(
                modifier = modifier,
                orderDetailInfo = orderDetailInfo,
                isFromCompletedTab = isFromCompleted,
                onNavigateToScan = onNavigateToScan,
                onCompletePicking = {
                    viewModel.completeOrderPickings(orderId)
                    onNavigateOrderList()
                },
                onCompleteShipping = {
                    viewModel.confirmOrderShipment(orderId)
                    onNavigateOrderList()
                },
                onCancelComplete = {
                    viewModel.cancelOrder(orderId)
                    onNavigateOrderList()
                }
            )
        }
    }
}
