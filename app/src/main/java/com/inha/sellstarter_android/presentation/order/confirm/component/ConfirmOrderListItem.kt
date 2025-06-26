package com.inha.sellstarter_android.presentation.order.confirm.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.inha.sellstarter_android.domain.model.OrderSummary
import com.inha.sellstarter_android.ui.theme.AppTypography
import com.inha.sellstarter_android.ui.theme.Grey100

@Composable
fun OrderListItem(
    order: OrderSummary,
    isSelected: Boolean,
    currentTabIndex: Int,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp)
    ) {

        Spacer(modifier = Modifier.size(12.dp))

        Image(
            painter = painterResource(id = order.channel.displayImage),
            contentDescription = "${order.channel.displayName} 아이콘",
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
        ) {
            Text(
                text = order.inventoryItem ?: "",
                maxLines = 2,
                style = MaterialTheme.typography.titleMedium,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "주문번호 : ${order.orderId}",
                style = MaterialTheme.typography.bodyMedium,
                color = Grey100
            )
            Text(
                text = "주문날짜 : ${order.orderDate}",
                style = MaterialTheme.typography.bodyMedium,
                color = Grey100
            )
        }
    }
}
