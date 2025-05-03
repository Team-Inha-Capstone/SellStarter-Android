package com.inha.sellstarter_android.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inha.sellstarter_android.domain.ShoppingMallType
import com.inha.sellstarter_android.domain.Users
import com.inha.sellstarter_android.presentation.home.component.HomeFeatureContent
import com.inha.sellstarter_android.presentation.home.component.OrderStatisticsContent
import com.inha.sellstarter_android.presentation.home.component.OrderSummaryContent
import com.inha.sellstarter_android.ui.theme.Grey0
import com.inha.sellstarter_android.ui.theme.Grey100
import com.inha.sellstarter_android.ui.theme.Purple200
import com.inha.sellstarter_android.ui.theme.Typography

@Composable
fun HomeScreen(
    users: Users,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "스토어 ${users.storeName}",
                    style = Typography.headlineSmall,
                    modifier = Modifier.weight(1f)
                )

                IconButton(onClick = { /* 알림 클릭 로직 */ }) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "알림",
                        tint = Purple200
                    )
                }

                IconButton(onClick = { /* 설정 클릭 로직 */ }) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "설정",
                        tint = Grey100
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            OrderSummaryContent(modifier = Modifier)

            Spacer(modifier = Modifier.height(24.dp))

            HomeFeatureContent(
                isDataAnalyticsSubscribed = false,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            OrderStatisticsContent(modifier = Modifier.fillMaxWidth())

            Spacer(modifier = Modifier.height(80.dp)) // FAB 가려지지 않도록 여유 padding
        }

        FloatingActionButton(
            onClick = { /*챗봇이동*/ },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(90.dp),
            shape = CircleShape,
            backgroundColor = Purple200
        ) {
            Text(
                text = "AI\n챗봇",
                color = Grey0,
                style = Typography.bodyLarge,
                textAlign = TextAlign.Center
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen(
        users = Users(1, "듀가나디 잡화점", ShoppingMallType.HOUSEHOLD_GOODS),
        modifier = Modifier.fillMaxSize()
    )
}