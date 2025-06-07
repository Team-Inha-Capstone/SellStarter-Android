package com.inha.sellstarter_android.presentation.inventory.scan.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inha.sellstarter_android.domain.model.Inventory
import com.inha.sellstarter_android.presentation.common.screen.dialog.TwoButtonDialog
import com.inha.sellstarter_android.ui.theme.Grey100
import com.inha.sellstarter_android.ui.theme.Purple200

@Composable
fun ValidBarcodeEditDialog(
    inventory: Inventory,
    quantity: Int,
    onQuantityChange: (Int) -> Unit,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    TwoButtonDialog(
        content = {
            ValidBarcodeContent(
                inventory = inventory,
                quantity = quantity,
                onQuantityChange = onQuantityChange
            )
        },
        leftButtonText = "취소",
        rightButtonText = "완료",
        leftButtonColor = Grey100,
        rightButtonColor = Purple200,
        onLeftClick = onDismiss,
        onRightClick = onConfirm,
        rightButtonEnabled = quantity > 0,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun ValidBarcodeScanDialog(
    inventory: Inventory,
    quantity: Int,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    TwoButtonDialog(
        content = {
            Text("상품명: ${inventory.name}", style = MaterialTheme.typography.titleMedium)
            Text("현재 수량: ${inventory.quantity}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(16.dp))
        },
        leftButtonText = "취소",
        rightButtonText = "완료",
        leftButtonColor = Grey100,
        rightButtonColor = Purple200,
        onLeftClick = onDismiss,
        onRightClick = onConfirm,
        rightButtonEnabled = quantity > 0,
        modifier = Modifier.fillMaxSize()
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewValidBarcodeDialog() {
    ValidBarcodeEditDialog(
        inventory = Inventory(
            "1",
            "사과",
            10,
            "aa",
            expiration = "2022-01-01",
            false,
            location = "위치",
            option = "option"
        ),
        1,
        { },
        { },
        { }
    )
}