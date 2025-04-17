package com.inha.sellstarter_android.presentation.chatbot

import android.annotation.SuppressLint
import android.widget.ImageButton
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
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
import com.inha.sellstarter_android.R
import com.inha.sellstarter_android.presentation.common.component.DefaultTextField
import com.inha.sellstarter_android.presentation.common.component.ImageIconButton
import com.inha.sellstarter_android.presentation.common.component.OneButton
import com.inha.sellstarter_android.presentation.common.component.OutlinedTextField
import com.inha.sellstarter_android.presentation.common.screen.TitleScreen
import com.inha.sellstarter_android.presentation.model.ChatMessage
import com.inha.sellstarter_android.ui.theme.Grey0
import com.inha.sellstarter_android.ui.theme.Grey100
import com.inha.sellstarter_android.ui.theme.Typography


@Composable
fun ChatbotScreen(
    modifier: Modifier
) {
    var messageText by remember { mutableStateOf("무엇이든 물어보세요.\nex)파란 니트 재고 위치 알려줘.") }
    val chatMessages = remember {
        mutableStateListOf<ChatMessage>()
            .apply {
                addAll(
                    listOf(
                        ChatMessage("안녕하세요! 무엇을 도와드릴까요?", isUser = false),
                        ChatMessage("파란 니트 재고 알려주세요.", isUser = true),
                        ChatMessage("네! 노란 선반 2번째에 있습니다.\n더 필요한게 있다면 말씀해주세요.\n언제든지 물어봐주셔도 됩니다! 저는 챗봇입니다.", isUser = false)
                    )
                )
            }
    }

    Column(
        modifier = modifier
    ) {
        TitleScreen(title = "채팅")
        LazyColumn(
            modifier = Modifier.weight(1f),
            reverseLayout = true
        ) {
            items(chatMessages.reversed()) { chatMessages ->
                ChatMessageItem(chatMessage = chatMessages)
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
                onValueChange = { messageText = it },
                innerTextFieldStyle = Typography.bodyMedium,
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
                onClick = {
                    if (messageText.isNotEmpty()) {
                        chatMessages.add(ChatMessage(messageText, isUser = true))
                        messageText = "" // 입력 필드 비우기
                    }
                },
                radius = 100,
                enabled = true,
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
    ChatbotScreen(
        modifier = Modifier.fillMaxSize()
    )
}
