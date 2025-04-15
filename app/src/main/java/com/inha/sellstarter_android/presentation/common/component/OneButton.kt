package com.inha.sellstarter_android.presentation.common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.contentcapture.ContentCaptureManager.Companion.isEnabled
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inha.sellstarter_android.ui.theme.Grey0
import com.inha.sellstarter_android.ui.theme.Grey100
import com.inha.sellstarter_android.ui.theme.Grey900
import com.inha.sellstarter_android.ui.theme.Purple200
import com.inha.sellstarter_android.ui.theme.Typography

@Composable
fun OneButton(
    text: String,
    fontColor: Color = Grey0,
    fontStyle: TextStyle = Typography.headlineMedium,
    buttonBackgroundColor: Color = Purple200,
    radius: Int = 20,
    width: Int = 10,
    height: Int = 60,
    enabled : Boolean,
    onClick: () -> Unit,
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(radius.dp))
            .background(
                if (enabled) buttonBackgroundColor else Grey100
            )
            .width(width.dp)
            .height(height.dp)
            .clickable(enabled = enabled) { onClick() }
    ) {
        Text(
            text = text,
            style = fontStyle,
            color =  if (enabled) fontColor else Grey900,
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
            fontStyle = Typography.headlineMedium,
            buttonBackgroundColor = Purple200,
            radius = 10,
            width = 330,
            height = 60,
            onClick = { },
            enabled = true,
            modifier = Modifier.wrapContentSize()
        )
}
