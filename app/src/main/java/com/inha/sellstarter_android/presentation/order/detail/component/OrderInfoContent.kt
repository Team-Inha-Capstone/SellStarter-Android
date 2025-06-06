package com.inha.sellstarter_android.presentation.order.detail.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inha.sellstarter_android.domain.model.OrderDetailInfo
import com.inha.sellstarter_android.domain.model.OrderInfo
import com.inha.sellstarter_android.domain.model.type.OrderStatusType
import com.inha.sellstarter_android.ui.theme.Purple200
import com.inha.sellstarter_android.ui.theme.AppTypography

@Composable
fun OrderInfoContent(
    orderInfo: OrderInfo,
    modifier: Modifier,
) {
    Column(modifier = modifier) {
        Text("주문 정보", style = MaterialTheme.typography.headlineMedium)
        Divider(modifier = Modifier.padding(vertical = 8.dp))

        InfoRow(label = "주문번호", value = orderInfo.orderId)
        InfoRow(label = "플랫폼", value = orderInfo.channelName)
        Spacer(modifier = Modifier.height(8.dp))

        InfoRow(
            label = "주문상태",
            value = orderInfo.orderStatus.displayName,
            valueTextColor = Purple200
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOrderInfoContent() {
    OrderInfoContent(
        orderInfo = OrderInfo(
            orderId = "ORD-20250607-001",
            channelName = "네이버 스마트스토어",
            orderStatus = OrderStatusType.ORDER_COMPLETED
        ),
        modifier = Modifier.padding(horizontal = 24.dp)
    )
}