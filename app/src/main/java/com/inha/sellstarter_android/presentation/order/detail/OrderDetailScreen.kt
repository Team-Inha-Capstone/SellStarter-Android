package com.inha.sellstarter_android.presentation.order.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
        Column(modifier = Modifier.fillMaxSize()) {
            TitleScreen(title = "주문 상세 확인")
            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 24.dp),
                contentPadding = PaddingValues(bottom = 20.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                item {
                    OrderInfoContent(
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                item {
                    PickingInfoContent(
                        pickedItems = pickedItems,
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(max = 300.dp)
                    )
                }

                item {
                    BuyerInfoContent(
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                item {
                    BuyerInfoContent(
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            OneButton(
                text = "전체 피킹 완료",
                buttonBackgroundColor = Blue200,
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                enabled = pickedItems.isNotEmpty()
            )
        }
}


@Preview(showBackground = true, apiLevel = 33)
@Composable
fun PreviewOrderDetailScreen() {
    OrderDetailScreen(
        modifier = Modifier.fillMaxSize()
    )
}