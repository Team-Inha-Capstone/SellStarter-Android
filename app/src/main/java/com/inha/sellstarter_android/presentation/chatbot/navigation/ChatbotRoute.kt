package com.inha.sellstarter_android.presentation.chatbot.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.inha.sellstarter_android.presentation.chatbot.ChatbotScreen
import com.inha.sellstarter_android.presentation.chatbot.ChatbotViewModel

@Composable
fun ChatbotRoute(
    modifier: Modifier = Modifier,
    viewModel: ChatbotViewModel = hiltViewModel()
) {
    val chatMessages by viewModel.chatMessages.collectAsState()
    val isTyping by viewModel.isBotTyping.collectAsState()
    var messageText by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.startChatbot()
    }

    DisposableEffect(Unit) {
        onDispose { viewModel.endChatbot() }
    }

    ChatbotScreen(
        modifier = modifier,
        chatMessages = chatMessages,
        isTyping = isTyping,
        messageText = messageText,
        onMessageTextChange = { messageText = it },
        onSendClick = {
            if (messageText.isNotBlank()) {
                viewModel.sendMessage(messageText)
                messageText = ""
            }
        }
    )
}
