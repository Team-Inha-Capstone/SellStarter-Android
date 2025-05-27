package com.inha.sellstarter_android.presentation.inventory.register

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.inha.sellstarter_android.presentation.inventory.InventoryViewModel

@Composable
fun InventoryRegisterRoute(
    viewModel: InventoryViewModel = hiltViewModel(),
    onRegisterSuccess: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    InventoryRegisterScreen(
        modifier = modifier,
        viewModel = viewModel,
        context = context,
        onClickRegisterSuccess = onRegisterSuccess
    )
}
