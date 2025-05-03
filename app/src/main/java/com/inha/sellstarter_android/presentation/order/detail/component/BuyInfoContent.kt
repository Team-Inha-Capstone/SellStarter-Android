package com.inha.sellstarter_android.presentation.order.detail.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inha.sellstarter_android.ui.theme.Typography

@Composable
fun BuyerInfoContent(
    modifier: Modifier
) {
    Column(modifier = modifier) {
        Text("구매자 정보", style = Typography.headlineMedium)
        Divider(modifier = Modifier.padding(vertical = 8.dp))

        InfoRow(label = "구매자 이름", value = "OOO")

        Spacer(modifier = Modifier.height(16.dp))

        InfoRow(label = "배송주소", value = "인천광역시 미추홀구 인하로 100\n우편번호 : 22188")

        InfoRow(label = "배송 요청사항",value = "경비실에 맡겨주세요.")

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBuyInfoContent() {
    BuyerInfoContent(
        modifier = Modifier.padding(horizontal = 24.dp)
    )
}