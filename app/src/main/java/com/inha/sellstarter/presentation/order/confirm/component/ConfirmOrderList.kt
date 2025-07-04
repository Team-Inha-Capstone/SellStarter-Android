package com.inha.sellstarter.presentation.order.confirm.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.inha.sellstarter.domain.model.OrderSummary

@Composable
fun OrderList(
    orders: List<OrderSummary>,
    selectedIds: Set<String>,
    currentTabIndex: Int,
    onItemSelect: (String) -> Unit,
    onOrderItemClick: (String) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 8.dp),
    ) {
        items(orders, key = { it.orderId }) { order ->
            OrderListItem(
                order = order,
                isSelected = selectedIds.contains(order.orderId),
                currentTabIndex = currentTabIndex,
                onClick = { onOrderItemClick(order.orderId) },
            )
            Divider(color = Color.LightGray)
        }
    }
}
