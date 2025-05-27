package com.inha.sellstarter_android.presentation.inventory.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.inha.sellstarter_android.presentation.inventory.InventoryViewModel

@Composable
fun InventoryGridRoute(
    modifier: Modifier = Modifier,
    onNavigateToDetail: (String) -> Unit,
    viewModel: InventoryViewModel = hiltViewModel()
) {
    val inventoryState by viewModel.inventoryListState.collectAsState()
    val searchResultState by viewModel.searchResultState.collectAsState()

    var searchText by remember { mutableStateOf("") }
    var selectedChipIndex by remember { mutableStateOf(0) } // 0: 전체, 1: 품절

    // 초기 진입 시 재고 요청
    LaunchedEffect(Unit) {
        viewModel.getInventoryList(status = (selectedChipIndex == 1))
    }

    // 보여줄 상태 결정
    val displayState = if (searchText.isNotEmpty()) searchResultState else inventoryState

    InventoryGridScreen(
        modifier = modifier,
        inventoryUiState = displayState,
        searchText = searchText,
        selectedChipIndex = selectedChipIndex,
        onSearchTextChanged = {
            searchText = it
            if (it.isEmpty()) {
                viewModel.getInventoryList(status = (selectedChipIndex == 1))
            }
        },
        onSearch = {
            viewModel.searchInventory(searchText, selectedChipIndex == 1)
        },
        onChipSelected = { index ->
            selectedChipIndex = index
            if (searchText.isEmpty()) {
                viewModel.getInventoryList(status = (index == 1))
            }
        },
        onItemClick = { barcodeId ->
            onNavigateToDetail(barcodeId)
        }
    )
}
