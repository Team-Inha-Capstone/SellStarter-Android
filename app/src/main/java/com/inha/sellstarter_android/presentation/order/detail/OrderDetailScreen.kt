package com.inha.sellstarter_android.presentation.order.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inha.sellstarter_android.domain.PickedItem
import com.inha.sellstarter_android.presentation.common.component.OneButton
import com.inha.sellstarter_android.presentation.common.screen.TitleScreen
import com.inha.sellstarter_android.presentation.order.detail.component.BuyerInfoContent
import com.inha.sellstarter_android.presentation.order.detail.component.OrderInfoContent
import com.inha.sellstarter_android.presentation.order.detail.component.PickingInfoContent
import com.inha.sellstarter_android.ui.theme.Blue200

@Composable
fun OrderDetailScreen(
    modifier: Modifier = Modifier
) {

    val pickedItems = listOf(
        PickedItem("미니 드레스 0A92 / 2", "0134Ad234"),
        PickedItem("파란 니트 032Z / 1", "085Ad145"),
        PickedItem("미니 드레스 0A92 / 2", "0134Ad234"),
        PickedItem("파란 니트 032Z / 1", "085Ad145"),
        PickedItem("미니 드레스 0A92 / 2", "0134Ad234"),
        PickedItem("파란 니트 032Z / 1", "085Ad145"),
        PickedItem("미니 드레스 0A92 / 2", "0134Ad234"),
        PickedItem("파란 니트 032Z / 1", "085Ad145")
    )

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        TitleScreen(
            title = "주문 상세 확인",
        )

        Column(
            modifier = Modifier
                .weight(1f) // 버튼 제외하고 다 차지
                .padding(bottom = 16.dp) // 버튼과 살짝 거리두기
                .padding(horizontal = 24.dp)
        ) {

            Spacer(modifier = Modifier.height(16.dp))

            OrderInfoContent(
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            PickingInfoContent(
                pickedItems = pickedItems,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            BuyerInfoContent(
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

        OneButton(
            text = "전체 피킹 완료",
            buttonBackgroundColor = Blue200,
            onClick = { },
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            enabled = pickedItems.isNotEmpty() // 조건에 따라 활성화
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOrderDetailScreen() {
    OrderDetailScreen(
        modifier = Modifier.fillMaxSize()
    )
}