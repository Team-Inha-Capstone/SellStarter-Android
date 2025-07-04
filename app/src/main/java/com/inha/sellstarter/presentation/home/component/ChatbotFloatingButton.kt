package com.inha.sellstarter.presentation.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.inha.sellstarter.R
import com.inha.sellstarter.ui.theme.Grey0
import com.inha.sellstarter.ui.theme.Purple200

@Composable
fun ChatbotFloatingButton(
    modifier: Modifier,
    onClickChatbot: () -> Unit,
) {
    FloatingActionButton(
        onClick = onClickChatbot,
        modifier = modifier,
        shape = CircleShape,
        backgroundColor = Purple200,
    ) {
        Column {
            Image(
                painter = painterResource(R.drawable.ic_chatbot),
                contentDescription = "chatbot image",
                modifier =
                    Modifier
                        .size(30.dp)
                        .align(Alignment.CenterHorizontally),
                colorFilter = ColorFilter.tint(Grey0),
            )

            Spacer(modifier = Modifier.size(4.dp))

            Text(
                text = "AI 챗봇",
                color = Grey0,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
            )
        }
    }
}
