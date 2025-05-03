package com.inha.sellstarter_android.presentation.inventory.scan.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inha.sellstarter_android.domain.Inventory
import com.inha.sellstarter_android.presentation.common.screen.dialog.TwoButtonDialog
import com.inha.sellstarter_android.presentation.inventory.detail.InventoryDetailScreen
import com.inha.sellstarter_android.ui.theme.Grey100
import com.inha.sellstarter_android.ui.theme.Grey900
import com.inha.sellstarter_android.ui.theme.Purple200
import com.inha.sellstarter_android.ui.theme.Typography

@Composable
fun ValidBarcodeDialog(
    inventory: Inventory,
    quantity: Int,
    onQuantityChange: (Int) -> Unit,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f)),
        contentAlignment = Alignment.Center
    ) {
        TwoButtonDialog(
            content = {
                ValidBarcodeContent(
                    inventory = inventory,
                    onQuantityChange = onQuantityChange,
                    quantity = quantity
                )
            },
            leftButtonText = "취소",
            rightButtonText = "완료",
            leftButtonColor = Grey100,
            rightButtonColor = Purple200,
            onLeftClick = onDismiss,
            onRightClick = onConfirm,
            rightButtonEnabled = quantity > 0,
            modifier = Modifier.padding(24.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewValidBarcodeDialog() {
    ValidBarcodeDialog(
        inventory = Inventory(1, "사과", 10, "aa", true, "2022-10-13", "2022-10-13"),
        1,
        { },
        { },
        { }
    )
}