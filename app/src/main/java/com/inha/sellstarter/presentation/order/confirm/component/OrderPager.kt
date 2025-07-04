package com.inha.sellstarter.presentation.order.confirm.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.inha.sellstarter.domain.model.OrderListPage
import com.inha.sellstarter.domain.model.OrderSummary
import com.inha.sellstarter.presentation.common.screen.ErrorScreen
import com.inha.sellstarter.presentation.common.screen.LoadingLottieScreen
import com.inha.sellstarter.util.base.UiState

// 탭과 연동되는 pager
@Composable
fun OrderPager(
    pagerState: PagerState,
    selectedTabIndex: Int,
    selectedIds: Set<String>,
    onItemSelect: (String) -> Unit,
    onSelectAll: (List<OrderSummary>) -> Unit,
    onOrderItemClick: (String, Boolean) -> Unit,
    newOrdersState: UiState<OrderListPage>,
    completedPickingsState: UiState<OrderListPage>,
    newPage: Int,
    newTotalPages: Int,
    donePage: Int,
    doneTotalPages: Int,
    onLoadNew: (Int) -> Unit,
    onLoadDone: (Int) -> Unit,
) {
    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxSize()
            .testTag("OrderPager"),
    ) { page ->
        when (page) {
            0 -> { // 신규 주문
                when (val state = newOrdersState) {
                    is UiState.Loading ->
                        LoadingLottieScreen(
                            loadingText = "신규 주문 목록을 불러오는 중 입니다...",
                            modifier = Modifier.fillMaxSize(),
                        )

                    is UiState.Failure ->
                        ErrorScreen(
                            errorText = "신규 주문 목록을 불러오는 중 오류가 발생하였습니다.\n잠시 후 다시 시도해주세요.",
                        )

                    is UiState.Success ->
                        OrderPageContent(
                            orders = state.data.orders,
                            currentPage = newPage,
                            totalPages = newTotalPages,
                            onLoadPage = onLoadNew,
                            currentTabIndex = 0,
                            selectedIds = selectedIds,
                            onItemSelect = onItemSelect,
                            onSelectAll = { onSelectAll(state.data.orders) },
                            onOrderItemClick = onOrderItemClick,
                        )
                }
            }

            1 -> { // 피킹 완료
                when (val state = completedPickingsState) {
                    is UiState.Loading ->
                        LoadingLottieScreen(
                            loadingText = "피킹 완료 목록을 불러오는 중 입니다...",
                            modifier = Modifier.fillMaxSize(),
                        )

                    is UiState.Failure ->
                        ErrorScreen(
                            errorText = "피킹 완료 목록을 불러오는 중 오류가 발생하였습니다.\n잠시 후 다시 시도해주세요.",
                        )

                    is UiState.Success ->
                        OrderPageContent(
                            orders = state.data.orders,
                            currentPage = donePage,
                            totalPages = doneTotalPages,
                            onLoadPage = onLoadDone,
                            currentTabIndex = 1,
                            selectedIds = selectedIds,
                            onItemSelect = onItemSelect,
                            onSelectAll = { onSelectAll(state.data.orders) },
                            onOrderItemClick = onOrderItemClick,
                        )
                }
            }
        }
    }
}
