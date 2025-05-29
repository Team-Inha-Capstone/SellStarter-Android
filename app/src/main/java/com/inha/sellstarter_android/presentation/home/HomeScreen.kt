package com.inha.sellstarter_android.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.inha.sellstarter_android.R
import com.inha.sellstarter_android.domain.model.ShoppingMallType
import com.inha.sellstarter_android.domain.model.Users
import com.inha.sellstarter_android.presentation.common.screen.ErrorScreen
import com.inha.sellstarter_android.presentation.common.screen.LoadingScreen
import com.inha.sellstarter_android.presentation.home.component.HomeFeatureContent
import com.inha.sellstarter_android.presentation.home.component.OrderStatisticsContent
import com.inha.sellstarter_android.presentation.home.component.OrderSummaryContent
import com.inha.sellstarter_android.ui.theme.Grey0
import com.inha.sellstarter_android.ui.theme.Grey100
import com.inha.sellstarter_android.ui.theme.Grey900
import com.inha.sellstarter_android.ui.theme.Purple100
import com.inha.sellstarter_android.ui.theme.Purple200
import com.inha.sellstarter_android.ui.theme.Purple50
import com.inha.sellstarter_android.util.base.UiState

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onClickInventoryRegister: () -> Unit,
    onClickChatbot: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val homeInfoState by viewModel.homeInfoState.collectAsState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 24.dp, horizontal = 16.dp)
    ) {
        when (val state = homeInfoState) {
            is UiState.Loading -> {
                LoadingScreen(
                    loadingText = "홈 정보를 불러오는 중입니다...",
                    modifier = modifier.fillMaxSize()
                )
            }

            is UiState.Failure -> {
                ErrorScreen("홈 정보를 불러오는데 실패했습니다.")
            }

            is UiState.Success -> {
                val homeInfo = state.data

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "스토어 : ${homeInfo.shopName}",
                            style = MaterialTheme.typography.headlineSmall,
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 8.dp)
                        )

                        IconButton(onClick = { }) {
                            Icon(
                                imageVector = Icons.Default.Notifications,
                                contentDescription = "알림",
                                tint = Purple200
                            )
                        }

                        IconButton(onClick = { }) {
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
                        modifier = Modifier
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    HomeFeatureContent(
                        isDataAnalyticsSubscribed = homeInfo.analysisSubscribed,
                        onClickInventoryRegister = onClickInventoryRegister,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    OrderStatisticsContent(modifier = Modifier.fillMaxWidth())

                    Spacer(modifier = Modifier.height(20.dp))
                }

                FloatingActionButton(
                    onClick = onClickChatbot,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .size(90.dp),
                    shape = CircleShape,
                    backgroundColor = Purple200,
                ) {
                    Column {
                        Image(
                            painter = painterResource(R.drawable.ic_chatbot),
                            contentDescription = "chatbot image",
                            modifier = Modifier
                                .size(30.dp)
                                .align(Alignment.CenterHorizontally),
                            colorFilter = ColorFilter.tint(Grey0)
                        )

                        Spacer(modifier = Modifier.size(4.dp))

                        Text(
                            text = "AI 챗봇",
                            color = Grey0,
                            style = MaterialTheme.typography.titleLarge,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}
