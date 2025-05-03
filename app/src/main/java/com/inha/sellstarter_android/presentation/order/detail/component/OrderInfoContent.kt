package com.inha.sellstarter_android.presentation.order.detail.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inha.sellstarter_android.ui.theme.Purple200
import com.inha.sellstarter_android.ui.theme.Typography

@Composable
fun OrderInfoContent(
    modifier: Modifier,
) {
    Column(modifier = modifier) {
        Text("주문 정보", style = Typography.headlineMedium)
        Divider(modifier = Modifier.padding(vertical = 8.dp))

        InfoRow(label = "주문번호", value = "AD12453WE")
        InfoRow(label = "플랫폼", value = "네이버 스마트스토어")
        Spacer(modifier = Modifier.height(8.dp))

        InfoRow(label = "주문상태", value = "결제완료", valueTextColor = Purple200)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOrderInfoContent() {
    OrderInfoContent(
        modifier = Modifier.padding(horizontal = 24.dp)
    )
}