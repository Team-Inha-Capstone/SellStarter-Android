package com.inha.sellstarter_android.presentation.common.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inha.sellstarter_android.ui.theme.Grey100

@Composable
fun LoadingScreen(
    loadingText: String,
    loadingTextColor: Color = Grey100,
    modifier: Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = loadingText,
            color = loadingTextColor,
            textAlign = TextAlign.Center
        )

    }
}

@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
        LoadingScreen(
            loadingText = "사진을 불러오는 중입니다.",
            loadingTextColor = Color.Gray,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        )
}