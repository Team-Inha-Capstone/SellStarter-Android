package com.inha.sellstarter_android.presentation.inventory.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.inha.sellstarter_android.presentation.inventory.InventoryViewModel
import com.inha.sellstarter_android.presentation.inventory.list.InventoryGridScreen

const val STATUS_ALL = 0
const val STATUS_OUT_OF_STOCK = 1

@Composable
fun InventoryGridRoute(
    modifier: Modifier = Modifier,
    onNavigateToDetail: (String) -> Unit,
    viewModel: InventoryViewModel = hiltViewModel()
) {
    val inventoryState by viewModel.inventoryListState.collectAsState()
    val searchResultState by viewModel.searchResultState.collectAsState()

    var searchText by remember { mutableStateOf("") }
    var selectedChipIndex by remember { mutableIntStateOf(STATUS_ALL) }

    LaunchedEffect(selectedChipIndex) {
        viewModel.getInitialInventoryList(
            status = (selectedChipIndex == STATUS_OUT_OF_STOCK),
            search = searchText
        )
    }

    InventoryGridScreen(
        inventoryUiState = inventoryState,
        searchText = searchText,
        selectedChipIndex = selectedChipIndex,
        onSearchTextChanged = { text ->
            searchText = text
            viewModel.getInitialInventoryList(
                search = searchText,
                status = (selectedChipIndex == STATUS_OUT_OF_STOCK)
            )
        },

        onSearch = {
            viewModel.getInitialInventoryList(
                search = searchText,
                status = (selectedChipIndex == STATUS_OUT_OF_STOCK)
            )
        },

        onChipSelected = { index ->
            selectedChipIndex = index
            if (searchText.isEmpty()) {
                viewModel.getInitialInventoryList(
                    status = (index == STATUS_OUT_OF_STOCK)
                )
            }
        },

        onItemClick = { barcodeId ->
            onNavigateToDetail(barcodeId)
        },

        onLoadMore = {
            viewModel.loadMoreInventoryList()
        },
        isLoadingMore = viewModel.isLoadingMore(),
        hasNextPage = viewModel.hasNextPage(),
        modifier = modifier
    )
}
