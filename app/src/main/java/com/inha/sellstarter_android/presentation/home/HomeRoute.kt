package com.inha.sellstarter_android.presentation.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigateToReport: () -> Unit,
    onNavigateToInventoryRegister: () -> Unit,
    onNavigateToChatbot: () -> Unit,
    onNavigateToOrder: () -> Unit,
    modifier: Modifier
) {
    HomeScreen(
        modifier = modifier,
        onClickInventoryRegister = onNavigateToInventoryRegister,
        onClickChatbot = onNavigateToChatbot,
        onClickDataReport = onNavigateToReport,
        onClickOrderSummary = onNavigateToOrder,
        viewModel = viewModel
    )
}