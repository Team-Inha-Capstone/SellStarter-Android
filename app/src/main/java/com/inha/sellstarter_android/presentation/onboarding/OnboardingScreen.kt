package com.inha.sellstarter_android.presentation.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inha.sellstarter_android.R
import com.inha.sellstarter_android.presentation.common.component.OneButton
import com.inha.sellstarter_android.ui.theme.Grey900
import com.inha.sellstarter_android.ui.theme.Purple100
import com.inha.sellstarter_android.ui.theme.Typography

@Composable
fun OnboardingScreen(
    modifier: Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(
                R.drawable.bg_onboarding
            ),
            contentDescription = "onboardingImage",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(500.dp)
                .padding(24.dp)
        )

        Text(
            text = "편리한 쇼핑몰 재고 주문 관리",
            color = Grey900,
            style = Typography.headlineMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "솔루션을 통해 통합된 쇼핑몰 주문관리와\n스마트한 재고관리를 체험하세요.",
            color = Grey900,
            style = Typography.bodyMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 8.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        OneButton(
            text = "시작하기",
            buttonBackgroundColor = Purple100,
            fontColor = Grey900,
            enabled = true,
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        )
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewOnboardingScreen() {
    OnboardingScreen(
        modifier = Modifier.fillMaxSize()
    )
}