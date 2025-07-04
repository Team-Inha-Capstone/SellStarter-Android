package com.inha.sellstarter.presentation.inventory.route

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import com.inha.sellstarter.presentation.inventory.InventoryViewModel
import com.inha.sellstarter.presentation.inventory.register.barcode.BarcodeConfirmDialog

@Composable
fun InventoryConfirmRoute(
    parentEntry: NavBackStackEntry,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
    val viewModel: InventoryViewModel = hiltViewModel(parentEntry)

    BarcodeConfirmDialog(
        viewModel = viewModel,
        onDismiss = onDismiss,
        onConfirm = onConfirm,
    )
}
