package com.inha.sellstarter_android.presentation.common.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inha.sellstarter_android.ui.theme.Grey900
import com.inha.sellstarter_android.ui.theme.AppTypography
import com.inha.sellstarter_android.ui.theme.Grey0

@Composable
fun TitleScreen(
    title: String,
    style: TextStyle = MaterialTheme.typography.headlineMedium,
    fontColor: Color = Grey900,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .statusBarsPadding()
) {
    Text(
        text = title,
        color = fontColor,
        style = style,
        modifier = modifier
            .padding(
                vertical = 16.dp,
                horizontal = 24.dp
            ),
    )
}

@Preview(showBackground = true)
@Composable
fun TitleScreenPreview() {
    TitleScreen(
        "마이페이지"
    )
}