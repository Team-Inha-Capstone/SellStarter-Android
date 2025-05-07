package com.inha.sellstarter_android.presentation.order.confim.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.inha.sellstarter_android.domain.model.Order
import com.inha.sellstarter_android.domain.model.OrderStatus

@Composable
fun OrderPager(
    pagerState: PagerState,
    orders: List<Order>,
    selectedIds: Set<Int>,
    onItemSelect: (Int) -> Unit,
    onSelectAll: (List<Order>) -> Unit
) {
    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .padding(top = 24.dp)
    ) { page ->
        val status = if (page == 0) OrderStatus.NEW else OrderStatus.PICKED
        val filteredOrders = orders.filter { it.status == status }

        OrderPageContent(
            orders = filteredOrders,
            selectedIds = selectedIds,
            onItemSelect = onItemSelect,
            onSelectAll = { onSelectAll(filteredOrders) }
        )
    }
}