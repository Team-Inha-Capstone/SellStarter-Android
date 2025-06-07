package com.inha.sellstarter_android.presentation.home.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.inha.sellstarter_android.presentation.home.HomeScreen
import com.inha.sellstarter_android.presentation.home.HomeViewModel

@Composable
fun HomeRoute(
    modifier: Modifier,
    onNavigateToReport: () -> Unit,
    onNavigateToInventoryRegister: () -> Unit,
    onNavigateToChatbot: () -> Unit,
    onNavigateToOrder: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val homeInfoState by viewModel.homeInfoState.collectAsState()
    val weeklySalesState by viewModel.weeklySalesState.collectAsState()
    val yearlySalesState by viewModel.yearlySalesState.collectAsState()

    HomeScreen(
        modifier = modifier,
        homeInfoState = homeInfoState,
        weeklySalesState = weeklySalesState,
        yearlySalesState = yearlySalesState,
        onNavigateToInventoryRegister = onNavigateToInventoryRegister,
        onNavigateToChatbot = onNavigateToChatbot,
        onNavigateToReport = onNavigateToReport,
        onNavigateToOrder = onNavigateToOrder
    )
}