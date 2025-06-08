package com.inha.sellstarter_android.presentation.common.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.inha.sellstarter_android.ui.theme.Grey100
import com.inha.sellstarter_android.ui.theme.Red200

@Composable
fun ErrorScreen(
    errorText: String,
    errorTextColor: Color = Grey100,
    errorIcon: ImageVector = Icons.Default.Warning,
    iconTint: Color = Red200,
    modifier: Modifier = Modifier.fillMaxSize(),
) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.Asset("error_loading_lottie.json")
    )
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever
    )

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier
                .size(180.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = errorText,
            color = errorTextColor,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    ErrorScreen(
        errorText = "사진을 가져오는데 문제가 발생했습니다.\n잠시 후 다시 시도해주세요.",
        errorIcon = Icons.Default.Warning,
        errorTextColor = Color.Gray,
        iconTint = Color.Red,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    )
}