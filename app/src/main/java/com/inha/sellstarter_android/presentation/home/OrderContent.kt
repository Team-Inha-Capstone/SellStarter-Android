package com.inha.sellstarter_android.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.inha.sellstarter_android.ui.theme.Typography

@Composable
fun OrderSummaryContent(modifier : Modifier) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "오늘의 주문 확인",
            style = Typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Icon(
            imageVector = Icons.Default.PlayArrow,
            contentDescription = "이동",
            modifier = Modifier.padding(start = 4.dp)
        )
    }
    Spacer(modifier = Modifier.height(12.dp))
    OrderSummaryCard(
       modifier = Modifier
           .fillMaxWidth()
           .height(145.dp)
   )
}

@Composable
fun OrderSummaryCard(modifier : Modifier){
    Card(
        shape = RoundedCornerShape(16.dp),
        backgroundColor = Color.White, // 원하는 색으로 설정
        modifier = modifier,
        elevation = 4.dp // 카드의 elevation 설정
    ) {
        Column(modifier = Modifier.padding(vertical = 24.dp, horizontal = 16.dp)) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                OrderSummaryItem("신규주문", "12", Modifier.weight(1f))
                OrderSummaryItem("피킹완료", "12", Modifier.weight(1f))
                OrderSummaryItem("출고완료", "12", Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.height(24.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                OrderSummaryItem("취소요청", "1", Modifier.weight(1f))
                OrderSummaryItem("반품요청", "1", Modifier.weight(1f))
                Spacer(Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun OrderSummaryItem(title: String, count: String, modifier: Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = title,
            style = Typography.bodySmall
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = count,
            style = Typography.titleMedium
        )
    }
}