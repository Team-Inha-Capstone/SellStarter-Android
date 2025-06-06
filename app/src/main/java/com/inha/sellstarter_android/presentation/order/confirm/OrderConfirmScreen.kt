package com.inha.sellstarter_android.presentation.order.confirm

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.inha.sellstarter_android.domain.model.OrderListPage
import com.inha.sellstarter_android.domain.model.OrderSummary
import com.inha.sellstarter_android.presentation.common.screen.TitleScreen
import com.inha.sellstarter_android.presentation.order.confirm.component.OrderPager
import com.inha.sellstarter_android.presentation.order.confirm.component.OrderTabRow
import com.inha.sellstarter_android.ui.theme.Grey0
import com.inha.sellstarter_android.ui.theme.SellStarterAndroidTheme
import com.inha.sellstarter_android.util.base.UiState
import kotlinx.coroutines.launch

@Composable
fun OrderConfirmScreen(
    // —— Pager & 탭 인덱스
    selectedTabIndex: Int,
    pagerState: PagerState,

    // —— 각각의 탭에서 보여줄 주문 목록 상태 (UiState<List<OrderSummary>>)
    newOrdersState: UiState<OrderListPage>,
    completedPickingsState: UiState<OrderListPage>,

    // —— 체크박스 선택된 ID 세트
    selectedIds: Set<String>,

    // —— 콜백
    onTabSelected: (Int) -> Unit,
    onItemSelect: (orderId: String) -> Unit,
    onSelectAll: (allSummaries: List<OrderSummary>) -> Unit,
    onOrderItemClick: (orderId: String, isFromCompleted: Boolean) -> Unit,
    onClickCompleteSelected: () -> Unit,

    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Grey0)
    ) {
        TitleScreen(title = "주문 확인")

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {
            OrderTabRow(
                selectedIndex = selectedTabIndex,
                onTabSelected = { index -> onTabSelected(index) },
                modifier = Modifier.padding(vertical = 4.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            OrderPager(
                pagerState = pagerState,
                selectedTabIndex = selectedTabIndex,
                selectedIds = selectedIds,

                // 개별 항목 선택 시
                onItemSelect = { orderId -> onItemSelect(orderId) },

                // 전체 선택 시
                onSelectAll = { allSummaries -> onSelectAll(allSummaries) },

                // 항목 클릭 시 (상세 화면 이동)
                onOrderItemClick = { id ->
                    val isFromCompleted = (pagerState.currentPage == 1)
                    onOrderItemClick(id, isFromCompleted)
                },
                onClickCompleteSelected = {
                    onClickCompleteSelected()
                },
                newOrdersState = newOrdersState,
                completedPickingsState = completedPickingsState
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOrderConfirmScreen() {
    SellStarterAndroidTheme {

    }
}

