package com.inha.sellstarter_android.presentation.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.inha.sellstarter_android.ui.theme.Typography

@Composable
fun HomeFeatureContent(
    isDataAnalyticsSubscribed : Boolean,
    modifier: Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        HomeFeatureCard(
            title = "재고 등록",
            description = "새롭게 들어온\n물류의 재고를\n등록할 수 있습니다.",
            modifier = Modifier
                .weight(1f)
                .height(130.dp)
        )

        HomeFeatureCard(
            title = "데이터 분석",
            description = "스토어의 재고와\n판매추이 등을\n분석할 수 있습니다.",
            modifier = Modifier
                .weight(1f)
                .height(130.dp)
        )
    }
}

@Composable
fun HomeFeatureCard(
    title: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        backgroundColor = Color.White, // 원하는 색으로 설정
        modifier = modifier,
        elevation = 4.dp // 카드의 elevation 설정
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                title, style = Typography.headlineMedium,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                description,
                style = Typography.bodySmall,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
