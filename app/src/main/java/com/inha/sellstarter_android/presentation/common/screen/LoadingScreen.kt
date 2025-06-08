package com.inha.sellstarter_android.presentation.common.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.inha.sellstarter_android.ui.theme.AppTypography
import com.inha.sellstarter_android.ui.theme.Grey0
import com.inha.sellstarter_android.ui.theme.Grey100
import com.inha.sellstarter_android.ui.theme.Purple200


@Composable
fun LoadingScreen(
    loadingText: String,
    loadingTextColor: Color = Grey100,
    modifier: Modifier
) {

    Column(
        modifier = modifier.background(Grey0),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        CircularProgressIndicator(
            color = Purple200
        )

        Spacer(modifier = Modifier.height(16.dp))


        Text(
            text = loadingText,
            color = loadingTextColor,
            textAlign = TextAlign.Center
        )

    }
}