package com.inha.sellstarter_android.presentation.order.confim.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.inha.sellstarter_android.domain.model.Order

@Composable
fun OrderPageContent(
    orders: List<Order>,
    selectedIds: Set<Int>,
    onItemSelect: (Int) -> Unit,
    onSelectAll: () -> Unit
) {
    Column {
        OrderActionButtons(
            isEnabled = selectedIds.isNotEmpty(),
            onSelectAll = onSelectAll,
            onSubmit = {
                // 서버통신 API
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        OrderList(
            orders = orders,
            selectedIds = selectedIds,
            onItemSelect = onItemSelect
        )
    }
}