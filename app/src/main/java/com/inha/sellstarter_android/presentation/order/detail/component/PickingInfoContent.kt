package com.inha.sellstarter_android.presentation.order.detail.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.inha.sellstarter_android.domain.model.OrderPickingInventory
import com.inha.sellstarter_android.domain.model.PickingInfo

@Composable
fun PickingInfoContent(
    pickingInfo: PickingInfo,
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text("피킹 정보", style = MaterialTheme.typography.headlineMedium)

        Divider(modifier = Modifier.padding(vertical = 8.dp))

        InfoRow(label = "상품목록 (이름/수량)", value = "바코드번호")

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth(),
        ) {
            items(pickingInfo.items) { item ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onItemClick(item.barcodeId) }
                ) {
                    PickedItemRow(
                        pickingInventory = item
                    )
                }
            }
        }
    }
}