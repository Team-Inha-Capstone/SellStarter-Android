package com.inha.sellstarter.presentation.order.detail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inha.sellstarter.domain.model.OrderPickingInventory
import com.inha.sellstarter.ui.theme.AppTypography
import com.inha.sellstarter.ui.theme.Blue200
import com.inha.sellstarter.ui.theme.Grey0
import com.inha.sellstarter.ui.theme.Grey50
import com.inha.sellstarter.ui.theme.Red200

@Composable
fun PickedItemRow(pickingInventory: OrderPickingInventory) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .background(
                    Grey0,
                    RoundedCornerShape(8.dp),
                )
                .border(
                    1.dp,
                    Grey50,
                    shape = RoundedCornerShape(8.dp),
                )
                .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            val (icon, tint) =
                if (pickingInventory.isPicked) {
                    Icons.Default.Check to Blue200
                } else {
                    Icons.Default.Close to Red200
                }

            Icon(
                imageVector = icon,
                contentDescription = if (pickingInventory.isPicked) "Picked" else "Not Picked",
                tint = tint,
                modifier = Modifier.size(20.dp),
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(
                    text = pickingInventory.inventoryName + " / " + pickingInventory.inventoryCount,
                    style = AppTypography.titleSmall,
                )

                Text(
                    text = pickingInventory.barcodeId,
                    maxLines = 1,
                    style = AppTypography.bodyMedium,
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewPickedItem() {
    val dummyPickingInventories =
        OrderPickingInventory(
            inventoryName = "상품 A",
            barcodeId = "ABC-123",
            inventoryCount = 2,
            isPicked = false,
        )

    PickedItemRow(
        pickingInventory = dummyPickingInventories,
    )
}
