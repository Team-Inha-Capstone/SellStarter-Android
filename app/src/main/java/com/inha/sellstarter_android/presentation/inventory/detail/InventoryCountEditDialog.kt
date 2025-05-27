package com.inha.sellstarter_android.presentation.inventory.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.inha.sellstarter_android.presentation.common.screen.dialog.TwoButtonDialog
import com.inha.sellstarter_android.ui.theme.AppTypography
import com.inha.sellstarter_android.ui.theme.Grey100
import com.inha.sellstarter_android.ui.theme.Purple200

@Composable
fun InventoryCountEditDialog(
    currentCount: Int,
    onConfirm: (Int) -> Unit,
    onDismiss: () -> Unit
) {
    var input by remember { mutableStateOf(currentCount.toString()) }

    val isValid = input.toIntOrNull() != null && input.toInt() >= 0

    TwoButtonDialog(
        leftButtonText = "취소",
        rightButtonText = "수정",
        leftButtonColor = Grey100,
        rightButtonColor = Purple200,
        leftButtonEnabled = true,
        rightButtonEnabled = isValid,
        onLeftClick = onDismiss,
        onRightClick = {
            onConfirm(input.toInt())
        },
        content = {
            Column {
                Text(
                    text = "수정할 재고 수량을 입력하세요.",
                    style = AppTypography.bodyLarge
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = input,
                    onValueChange = { input = it },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    )
}