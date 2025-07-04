package com.inha.sellstarter.presentation.inventory.route

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.inha.sellstarter.presentation.inventory.scan.BarcodeScannerScreen

@Composable
fun InventoryScanRoute(
    orderId: String,
    barcodeId: String,
    onNavigateToDialog: () -> Unit,
    onError: () -> Unit,
    modifier: Modifier = Modifier,
) {
    BarcodeScannerScreen(
        barcodeId = barcodeId,
        onBack = onError,
        onSubmitPicking = {
            onNavigateToDialog()
        },
        modifier = modifier,
    )
}
