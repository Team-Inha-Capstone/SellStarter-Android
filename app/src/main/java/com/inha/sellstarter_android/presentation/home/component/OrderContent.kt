package com.inha.sellstarter_android.presentation.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.inha.sellstarter_android.R
import com.inha.sellstarter_android.domain.model.HomeInfo
import com.inha.sellstarter_android.ui.theme.Grey0

@Composable
fun OrderSummaryContent(
    homeInfo: HomeInfo,
    onClickOrderSummary: () -> Unit,
    modifier: Modifier,
) {
    Row(
        modifier = Modifier.padding(horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "오늘의 주문 확인",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = "이동",
            modifier = Modifier
                .padding(start = 4.dp)
                .clickable {
                    onClickOrderSummary()
                }
        )
    }

    Spacer(modifier = Modifier.height(12.dp))

    Box(modifier = modifier) {
        OrderSummaryCard(
            homeInfo = homeInfo,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        )
        Image(
            painter = painterResource(R.drawable.img_cart),
            contentDescription = "card background image",
            modifier = Modifier
                .size(150.dp)
                .padding(start = 30.dp)
                .align(Alignment.BottomEnd)
        )
    }
}

@Composable
fun OrderSummaryCard(
    homeInfo: HomeInfo,
    modifier: Modifier
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        backgroundColor = Grey0,
        modifier = modifier,
        elevation = 2.dp
    ) {
        Column(modifier = Modifier.padding(vertical = 24.dp, horizontal = 16.dp)) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                OrderSummaryItem("신규주문", homeInfo.newOrder, Modifier.weight(1f))
                OrderSummaryItem("피킹완료", homeInfo.pickingCompleted, Modifier.weight(1f))
                OrderSummaryItem("출고완료", homeInfo.shippingCompleted, Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.height(24.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                OrderSummaryItem("취소요청", homeInfo.cancelRequest, Modifier.weight(1f))
                OrderSummaryItem("반품요청", homeInfo.returnRequest, Modifier.weight(1f))
                Spacer(Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun OrderSummaryItem(title: String, count: Int, modifier: Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = "$count",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}