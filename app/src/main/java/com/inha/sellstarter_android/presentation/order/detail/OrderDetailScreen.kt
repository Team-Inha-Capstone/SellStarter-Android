package com.inha.sellstarter_android.presentation.order.detail

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inha.sellstarter_android.domain.model.BuyerInfo
import com.inha.sellstarter_android.domain.model.OrderDetailInfo
import com.inha.sellstarter_android.domain.model.OrderInfo
import com.inha.sellstarter_android.domain.model.OrderPickingInventory
import com.inha.sellstarter_android.domain.model.PickingInfo
import com.inha.sellstarter_android.domain.model.type.OrderStatusType
import com.inha.sellstarter_android.presentation.common.screen.TitleScreen
import com.inha.sellstarter_android.presentation.order.detail.component.BuyerInfoContent
import com.inha.sellstarter_android.presentation.order.detail.component.OrderDetailBottomButton
import com.inha.sellstarter_android.presentation.order.detail.component.OrderInfoContent
import com.inha.sellstarter_android.presentation.order.detail.component.PickingInfoContent
import com.inha.sellstarter_android.ui.theme.Grey0
import com.inha.sellstarter_android.ui.theme.SellStarterAndroidTheme

@Composable
fun OrderDetailScreen(
    orderDetailInfo: OrderDetailInfo,
    isFromCompletedTab: Boolean,
    onNavigateToScan: (orderId: String, barcode: String) -> Unit,
    onCompletePicking: () -> Unit,
    onCompleteShipping: () -> Unit,
    onCancelComplete: () -> Unit,
    modifier: Modifier = Modifier
) {
    val pickedItems = remember(orderDetailInfo) {
        orderDetailInfo.pickingInfo.items
    }

    val allPicked = orderDetailInfo.pickingInfo.allPicked

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Grey0)
    ) {
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
                    orderInfo = orderDetailInfo.orderInfo,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item {
                PickingInfoContent(
                    pickingInfo = orderDetailInfo.pickingInfo,
                    onItemClick = { barcode ->
                        onNavigateToScan(orderDetailInfo.orderInfo.orderId, barcode)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 300.dp)
                )
            }
            item {
                BuyerInfoContent(
                    buyerInfo = orderDetailInfo.buyerInfo,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        OrderDetailBottomButton(
            isFromCompletedTab = isFromCompletedTab,
            pickedItems = pickedItems,
            allPicked = allPicked,
            onCompletePicking = onCompletePicking,
            onCompleteShipping = onCompleteShipping,
            onCancelComplete = onCancelComplete
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOrderDetailScreen() {
    val dummyOrderInfo = OrderInfo(
        orderId = "ORD123456",
        channelName = "shopify",
        orderStatus = OrderStatusType.ORDER_COMPLETED
    )

    val dummyPickingInfo = PickingInfo(
        items = listOf(
            OrderPickingInventory(
                inventoryName = "나일론 와샤 자켓",
                barcodeId = "1234567890",
                inventoryCount = 2,
                isPicked = true
            ),
            OrderPickingInventory(
                inventoryName = "aeae모자",
                barcodeId = "0987654321",
                inventoryCount = 1,
                isPicked = true
            )
        ),
        allPicked = true
    )

    val dummyBuyerInfo = BuyerInfo(
        purchaserName = "홍길동",
        purchaserAddress = "서울시 강남구 테헤란로 123",
        purchaserRequest = "문 앞에 놔주세요."
    )

    val dummyDetailInfo = OrderDetailInfo(
        orderInfo = dummyOrderInfo,
        pickingInfo = dummyPickingInfo,
        buyerInfo = dummyBuyerInfo
    )

    SellStarterAndroidTheme {
        OrderDetailScreen(
            orderDetailInfo = dummyDetailInfo,
            isFromCompletedTab = false,
            onNavigateToScan = { _, _ -> },
            onCompletePicking = {},
            onCompleteShipping = {},
            onCancelComplete = {}
        )
    }
}