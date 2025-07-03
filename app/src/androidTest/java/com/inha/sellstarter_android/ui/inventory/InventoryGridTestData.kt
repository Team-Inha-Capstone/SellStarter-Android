package com.inha.sellstarter_android.ui.inventory

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import com.inha.sellstarter_android.domain.model.InventoryListPage
import com.inha.sellstarter_android.domain.model.InventorySummary
import com.inha.sellstarter_android.presentation.inventory.list.InventoryGridScreen
import com.inha.sellstarter_android.util.base.UiState

object InventoryGridTestData {
    // — 테스트용 태그 상수
    const val TAG_ROOT = "InventoryGridRoot"
    const val TAG_SEARCH_BAR = "SearchBar"
    const val TAG_SEARCH_INPUT = "SearchInput"
    const val TAG_FILTER_CHIPS = "SoldOutFilterChips"
    const val TAG_GRID = "InventoryGrid"
    const val TAG_LOAD_MORE_INDICATOR = "LoadMoreIndicator"
    fun itemTag(id: String) = "Item_$id"


    // — 더미 데이터 리스트
    val dummyList = listOf(
        InventorySummary("1", "상품 A", 10, false, "옵션 1", null),
        InventorySummary("2", "상품 B", 0, true, "옵션 2", null),
        InventorySummary("3", "상품 C", 5, false, "옵션 3", null)
    )

    // — 더미 페이징 페이지
    val dummyPage = InventoryListPage(
        inventories = dummyList,
        page = 0,
        size = dummyList.size,
        totalElements = dummyList.size,
        totalPages = 1
    )

    fun setInventoryGridScreen(
        rule: ComposeContentTestRule,
        uiState: UiState<InventoryListPage> = UiState.Success(dummyPage),
        searchText: String = "",
        selectedChipIndex: Int = 0,
        onSearchTextChanged: (String) -> Unit = {},
        onSearch: () -> Unit = {},
        onChipSelected: (Int) -> Unit = {},
        onItemClick: (String) -> Unit = {},
        onLoadMore: () -> Unit = {},
        isLoadingMore: Boolean = false,
        hasNextPage: Boolean = false
    ) {
        rule.setContent {
            InventoryGridScreen(
                inventoryUiState = uiState,
                searchText = searchText,
                selectedChipIndex = selectedChipIndex,
                onSearchTextChanged = onSearchTextChanged,
                onSearch = onSearch,
                onChipSelected = onChipSelected,
                onItemClick = onItemClick,
                onLoadMore = onLoadMore,
                isLoadingMore = isLoadingMore,
                hasNextPage = hasNextPage,
                modifier = Modifier
            )
        }
    }
}