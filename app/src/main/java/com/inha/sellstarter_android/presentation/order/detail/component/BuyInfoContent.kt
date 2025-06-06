package com.inha.sellstarter_android.presentation.order.detail.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inha.sellstarter_android.domain.model.BuyerInfo
import com.inha.sellstarter_android.domain.model.OrderDetailInfo
import com.inha.sellstarter_android.ui.theme.AppTypography
import com.inha.sellstarter_android.ui.theme.SellStarterAndroidTheme

@Composable
fun BuyerInfoContent(
    buyerInfo: BuyerInfo,
    modifier: Modifier
) {
    Column(modifier = modifier) {
        Text(
            "구매자 정보",
            style = MaterialTheme.typography.headlineMedium
        )

        Divider(modifier = Modifier.padding(vertical = 8.dp))

        InfoRow(label = "구매자 이름", value = buyerInfo.purchaserName)

        Spacer(modifier = Modifier.height(4.dp))

        InfoRow(label = "배송주소", value = buyerInfo.purchaserAddress)

        InfoRow(label = "배송 요청사항", value = buyerInfo.purchaserRequest)

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBuyInfoContent() {
    SellStarterAndroidTheme {
        BuyerInfoContent(
            buyerInfo = BuyerInfo(
                purchaserName = "홍길동",
                purchaserAddress = "서울특별시 강남구 테헤란로 123",
                purchaserRequest = "배송 전 연락 부탁드립니다."
            ),
            modifier = Modifier.padding(horizontal = 24.dp)
        )
    }
}