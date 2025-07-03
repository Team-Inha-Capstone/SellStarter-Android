package com.inha.sellstarter_android.presentation.common.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inha.sellstarter_android.ui.theme.Grey900
import com.inha.sellstarter_android.ui.theme.AppTypography
import com.inha.sellstarter_android.ui.theme.Grey0

@Composable
fun TitleScreen(
    title: String,
    backGroundColor: Color = Grey0,
    description: String = "",
    style: TextStyle = MaterialTheme.typography.headlineMedium,
    fontColor: Color = Grey900,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .statusBarsPadding()
        .background(color = backGroundColor)
) {
    Column(
        modifier = modifier
            .padding(
                vertical = 16.dp,
                horizontal = 24.dp
            ),
    ) {
        Text(
            text = title,
            color = fontColor,
            style = style,
            modifier = Modifier.testTag("Title")
        )
        Text(
            text = description,
            color = fontColor,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.testTag("Title_Description")
        )

    }
}

@Preview(showBackground = true)
@Composable
fun TitleScreenPreview() {
    TitleScreen(
        "마이페이지",
        description = "랄라"
    )
}