package com.inha.sellstarter.presentation.inventory.scan.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inha.sellstarter.domain.model.Inventory
import com.inha.sellstarter.presentation.common.screen.dialog.TwoButtonDialog
import com.inha.sellstarter.ui.theme.Grey100
import com.inha.sellstarter.ui.theme.Purple200
import com.inha.sellstarter.ui.theme.SellStarterAndroidTheme

@Composable
fun ValidBarcodeEditDialog(
    inventory: Inventory,
    quantity: Int,
    onQuantityChange: (Int) -> Unit,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
    TwoButtonDialog(
        content = {
            ValidBarcodeContent(
                inventory = inventory,
                quantity = quantity,
                onQuantityChange = onQuantityChange,
            )
        },
        leftButtonText = "취소",
        rightButtonText = "완료",
        leftButtonColor = Grey100,
        rightButtonColor = Purple200,
        onLeftClick = onDismiss,
        onRightClick = onConfirm,
        rightButtonEnabled = quantity > 0,
        modifier = Modifier.fillMaxSize(),
    )
}

@Composable
fun ValidBarcodeScanDialog(
    inventory: Inventory,
    quantity: Int,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
    TwoButtonDialog(
        content = {
            Spacer(modifier = Modifier.height(16.dp))
            Text("상품명: ${inventory.name}", style = MaterialTheme.typography.titleMedium)
            Text("현재 수량: ${inventory.quantity}", style = MaterialTheme.typography.bodyLarge)
        },
        leftButtonText = "취소",
        rightButtonText = "완료",
        leftButtonColor = Grey100,
        rightButtonColor = Purple200,
        onLeftClick = onDismiss,
        onRightClick = onConfirm,
        rightButtonEnabled = quantity > 0,
        modifier = Modifier.fillMaxSize(),
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewValidBarcodeScanDialog() {
    val dummyInventory =
        Inventory(
            id = "1",
            name = "샘플 상품",
            quantity = 10,
            imageUrl = "",
            expiration = "2025-12-31",
            isSoldOut = false,
            option = "500ml",
            location = "창고 A",
        )

    SellStarterAndroidTheme {
        ValidBarcodeScanDialog(
            inventory = dummyInventory,
            quantity = 20,
            onDismiss = {},
            onConfirm = {},
        )
    }
}
