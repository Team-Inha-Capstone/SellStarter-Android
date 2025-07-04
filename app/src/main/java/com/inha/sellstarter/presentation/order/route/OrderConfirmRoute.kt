package com.inha.sellstarter.presentation.order.route

import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.inha.sellstarter.presentation.order.confirm.OrderConfirmScreen
import com.inha.sellstarter.presentation.order.confirm.OrderConfirmViewModel

@Composable
fun OrderConfirmRoute(
    onNavigateToDetail: (String, Boolean) -> Unit,
    viewModel: OrderConfirmViewModel = hiltViewModel(),
    modifier: Modifier,
) {
    // 현재 탭 인덱스 및 PagerState (2개의 탭)
    val selectedTabIndex by viewModel.selectedTab.collectAsState()
    val pagerState: PagerState =
        rememberPagerState(
            pageCount = { 2 },
            initialPage = selectedTabIndex,
        )

    // ViewModel 상태 수집
    val newOrdersState by viewModel.newOrdersState.collectAsState()
    val completedPickingsState by viewModel.doneOrdersState.collectAsState()
    val newPage by viewModel.newPage.collectAsState()
    val newTotalPages by viewModel.newTotalPages.collectAsState()
    val donePage by viewModel.donePage.collectAsState()
    val doneTotalPages by viewModel.doneTotalPages.collectAsState()
    val selectedIds by viewModel.selectedIds.collectAsState()

    // 탭 변경 시 Pager 동기화
    LaunchedEffect(selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }

    LaunchedEffect(pagerState.currentPage) {
        if (pagerState.currentPage != selectedTabIndex) {
            viewModel.onTabSelected(pagerState.currentPage)
        }
    }

    OrderConfirmScreen(
        selectedTabIndex = selectedTabIndex,
        pagerState = pagerState,
        newOrdersState = newOrdersState,
        completedPickingsState = completedPickingsState,
        newPage = newPage,
        newTotalPages = newTotalPages,
        donePage = donePage,
        doneTotalPages = doneTotalPages,
        selectedIds = selectedIds,
        onTabSelected = viewModel::onTabSelected,
        onLoadNew = viewModel::loadNewOrders,
        onLoadDone = viewModel::loadDoneOrders,
        onItemSelect = viewModel::onItemSelect,
        onSelectAll = viewModel::onSelectAll,
        onOrderItemClick = onNavigateToDetail,
        modifier = modifier,
    )
}
