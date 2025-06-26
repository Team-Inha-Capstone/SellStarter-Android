package com.inha.sellstarter_android.presentation.order.confirm.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.inha.sellstarter_android.R
import com.inha.sellstarter_android.domain.model.OrderSummary
import com.inha.sellstarter_android.presentation.common.screen.EmptyScreen
import com.inha.sellstarter_android.util.paging.PaginationBar

/**
 * 주문 페이지 콘텐츠: 전체선택/버튼 + 주문 목록
 */
@Composable
fun OrderPageContent(
    orders: List<OrderSummary>,
    currentPage: Int,
    totalPages: Int,
    onLoadPage: (Int) -> Unit,
    currentTabIndex: Int,
    selectedIds: Set<String>,
    onItemSelect: (String) -> Unit,
    onSelectAll: () -> Unit,
    onOrderItemClick: (String, Boolean) -> Unit,
    extraBottomAction: @Composable () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // 전체 선택 및 액션 버튼

        extraBottomAction()

        val listState = rememberLazyListState()

        if (orders.isEmpty()) {
            EmptyScreen(
                emptyIcon = R.drawable.ic_empty_shopping_car,
                emptyText = "처리할 주문이 존재하지 않습니다.",
            )
        } else {
            LazyColumn(
                state = listState,
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.weight(0.9f)
            ) {
                items(items = orders) { order ->
                    OrderListItem(
                        order = order,
                        isSelected = selectedIds.contains(order.orderId),
                        currentTabIndex = currentTabIndex,
                        onCheckedChange = { onItemSelect(order.orderId) },
                        onClick = { onOrderItemClick(order.orderId, currentTabIndex == 1) }
                    )
                }
            }

        }

        // 페이징 바
        PaginationBar(
            currentPage = currentPage,
            totalPages = totalPages,
            onPageSelected = onLoadPage,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .weight(0.1f)
                .padding(vertical = 4.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}
