package com.inha.sellstarter_android.presentation.chatbot

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Canvas
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inha.sellstarter_android.presentation.model.ChatMessage
import com.inha.sellstarter_android.ui.theme.Grey100
import com.inha.sellstarter_android.ui.theme.Purple200
import com.inha.sellstarter_android.ui.theme.Typography

@Composable
fun ChatMessageItem(chatMessage: ChatMessage) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = if (chatMessage.isUser) Arrangement.End else Arrangement.Start
    ) {
        Column {
            Box(
                modifier = Modifier
                    .background(
                        if (chatMessage.isUser) Purple200 else Grey100,
                        shape = RoundedCornerShape(12.dp)
                    )
            ) {
                Text(
                    text = chatMessage.message,
                    color = Color.White,
                    style = Typography.labelSmall,
                    modifier = Modifier.padding(8.dp)
                )
            }
            if (chatMessage.isUser) TriangleArrow(
                modifier = Modifier
                    .padding(end = 16.dp)
                    .align(Alignment.End),
                isUser = true
            )
            else TriangleArrow(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .align(Alignment.Start),
                isUser = false
            )
        }
    }
}

@Composable
fun TriangleArrow(modifier: Modifier, isUser: Boolean) {
    Canvas(modifier = modifier.padding(bottom = 4.dp)) {
        val path = Path().apply {
            if (isUser) {
                moveTo(16f, 0f)
                lineTo(16f, 16f)
                lineTo(-5f, -5f)
            } else {
                moveTo(0f, 0f)
                lineTo(0f, 16f)
                lineTo(16f, 0f)
                close()
            }
        }
        drawPath(path, color = if (isUser) Purple200 else Grey100) // 꼬리 색상
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewChatMessageItem() {
    Column {
        ChatMessageItem(
            ChatMessage("안녕하세요, 어떤 도움이 필요하세요?", false)
        )
        ChatMessageItem(
            ChatMessage("니트어딧어?", true)
        )
    }
}