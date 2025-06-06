package com.inha.sellstarter_android.presentation.common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inha.sellstarter_android.ui.theme.Grey0
import com.inha.sellstarter_android.ui.theme.Grey100
import com.inha.sellstarter_android.ui.theme.Purple200
import com.inha.sellstarter_android.ui.theme.AppTypography

@Composable
fun OneButton(
    text: String,
    fontColor: Color = Grey0,
    fontStyle: TextStyle = MaterialTheme.typography.headlineMedium,
    buttonBackgroundColor: Color = Purple200,
    radius: Int = 10,
    enabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(radius.dp))
            .background(
                if (enabled) buttonBackgroundColor else Grey100
            )
            .padding(8.dp)
            .clickable(enabled = enabled) { onClick() }
    ) {
        Text(
            text = text,
            style = fontStyle,
            color = if (enabled) fontColor else fontColor,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewStockMindDefaultButton() {
    OneButton(
        text = "다음으로",
        fontColor = Grey0,
        fontStyle = AppTypography.headlineMedium,
        buttonBackgroundColor = Purple200,
        radius = 10,
        onClick = { },
        enabled = true,
        modifier = Modifier.wrapContentSize()
    )
}
