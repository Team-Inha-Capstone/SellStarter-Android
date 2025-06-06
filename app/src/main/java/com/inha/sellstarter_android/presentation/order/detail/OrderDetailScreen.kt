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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inha.sellstarter_android.domain.model.OrderDetailInfo
import com.inha.sellstarter_android.domain.model.OrderPickingInventory
import com.inha.sellstarter_android.presentation.common.component.OneButton
import com.inha.sellstarter_android.presentation.common.screen.TitleScreen
import com.inha.sellstarter_android.presentation.order.detail.component.BuyerInfoContent
import com.inha.sellstarter_android.presentation.order.detail.component.OrderDetailBottomButton
import com.inha.sellstarter_android.presentation.order.detail.component.OrderInfoContent
import com.inha.sellstarter_android.presentation.order.detail.component.PickingInfoContent
import com.inha.sellstarter_android.ui.theme.Blue200
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
    // 1) PickedItem 리스트로 매핑
    //    이미 OrderPickingInventory 타입이므로, 그대로 사용
    val pickedItems = remember(orderDetailInfo) {
        orderDetailInfo.pickingInfo.items
    }

    // 2) “전체 피킹 완료” 버튼 활성 조건: DTO에서 내려온 allPicked 값을 그대로 사용
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

        Spacer(modifier = Modifier.height(70.dp))
    }
}

@Preview(showBackground = true, apiLevel = 33)
@Composable
fun PreviewOrderDetailScreen() {
    SellStarterAndroidTheme {

    }
}