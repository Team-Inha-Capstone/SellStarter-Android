package com.inha.sellstarter_android.presentation.order.route

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.inha.sellstarter_android.presentation.order.confirm.OrderConfirmScreen
import com.inha.sellstarter_android.presentation.order.confirm.OrderConfirmViewModel
import com.inha.sellstarter_android.util.base.UiState
import kotlinx.coroutines.launch

@Composable
fun OrderConfirmRoute(
    viewModel: OrderConfirmViewModel = hiltViewModel(),
    onNavigateToDetail: (orderId: String, isFromCompletedTab: Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    // 탭 & pager 상태
    var selectedTabIndex by rememberSaveable { mutableIntStateOf(0) }
    val pagerState = rememberPagerState(initialPage = selectedTabIndex) { 2 }

    // 주문 상태 구독
    val newOrdersState by viewModel.newOrderListState.collectAsState()
    val completedPickingsState by viewModel.completedPickingListState.collectAsState()
    val batchState by viewModel.batchCompleteState.collectAsState()

    // 선택된 주문 ID 상태
    var selectedIds by rememberSaveable { mutableStateOf(setOf<String>()) }

    LaunchedEffect(pagerState.currentPage) {
        selectedTabIndex = pagerState.currentPage
        when (selectedTabIndex) {
            0 -> viewModel.loadNewOrders()
            1 -> viewModel.loadCompletedPickings()
        }
    }

    LaunchedEffect(batchState) {
        when (batchState) {
            is UiState.Failure -> {
                val message = (batchState as UiState.Failure).message
                Toast.makeText(
                    context,
                    if (message == "NotAllPicked") "아직 피킹이 다 안되었습니다."
                    else "일괄 피킹 처리 중 오류가 발생했습니다.",
                    Toast.LENGTH_SHORT
                ).show()
                viewModel.resetBatchCompleteState()
            }

            is UiState.Success -> {
                val count = (batchState as UiState.Success).data
                Toast.makeText(context, "$count 건 피킹 완료 처리되었습니다.", Toast.LENGTH_SHORT).show()
                viewModel.resetBatchCompleteState()
            }

            else -> Unit
        }
    }

    OrderConfirmScreen(
        modifier = modifier.fillMaxSize(),
        selectedTabIndex = selectedTabIndex,
        pagerState = pagerState,
        newOrdersState = newOrdersState,
        completedPickingsState = completedPickingsState,
        selectedIds = selectedIds,
        onTabSelected = { index ->
            coroutineScope.launch { pagerState.animateScrollToPage(index) }
        },
        onOrderItemClick = { orderId ->
            val isFromCompleted = (selectedTabIndex == 1)
            onNavigateToDetail(orderId, isFromCompleted)
        },
        onItemSelect = { orderId ->
            selectedIds = selectedIds.toMutableSet().apply {
                if (!add(orderId)) remove(orderId)
            }
        },
        onClickCompleteSelected = {
            viewModel.completeSelectedOrders(selectedIds.toList())
        },
        onSelectAll = { allOrders ->
            selectedIds = allOrders.map { it.orderId }.toSet()
        }
    )
}
