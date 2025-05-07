package com.inha.sellstarter_android.presentation.inventory.scan.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.inha.sellstarter_android.domain.Inventory
import com.inha.sellstarter_android.ui.theme.Grey900
import com.inha.sellstarter_android.ui.theme.AppTypography

@Composable
fun ValidBarcodeContent(
    inventory: Inventory,
    quantity: Int,
    onQuantityChange: (Int) -> Unit,
) {
    Column {
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "상품 이름 : ${inventory.name}",
            style = MaterialTheme.typography.headlineSmall,
            color = Grey900
        )

        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "상품 위치 : ${inventory.location}",
            style = MaterialTheme.typography.headlineSmall,
            color = Grey900
        )
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = quantity.toString(),
            onValueChange = { input ->
                input.toIntOrNull()?.let {
                    onQuantityChange(it.coerceAtLeast(1))
                }
            },
            label = { Text("출고 수량") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "재고를 차감하겠습니까?",
            style = MaterialTheme.typography.headlineMedium,
            color = Grey900,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}