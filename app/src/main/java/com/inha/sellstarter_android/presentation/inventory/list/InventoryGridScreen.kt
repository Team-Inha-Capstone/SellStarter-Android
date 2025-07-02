package com.inha.sellstarter_android.presentation.inventory.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inha.sellstarter_android.domain.model.InventoryListPage
import com.inha.sellstarter_android.domain.model.InventorySummary
import com.inha.sellstarter_android.presentation.common.component.LoadingItem
import com.inha.sellstarter_android.presentation.common.screen.EmptyScreen
import com.inha.sellstarter_android.presentation.common.screen.ErrorScreen
import com.inha.sellstarter_android.presentation.common.screen.LoadingLottieScreen
import com.inha.sellstarter_android.presentation.common.screen.TitleScreen
import com.inha.sellstarter_android.presentation.inventory.list.component.InventoryItem
import com.inha.sellstarter_android.presentation.inventory.list.component.SearchBar
import com.inha.sellstarter_android.presentation.inventory.list.component.SoldOutFilterChips
import com.inha.sellstarter_android.ui.theme.Grey0
import com.inha.sellstarter_android.ui.theme.Purple50
import com.inha.sellstarter_android.ui.theme.SellStarterAndroidTheme
import com.inha.sellstarter_android.util.base.UiState
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.mapNotNull

@Composable
fun InventoryGridScreen(
    inventoryUiState: UiState<InventoryListPage>,
    searchText: String,
    selectedChipIndex: Int,
    onSearchTextChanged: (String) -> Unit,
    onSearch: () -> Unit,
    onChipSelected: (Int) -> Unit,
    onItemClick: (String) -> Unit,
    onLoadMore: () -> Unit,
    isLoadingMore: Boolean,
    hasNextPage: Boolean,
    modifier: Modifier = Modifier,
) {

    val gridState = rememberLazyGridState()

    LaunchedEffect(gridState) {
        snapshotFlow { gridState.layoutInfo }
            .mapNotNull { layoutInfo ->
                val lastVisible = layoutInfo.visibleItemsInfo.lastOrNull()?.index
                if (lastVisible != null) {
                    lastVisible to layoutInfo.totalItemsCount
                } else null
            }
            .distinctUntilChanged()
            .collect { (lastVisible, totalItems) ->
                if (lastVisible >= totalItems - 2) {
                    onLoadMore()
                }
            }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Grey0)
            .testTag("InventoryGridRoot")
    ) {
        TitleScreen(
            title = "스토어 재고 확인",
            description = "스토어 내 재고를 한눈에 파악하세요.",
            modifier = Modifier.testTag("Title")
        )

        Spacer(
            modifier = Modifier
                .height(4.dp)
        )

        SearchBar(
            value = searchText,
            onValueChange = onSearchTextChanged,
            onSearch = onSearch,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .height(50.dp)
                .testTag("SearchBar")
        )

        Spacer(modifier = Modifier.height(4.dp))

        SoldOutFilterChips(
            selectedIndex = selectedChipIndex,
            onChipSelected = { index, _ -> onChipSelected(index) },
            selectedColor = Purple50,
            unSelectedColor = Grey0,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .testTag("SoldOutFilterChips")
        )

        Spacer(modifier = Modifier.size(8.dp))

        when (val state = inventoryUiState) {
            is UiState.Loading -> {
                LoadingLottieScreen(
                    loadingText = "재고 정보를 가져오고 있습니다.",
                    modifier = Modifier
                        .fillMaxSize()
                        .testTag("Loading")
                )
            }

            is UiState.Success -> {
                if (state.data.inventories.isEmpty()) {
                    EmptyScreen(
                        emptyText = "검색한 재고가 없습니다.",
                        modifier = Modifier
                            .fillMaxSize()
                            .testTag("Empty")
                    )
                } else {
                    LazyVerticalGrid(
                        state = gridState,
                        columns = GridCells.Fixed(2),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 12.dp)
                            .testTag("InventoryGrid"),
                        content = {
                            items(state.data.inventories, key = { it.id }) { item ->
                                InventoryItem(
                                    inventory = item,
                                    modifier = Modifier
                                        .wrapContentHeight()
                                        .clickable {
                                            onItemClick(item.id)
                                        }
                                        .testTag("Item_${item.id}")
                                )
                            }
                            if (isLoadingMore && hasNextPage) {
                                item(span = { GridItemSpan(2) }) {
                                    Spacer(modifier = Modifier.height(12.dp))
                                    LoadingItem(
                                        modifier = Modifier
                                            .size(24.dp)
                                            .testTag("LoadMoreIndicator")
                                    )
                                    Spacer(modifier = Modifier.height(12.dp))
                                }
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }

            is UiState.Failure -> {
                ErrorScreen(
                    errorText = "재고 정보를 가져오는 데 실패했습니다.",
                    modifier = Modifier.testTag("Error")
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewInventoryGridScreen() {
    val dummyInventories = listOf(
        InventorySummary(
            id = "1",
            name = "상품 A",
            quantity = 10,
            isSoldOut = false,
            option = "옵션 1",
            imageUrl = null
        ),
        InventorySummary(
            id = "2",
            name = "상품 B",
            quantity = 0,
            isSoldOut = true,
            option = "옵션 2",
            imageUrl = null
        )
    )

    SellStarterAndroidTheme {
        InventoryGridScreen(
            inventoryUiState = UiState.Success(
                InventoryListPage(
                    inventories = dummyInventories,
                    page = 0,
                    size = 2,
                    totalElements = 2,
                    totalPages = 1
                )
            ),
            searchText = "상품",
            selectedChipIndex = 0,
            onSearchTextChanged = {},
            onSearch = {},
            onChipSelected = {},
            onItemClick = {},
            onLoadMore = {},
            isLoadingMore = false,
            hasNextPage = false
        )
    }
}