package com.inha.sellstarter_android.presentation.common.screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inha.sellstarter_android.R
import com.inha.sellstarter_android.ui.theme.Grey0
import com.inha.sellstarter_android.ui.theme.Grey100

@Composable
fun EmptyScreen(
    emptyText: String,
    emptyTextColor: Color = Grey100,
    @DrawableRes
    emptyIcon: Int = R.drawable.ic_empty_box,
    modifier: Modifier = Modifier
        .fillMaxSize(),
) {
    Column(
        modifier = modifier
            .testTag("Empty"),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(emptyIcon),
            contentDescription = "empty_screen_icon",
            modifier = Modifier.size(120.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = emptyText,
            color = emptyTextColor,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(name = "EmptyScreen Light", showBackground = true)
@Composable
fun EmptyScreenLightPreview() {
    EmptyScreen(
        emptyText = "재고가 없습니다",
        modifier = Modifier
            .fillMaxSize()
            .background(color = Grey0)
    )
}