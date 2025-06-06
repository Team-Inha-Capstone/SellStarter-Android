package com.inha.sellstarter_android.presentation.order.confirm.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.inha.sellstarter_android.domain.model.OrderListPage
import com.inha.sellstarter_android.domain.model.OrderSummary
import com.inha.sellstarter_android.presentation.common.screen.ErrorScreen
import com.inha.sellstarter_android.presentation.common.screen.LoadingScreen
import com.inha.sellstarter_android.presentation.order.confirm.OrderConfirmViewModel
import com.inha.sellstarter_android.util.base.UiState

// 탭과 연동되는 pager
@Composable
fun OrderPager(
    pagerState: PagerState,
    selectedTabIndex: Int,
    selectedIds: Set<String>,
    onItemSelect: (String) -> Unit,
    onSelectAll: (List<OrderSummary>) -> Unit,
    onOrderItemClick: (String) -> Unit,
    newOrdersState: UiState<OrderListPage>,
    completedPickingsState: UiState<OrderListPage>,
    onClickCompleteSelected: () -> Unit,
    viewModel: OrderConfirmViewModel = hiltViewModel()
) {
    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize()
    ) { page ->
        when (page) {
            0 -> {
                // “신규 주문” 탭
                when (newOrdersState) {
                    is UiState.Loading -> LoadingScreen(
                        loadingText = "신규 주문 목록을 불러오는 중입니다...",
                        modifier = Modifier.fillMaxSize()
                    )

                    is UiState.Failure -> ErrorScreen(
                        errorText = newOrdersState.message
                            ?: "신규 주문을 불러오는 중 오류가 발생했습니다."
                    )

                    is UiState.Success -> {
                        val summaries: List<OrderSummary> = newOrdersState.data.orders
                        OrderPageContent(
                            orders = summaries,
                            selectedIds = selectedIds,
                            currentTabIndex = selectedTabIndex,
                            onItemSelect = onItemSelect,
                            onSelectAll = { onSelectAll(summaries) },
                            onOrderItemClick = onOrderItemClick
                        )
                    }
                }
            }

            1 -> {
                // “피킹 완료” 탭
                when (completedPickingsState) {
                    is UiState.Loading -> LoadingScreen(
                        loadingText = "피킹 완료 주문 목록을 불러오는 중입니다...",
                        modifier = Modifier.fillMaxSize()
                    )

                    is UiState.Failure -> ErrorScreen(
                        errorText = completedPickingsState.message
                            ?: "피킹 완료 주문을 불러오는 중 오류가 발생했습니다."
                    )

                    is UiState.Success -> {
                        val summaries: List<OrderSummary> = completedPickingsState.data.orders
                        OrderPageContent(
                            orders = summaries,
                            selectedIds = selectedIds,
                            currentTabIndex = selectedTabIndex,
                            onItemSelect = onItemSelect,
                            onSelectAll = { onSelectAll(summaries) },
                            onOrderItemClick = onOrderItemClick
                        )
                    }
                }
            }
        }
    }
}
