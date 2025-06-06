package com.inha.sellstarter_android.presentation.home.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inha.sellstarter_android.R
import com.inha.sellstarter_android.ui.theme.AppTypography
import com.inha.sellstarter_android.ui.theme.Grey0
import com.inha.sellstarter_android.ui.theme.SellStarterAndroidTheme

@Composable
fun HomeFeatureContent(
    isDataAnalyticsSubscribed: Boolean,
    onClickInventoryRegister: () -> Unit,
    onClickDataReport: () -> Unit,
    modifier: Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        HomeFeatureCard(
            title = "재고 등록",
            description = "새롭게 들어온\n물류의 재고를\n등록할 수 있습니다.",
            onClick = onClickInventoryRegister,
            backgroundImg = R.drawable.img_box,
            modifier = Modifier
                .weight(1f)
                .wrapContentHeight()
        )

        val uriHandler = LocalUriHandler.current
        val subscribeWebUrl = "https://www.notion.so/ss99x2002/20a5e65acf338052b199d98cd0b323e5"

        HomeFeatureCard(
            title = "데이터 분석",
            description = "스토어의 재고와\n판매추이 등을\n분석할 수 있습니다.",
            onClick = {
                if (isDataAnalyticsSubscribed) onClickDataReport
                else uriHandler.openUri(subscribeWebUrl)
            },
            backgroundImg = R.drawable.img_purple_search,
            modifier = Modifier
                .weight(1f)
                .wrapContentHeight()
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeFeatureCard(
    title: String,
    description: String,
    onClick: () -> Unit,
    @DrawableRes
    backgroundImg: Int,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        backgroundColor = Grey0,
        modifier = modifier,
        onClick = onClick,
        elevation = 2.dp
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Image(
                painter = painterResource(backgroundImg),
                contentDescription = "card background image",
                modifier = Modifier
                    .padding(vertical = 10.dp, horizontal = 4.dp)
                    .size(100.dp)
            )
        }

        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                title, style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                description,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeFeatureContent_Preview_False() {
    SellStarterAndroidTheme {
        HomeFeatureContent(
            isDataAnalyticsSubscribed = false,
            onClickInventoryRegister = {},
            onClickDataReport = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewHomeFeatureCard() {
    SellStarterAndroidTheme {
        HomeFeatureCard(
            title = "재고 등록",
            description = "새롭게 들어온\n물류의 재고를\n등록할 수 있습니다.",
            onClick = { /* 클릭 미리보기용 */ },
            backgroundImg = R.drawable.img_purple_search,
            modifier = Modifier
                .padding(16.dp)
        )
    }
}