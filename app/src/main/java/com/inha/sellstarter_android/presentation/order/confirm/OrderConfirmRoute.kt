package com.inha.sellstarter_android.presentation.order.confirm

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.inha.sellstarter_android.presentation.common.screen.TitleScreen
import com.inha.sellstarter_android.util.base.UiState
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

@Composable
fun OrderConfirmRoute(
    viewModel: OrderConfirmViewModel = hiltViewModel(),
    onNavigateToDetail: (orderId: String, isFromCompletedTab: Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    // 1) Pager & 탭 인덱스 상태
    var selectedTabIndex by remember { mutableStateOf(0) } // 0 = 신규 주문, 1 = 피킹 완료
    val pagerState = rememberPagerState(initialPage = selectedTabIndex) { 2 }

    // 2) ViewModel 상태 구독
    val newOrdersState by viewModel.newOrderListState.collectAsState()
    val completedPickingsState by viewModel.completedPickingListState.collectAsState()
    val batchState by viewModel.batchCompleteState.collectAsState()

    // 3) 선택된 주문 ID 세트
    val selectedIds = remember { mutableStateOf(setOf<String>()) }

    // 4) Context & CoroutineScope
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(pagerState.currentPage) {
        when (pagerState.currentPage) {
            0 -> viewModel.loadNewOrders()
            1 -> viewModel.loadCompletedPickings()
        }
        selectedTabIndex = pagerState.currentPage
    }

    LaunchedEffect(batchState) {
        when (batchState) {
            is UiState.Failure -> {
                // “NotAllPicked” 메시지가 내려온 경우
                val throwable = (batchState as UiState.Failure)
                if (throwable.message == "NotAllPicked") {
                    Toast.makeText(
                        context,
                        "아직 피킹이 다 안되었습니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    // 다른 에러 메시지가 있으면 일반 오류 토스트
                    Toast.makeText(
                        context,
                        "일괄 피킹 처리 중 오류가 발생했습니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                // 상태 초기화
                viewModel.resetBatchCompleteState()
            }
            is UiState.Success -> {
                val count = (batchState as UiState.Success<Int>).data
                Toast.makeText(
                    context,
                    "${count}건 피킹 완료 처리되었습니다.",
                    Toast.LENGTH_SHORT
                ).show()
                viewModel.resetBatchCompleteState()
            }
            else -> {
                // Idle 또는 Loading인 경우 무시
            }
        }
    }

    OrderConfirmScreen(
        selectedTabIndex = selectedTabIndex,
        pagerState = pagerState,
        newOrdersState = newOrdersState,
        completedPickingsState = completedPickingsState,
        selectedIds = selectedIds.value,
        onTabSelected = { index ->
            coroutineScope.launch { pagerState.animateScrollToPage(index) }
        },
        onItemSelect = { orderId ->
            // 체크박스 클릭 → selectedIds 업데이트
            selectedIds.value = selectedIds.value.toMutableSet()
                .apply {
                    if (!add(orderId)) remove(orderId)
                }
        },
        onOrderItemClick = { orderId, isFromCompleted ->
            val isFromCompleted = (selectedTabIndex == 1)
            onNavigateToDetail(orderId, isFromCompleted)
        },
        onClickCompleteSelected = {
            val idsList = selectedIds.value.toList()
            viewModel.completeSelectedOrders(idsList)
        },
        onSelectAll = { allSummaries ->
            val allIds = allSummaries.map { it.orderId }.toSet()
            selectedIds.value = allIds
        },
        modifier = Modifier.fillMaxSize()
    )
}