package com.inha.sellstarter.presentation.inventory.route

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.inha.sellstarter.presentation.inventory.scan.component.InvalidBarcodeDialog

@Composable
fun InventoryScanErrorRoute(
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {
    InvalidBarcodeDialog(
        onDismiss = onDismiss,
        modifier = modifier,
    )
}
