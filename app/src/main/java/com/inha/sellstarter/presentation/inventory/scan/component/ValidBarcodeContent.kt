package com.inha.sellstarter.presentation.inventory.scan.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.inha.sellstarter.domain.model.Inventory

@Composable
fun ValidBarcodeContent(
    inventory: Inventory,
    quantity: Int,
    onQuantityChange: (Int) -> Unit,
) {
    var quantityInput by remember { mutableStateOf(quantity.toString()) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Text("상품명: ${inventory.name}", style = MaterialTheme.typography.titleMedium)
        Text("현재 수량: ${inventory.quantity}", style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = quantityInput,
            onValueChange = { input ->
                quantityInput = input
                input.toIntOrNull()?.let { onQuantityChange(it) }
            },
            label = { Text("변경할 수량") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
