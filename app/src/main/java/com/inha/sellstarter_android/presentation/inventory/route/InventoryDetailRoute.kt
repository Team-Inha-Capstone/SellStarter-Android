package com.inha.sellstarter_android.presentation.inventory.route

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.inha.sellstarter_android.presentation.common.screen.ErrorScreen
import com.inha.sellstarter_android.presentation.common.screen.LoadingScreen
import com.inha.sellstarter_android.presentation.inventory.InventoryViewModel
import com.inha.sellstarter_android.presentation.inventory.detail.InventoryDetailScreen
import com.inha.sellstarter_android.util.base.UiState

@Composable
fun InventoryDetailRoute(
    barcodeId: String,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InventoryViewModel = hiltViewModel()
) {
    val state by viewModel.inventoryDetailState.collectAsState()
    val graph by viewModel.inventoryGraphState.collectAsState()

    LaunchedEffect(barcodeId) {
        viewModel.getInventoryFlowGraph(barcodeId)
        viewModel.getInventoryDetail(barcodeId)
    }

    when (val uiState = state) {
        is UiState.Success -> InventoryDetailScreen(
            inventory = uiState.data,
            graphUrl = graph,
            onBack = onBack,
            modifier = modifier,
            onClickEditCount = { newCount ->
                viewModel.editInventoryCount(
                    barcodeId = barcodeId,
                    currentCount = uiState.data.quantity,
                    newCount = newCount
                )
            }
        )

        is UiState.Loading -> LoadingScreen(
            loadingText = "재고 정보를 가져오고 있습니다.",
            modifier = Modifier.fillMaxSize()
        )

        is UiState.Failure -> ErrorScreen(errorText = "재고 정보를 가져오는 데 실패했습니다.")
    }
}