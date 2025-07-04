package com.inha.sellstarter.presentation.common.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inha.sellstarter.R
import com.inha.sellstarter.ui.theme.Grey0
import com.inha.sellstarter.ui.theme.Grey100
import com.inha.sellstarter.ui.theme.Purple200

@Composable
fun ImageIconButton(
    text: String,
    imagePainter: Painter,
    fontColor: Color = Grey0,
    fontStyle: TextStyle = MaterialTheme.typography.headlineMedium,
    buttonBackgroundColor: Color = Purple200,
    imageSize: Int,
    radius: Int = 10,
    width: Int = 200,
    height: Int = 60,
    enabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier =
            modifier
                .clip(RoundedCornerShape(radius.dp))
                .background(
                    if (enabled) buttonBackgroundColor else Grey100,
                )
                .width(width.dp)
                .height(height.dp)
                .clickable(enabled = enabled) { onClick() },
        contentAlignment = Alignment.Center,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = imagePainter,
                contentDescription = "icon",
                modifier = Modifier.size(imageSize.dp),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewImageIconButton() {
    ImageIconButton(
        text = "",
        imagePainter = painterResource(id = R.drawable.ic_logo_foreground), // drawable에 이미지 필요!
        fontColor = Grey0,
        buttonBackgroundColor = Purple200,
        radius = 100,
        width = 50,
        height = 50,
        enabled = true,
        onClick = {},
        imageSize = 24,
        modifier = Modifier,
    )
}
