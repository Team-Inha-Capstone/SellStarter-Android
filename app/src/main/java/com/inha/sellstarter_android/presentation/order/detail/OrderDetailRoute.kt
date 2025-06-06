package com.inha.sellstarter_android.presentation.order.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.inha.sellstarter_android.domain.model.OrderDetailInfo
import com.inha.sellstarter_android.presentation.common.screen.ErrorScreen
import com.inha.sellstarter_android.presentation.common.screen.LoadingScreen
import com.inha.sellstarter_android.util.base.UiState

@Composable
fun OrderDetailRoute(
    orderId: String,
    isFromCompleted: Boolean,
    onNavigateOrderList: () -> Unit,
    onNavigateToScan: (orderId: String, barcode: String) -> Unit,
    viewModel: OrderDetailViewModel = hiltViewModel()
) {
    // 1) ViewModel 상태 구독
    val detailState by viewModel.orderDetailState.collectAsState()

    // 2) 최초 로드시 loadOrderDetail(orderId) 호출
    LaunchedEffect(orderId) {
        viewModel.loadOrderDetail(orderId)
    }

    // 3) 상태 분기: 로딩, 에러, 데이터 성공이면 OrderDetailScreen으로 데이터 전달
    when (detailState) {
        is UiState.Loading -> {
            LoadingScreen(
                loadingText = "주문 상세 정보를 불러오는 중입니다...",
                modifier = Modifier.fillMaxSize()
            )
        }

        is UiState.Failure -> {
            ErrorScreen("주문 상세 정보를 불러오는데 실패했습니다.")
        }

        is UiState.Success -> {
            val orderDetailInfo = (detailState as UiState.Success<OrderDetailInfo>).data

            OrderDetailScreen(
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