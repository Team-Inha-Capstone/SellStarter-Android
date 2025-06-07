package com.inha.sellstarter_android.presentation.inventory.route

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.inha.sellstarter_android.presentation.inventory.InventoryViewModel
import com.inha.sellstarter_android.presentation.inventory.register.InventoryRegisterScreen

@Composable
fun InventoryRegisterRoute(
    onRegisterSuccess: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InventoryViewModel = hiltViewModel()
) {
    InventoryRegisterScreen(
        modifier = modifier,
        onRegister = { dto, imageUri ->
            viewModel.saveDraft(dto, imageUri)
            onRegisterSuccess()
        }
    )
}