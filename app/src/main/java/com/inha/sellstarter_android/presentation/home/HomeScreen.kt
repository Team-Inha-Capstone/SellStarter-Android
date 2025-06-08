package com.inha.sellstarter_android.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.inha.sellstarter_android.domain.model.HomeInfo
import com.inha.sellstarter_android.domain.model.WeeklySales
import com.inha.sellstarter_android.domain.model.YearlySales
import com.inha.sellstarter_android.presentation.common.screen.ErrorScreen
import com.inha.sellstarter_android.presentation.common.screen.LoadingLottieScreen
import com.inha.sellstarter_android.presentation.home.component.ChatbotFloatingButton
import com.inha.sellstarter_android.presentation.home.component.HomeFeatureContent
import com.inha.sellstarter_android.presentation.home.component.OrderStatisticsContent
import com.inha.sellstarter_android.presentation.home.component.OrderSummaryContent
import com.inha.sellstarter_android.ui.theme.Grey0
import com.inha.sellstarter_android.ui.theme.Grey100
import com.inha.sellstarter_android.ui.theme.Purple200
import com.inha.sellstarter_android.util.base.UiState

@Composable
fun HomeScreen(
    modifier: Modifier,
    homeInfoState: UiState<HomeInfo>,
    weeklySalesState: UiState<WeeklySales>,
    yearlySalesState: UiState<YearlySales>,
    onNavigateToInventoryRegister: () -> Unit,
    onNavigateToChatbot: () -> Unit,
    onNavigateToReport: () -> Unit,
    onNavigateToOrder: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Grey0)
            .padding(vertical = 24.dp, horizontal = 16.dp)
    ) {
        when (homeInfoState) {
            is UiState.Loading -> {
                LoadingLottieScreen(
                    loadingText = "홈 정보를 불러오는 중입니다...",
                    modifier = Modifier.fillMaxSize()
                )
            }

            is UiState.Failure -> {
                ErrorScreen(
                    errorText = "홈 정보를 불러오는데 실패했습니다.",
                )
            }

            is UiState.Success -> {
                val homeInfo = homeInfoState.data
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
                            text = "📦 스토어 : ${homeInfo.shopName}",
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 4.dp)
                        )

                        IconButton(onClick = { /* TODO: 알림 */ }) {
                            Icon(
                                imageVector = Icons.Default.Notifications,
                                contentDescription = "알림",
                                tint = Purple200
                            )
                        }

                        IconButton(onClick = { /* TODO: 설정 */ }) {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = "설정",
                                tint = Grey100
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    OrderSummaryContent(
                        homeInfo = homeInfo,
                        onClickOrderSummary = onNavigateToOrder,
                        modifier = Modifier
                            .wrapContentHeight()
                            .clickable { onNavigateToOrder() }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    HomeFeatureContent(
                        isDataAnalyticsSubscribed = homeInfo.analysisSubscribed,
                        onClickInventoryRegister = onNavigateToInventoryRegister,
                        onClickDataReport = onNavigateToReport,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    if (weeklySalesState is UiState.Success && yearlySalesState is UiState.Success) {
                        OrderStatisticsContent(
                            weeklySales = weeklySalesState.data,
                            yearlySales = yearlySalesState.data,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    Spacer(modifier = Modifier.height(90.dp))
                }

                Box(modifier = Modifier.align(Alignment.BottomEnd)) {
                    ChatbotFloatingButton(
                        onClickChatbot = onNavigateToChatbot,
                        modifier = Modifier.size(90.dp)
                    )
                }
            }
        }
    }
}