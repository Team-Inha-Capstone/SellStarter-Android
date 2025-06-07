package com.inha.sellstarter_android.presentation.chatbot

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.inha.sellstarter_android.R
import com.inha.sellstarter_android.presentation.common.component.DefaultTextField
import com.inha.sellstarter_android.presentation.common.component.ImageIconButton
import com.inha.sellstarter_android.presentation.common.screen.TitleScreen
import com.inha.sellstarter_android.domain.model.ChatMessage
import com.inha.sellstarter_android.ui.theme.Grey0
import com.inha.sellstarter_android.ui.theme.Grey100
import com.inha.sellstarter_android.ui.theme.AppTypography

@Composable
fun ChatbotScreen(
    modifier: Modifier,
    chatMessages: List<ChatMessage>,
    isTyping: Boolean,
    messageText: String,
    onMessageTextChange: (String) -> Unit,
    onSendClick: () -> Unit
) {
    Column(modifier = modifier.fillMaxSize()) {
        TitleScreen(title = "채팅")

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            reverseLayout = true,
        ) {
            if (isTyping) {
                item {
                    ChatMessageItem(
                        chatMessage = ChatMessage("챗 봇 타이핑 중...", isUser = false)
                    )
                }
            }

            items(chatMessages.reversed()) { chatMessage ->
                ChatMessageItem(chatMessage = chatMessage)
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Grey0)
                .border(width = 1.dp, color = Grey100, shape = RoundedCornerShape(8.dp)),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            DefaultTextField(
                value = messageText,
                onValueChange = onMessageTextChange,
                innerTextFieldStyle = AppTypography.bodyMedium,
                singleLine = true,
                borderColor = Color.Transparent,
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 8.dp, bottom = 8.dp, start = 8.dp)
                    .height(90.dp)
            )

            ImageIconButton(
                text = "",
                imagePainter = painterResource(R.drawable.ic_send_white),
                onClick = onSendClick,
                radius = 100,
                enabled = messageText.isNotBlank(),
                imageSize = 24,
                width = 60,
                height = 60,
                modifier = Modifier
                    .padding(vertical = 24.dp, horizontal = 12.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewChatbotScreen() {
}
