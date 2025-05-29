package com.inha.sellstarter_android.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigateToInventoryRegister: () -> Unit,
    onNavigateToChatbot: () -> Unit,
    modifier: Modifier
) {

    HomeScreen(
        modifier = modifier,
        onClickInventoryRegister = onNavigateToInventoryRegister,
        onClickChatbot = onNavigateToChatbot,
        viewModel = viewModel
    )
}