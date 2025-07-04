package com.inha.sellstarter.presentation.common.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.inha.sellstarter.ui.theme.AppTypography
import com.inha.sellstarter.ui.theme.Grey0
import com.inha.sellstarter.ui.theme.Grey100

@Composable
fun LoadingLottieScreen(
    loadingText: String,
    loadingTextColor: Color = Grey100,
    modifier: Modifier,
) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.Asset("loading_inventory_lottie.json"),
    )
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
    )

    Column(
        modifier =
            modifier
                .background(Grey0)
                .testTag("LoadingLottie"),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier =
                Modifier
                    .size(180.dp),
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "LOADING ..",
            color = loadingTextColor,
            style = AppTypography.titleLarge,
            textAlign = TextAlign.Center,
        )

        Text(
            text = loadingText,
            color = loadingTextColor,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    LoadingLottieScreen(
        loadingText = "사진을 불러오는 중입니다.",
        loadingTextColor = Color.Gray,
        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp),
    )
}
