package com.inha.sellstarter.presentation.order.detail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.inha.sellstarter.domain.model.OrderPickingInventory
import com.inha.sellstarter.presentation.common.component.OneButton
import com.inha.sellstarter.ui.theme.Blue200
import com.inha.sellstarter.ui.theme.Grey100

@Composable
fun OrderDetailBottomButton(
    isFromCompletedTab: Boolean,
    pickedItems: List<OrderPickingInventory>,
    allPicked: Boolean,
    onCompletePicking: () -> Unit,
    onCompleteShipping: () -> Unit,
    onCancelComplete: () -> Unit,
) {
    if (!isFromCompletedTab) {
        OneButton(
            text = "전체 피킹 완료",
            buttonBackgroundColor = Blue200,
            onClick = onCompletePicking,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
                    .height(55.dp),
            enabled = pickedItems.isNotEmpty() && allPicked,
        )
    } else {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            OneButton(
                text = "취소완료",
                buttonBackgroundColor = Grey100,
                onClick = onCancelComplete,
                modifier =
                    Modifier
                        .weight(1f)
                        .height(55.dp),
                enabled = true,
            )
            OneButton(
                text = "출고완료",
                buttonBackgroundColor = Blue200,
                onClick = onCompleteShipping,
                modifier =
                    Modifier
                        .weight(1f)
                        .height(55.dp),
                enabled = true,
            )
        }
    }
}
