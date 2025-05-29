package com.inha.sellstarter_android.presentation.chatbot

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ChatbotRoute(
    modifier: Modifier = Modifier,
    viewModel: ChatbotViewModel = hiltViewModel()
) {
    ChatbotScreen(
        modifier = modifier,
        viewModel = viewModel
    )
}
