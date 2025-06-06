package com.inha.sellstarter_android.presentation.order.confim

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inha.sellstarter_android.domain.model.Order
import com.inha.sellstarter_android.domain.model.OrderStatus
import com.inha.sellstarter_android.presentation.common.screen.TitleScreen
import com.inha.sellstarter_android.presentation.order.confim.component.OrderPager
import com.inha.sellstarter_android.presentation.order.confim.component.OrderTabRow
import com.inha.sellstarter_android.ui.theme.SellStarterAndroidTheme

@Composable
fun OrderConfirmScreen(
    modifier: Modifier = Modifier,
    onOrderItemClick: () -> Unit,
) {
    val pagerState = rememberPagerState(initialPage = 0) { 2 }
    var selectedTabIndex by remember { mutableStateOf(0) }
    val selectedIds = remember { mutableStateOf<Set<Int>>(emptySet()) }

    val dummyOrders = remember {
        listOf(
            Order(1, "미니 드레스 032F, 후드집업 회색, 트렌치 코트", "", "2025.03.20", "N", OrderStatus.NEW),
            Order(2, "미니 드레스 032F", "", "2025.03.27", "N", OrderStatus.NEW),
            Order(3, "미니 드레스 032F", "", "2025.04.10", "N", OrderStatus.PICKED),
        )
    }

    val onItemSelect: (Int) -> Unit = { id ->
        selectedIds.value = selectedIds.value.toMutableSet().apply {
            if (!add(id)) remove(id)
        }
    }

    Column(modifier = modifier.fillMaxSize()) {
        TitleScreen(title = "주문 확인")

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {

            OrderTabRow(
                selectedIndex = selectedTabIndex,
                onTabSelected = { selectedTabIndex = it }
            )

            LaunchedEffect(selectedTabIndex) {
                pagerState.animateScrollToPage(selectedTabIndex)
            }

            OrderPager(
                pagerState = pagerState,
                orders = dummyOrders,
                selectedIds = selectedIds.value,
                onItemSelect = onItemSelect,
                onSelectAll = { filtered ->
                    selectedIds.value = filtered.map { it.id }.toSet()
                },
                onOrderItemClick = onOrderItemClick
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

