package com.inha.sellstarter_android.presentation.order.confirm.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.inha.sellstarter_android.domain.model.OrderSummary
import com.inha.sellstarter_android.ui.theme.Grey100

/**
 * 주문 페이지 콘텐츠: 전체선택/버튼 + 주문 목록
 */
@Composable
fun OrderPageContent(
    orders: List<OrderSummary>,
    currentTabIndex: Int,
    selectedIds: Set<String>,
    onItemSelect: (String) -> Unit,
    onSelectAll: () -> Unit,
    onOrderItemClick: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {

        OrderActionButtons(
            isEnabled = selectedIds.isNotEmpty(),
            currentTabIndex = currentTabIndex,
            onSelectAll = onSelectAll,
            onSubmit = {
                // TODO: 선택된 주문을 서버로 전송하는 API 호출
            }
        )

        OrderList(
            orders = orders,
            selectedIds = selectedIds,
            onItemSelect = onItemSelect,
            onOrderItemClick = onOrderItemClick
        )
    }
}