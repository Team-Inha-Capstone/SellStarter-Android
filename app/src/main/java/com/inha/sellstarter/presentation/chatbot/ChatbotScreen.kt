package com.inha.sellstarter.presentation.chatbot

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
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inha.sellstarter.R
import com.inha.sellstarter.domain.model.ChatMessage
import com.inha.sellstarter.presentation.common.component.DefaultTextField
import com.inha.sellstarter.presentation.common.component.ImageIconButton
import com.inha.sellstarter.presentation.common.screen.TitleScreen
import com.inha.sellstarter.ui.theme.Grey0
import com.inha.sellstarter.ui.theme.Grey20
import com.inha.sellstarter.ui.theme.Grey50
import com.inha.sellstarter.ui.theme.SellStarterAndroidTheme
import java.time.LocalDate

@Composable
fun ChatbotScreen(
    modifier: Modifier,
    chatMessages: List<ChatMessage>,
    isTyping: Boolean,
    messageText: String,
    onMessageTextChange: (String) -> Unit,
    onSendClick: () -> Unit,
) {
    Column(
        modifier =
            modifier
                .fillMaxSize()
                .background(color = Grey0),
    ) {
        TitleScreen(
            title = "💬 채팅",
            description = "재고 및 주문 관련 궁금한 점을 챗봇에게 물어보세요.",
        )
        Text(
            modifier =
                Modifier
                    .padding(vertical = 12.dp)
                    .align(Alignment.CenterHorizontally),
            text = LocalDate.now().toString(),
            color = Grey50,
        )
        LazyColumn(
            modifier =
                Modifier
                    .weight(1f)
                    .fillMaxWidth(),
            reverseLayout = true,
        ) {
            if (isTyping) {
                item {
                    ChatMessageItem(
                        chatMessage = ChatMessage("챗 봇 타이핑 중...", isUser = false),
                    )
                }
            }

            items(chatMessages.reversed()) { chatMessage ->
                ChatMessageItem(chatMessage = chatMessage)
            }
        }

        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Grey0)
                    .border(width = 1.dp, color = Grey20, shape = RoundedCornerShape(8.dp)),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            DefaultTextField(
                value = messageText,
                onValueChange = onMessageTextChange,
                innerTextFieldStyle = MaterialTheme.typography.bodyMedium,
                singleLine = true,
                placeholder = "챗봇에게 물어보세요.\nex) 스토어 내 재고 요약 정보 알려줘",
                borderColor = Color.Transparent,
                modifier =
                    Modifier
                        .weight(1f)
                        .padding(top = 4.dp, bottom = 4.dp, start = 8.dp)
                        .height(85.dp),
            )

            ImageIconButton(
                text = "",
                imagePainter = painterResource(R.drawable.ic_send_white),
                onClick = onSendClick,
                radius = 100,
                enabled = messageText.isNotBlank(),
                imageSize = 24,
                width = 50,
                height = 50,
                modifier =
                    Modifier
                        .padding(vertical = 12.dp, horizontal = 12.dp)
                        .align(Alignment.CenterVertically),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewChatbotScreen() {
    SellStarterAndroidTheme {
        val dummyMessages =
            listOf(
                ChatMessage("안녕하세요! 무엇을 도와드릴까요?", isUser = false),
                ChatMessage("재고 확인하고 싶어요.", isUser = true),
                ChatMessage("어떤 상품의 재고를 확인할까요?", isUser = false),
            )

        ChatbotScreen(
            modifier = Modifier,
            chatMessages = dummyMessages,
            isTyping = true,
            messageText = "샘플 입력 중",
            onMessageTextChange = {},
            onSendClick = {},
        )
    }
}
