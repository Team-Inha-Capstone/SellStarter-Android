package com.inha.sellstarter_android.presentation.order.confirm

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
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


@Composable
fun OrderConfirmScreen(
    selectedTabIndex: Int,
    pagerState: PagerState,
    newOrdersState: UiState<OrderListPage>,
    completedPickingsState: UiState<OrderListPage>,
    newPage: Int,
    newTotalPages: Int,
    donePage: Int,
    doneTotalPages: Int,
    selectedIds: Set<String>,
    onTabSelected: (Int) -> Unit,
    onLoadNew: (Int) -> Unit,
    onLoadDone: (Int) -> Unit,
    onItemSelect: (String) -> Unit,
    onSelectAll: (List<OrderSummary>) -> Unit,
    onOrderItemClick: (String, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Grey0)
    ) {
        TitleScreen(title = "주문 확인",
            description = "스토어에 들어온 주문을 확인할 수 있습니다.")
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding()
        ) {
            OrderTabRow(
                selectedIndex = selectedTabIndex,
                onTabSelected = onTabSelected,
                modifier = Modifier.padding(vertical = 4.dp)
            )
            OrderPager(
                pagerState = pagerState,
                selectedTabIndex = selectedTabIndex,
                selectedIds = selectedIds,
                onItemSelect = onItemSelect,
                onSelectAll = onSelectAll,
                onOrderItemClick = onOrderItemClick,
                newOrdersState = newOrdersState,
                completedPickingsState = completedPickingsState,
                newPage = newPage,
                newTotalPages = newTotalPages,
                donePage = donePage,
                doneTotalPages = doneTotalPages,
                onLoadNew = onLoadNew,
                onLoadDone = onLoadDone,
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

