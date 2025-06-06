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
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inha.sellstarter_android.domain.model.InventorySummary
import com.inha.sellstarter_android.presentation.common.screen.ErrorScreen
import com.inha.sellstarter_android.presentation.common.screen.LoadingScreen
import com.inha.sellstarter_android.presentation.common.screen.TitleScreen
import com.inha.sellstarter_android.presentation.inventory.list.component.InventoryItem
import com.inha.sellstarter_android.presentation.inventory.list.component.SearchBar
import com.inha.sellstarter_android.presentation.inventory.list.component.SoldOutFilterChips
import com.inha.sellstarter_android.ui.theme.Grey0
import com.inha.sellstarter_android.ui.theme.Purple50
import com.inha.sellstarter_android.util.base.UiState

@Composable
fun InventoryGridScreen(
    inventoryUiState: UiState<List<InventorySummary>>,
    searchText: String,
    selectedChipIndex: Int,
    onSearchTextChanged: (String) -> Unit,
    onSearch: () -> Unit,
    onChipSelected: (Int) -> Unit,
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Grey0)
    ) {
        TitleScreen(title = "스토어 재고 확인")

        Spacer(
            modifier = Modifier
                .size(12.dp)
        )

        SearchBar(
            value = searchText,
            onValueChange = onSearchTextChanged,
            onSearch = onSearch,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .height(50.dp)
        )

        Spacer(modifier = Modifier.size(4.dp))

        SoldOutFilterChips(
            selectedIndex = selectedChipIndex,
            onChipSelected = { index, _ -> onChipSelected(index) },
            selectedColor = Purple50,
            unSelectedColor = Grey0,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
        )

        Spacer(modifier = Modifier.size(8.dp))

        when (val state = inventoryUiState) {
            is UiState.Loading -> {
                LoadingScreen(
                    loadingText = "정보를 가져오고 있습니다.",
                    modifier = Modifier.fillMaxSize()
                )
            }

            is UiState.Success -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 12.dp),
                    content = {
                        items(state.data, key = { it.id }) { item ->
                            InventoryItem(
                                inventory = item,
                                modifier = Modifier
                                    .wrapContentHeight()
                                    .clickable {
                                        onItemClick(item.id)
                                    }
                            )
                        }
                    }
                )
            }

            is UiState.Failure -> {
                ErrorScreen(errorText = "재고 정보를 가져오는 데 실패했습니다.")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewInventoryGridScreen() {
//    InventoryGridScreen(
//        inventoryList = listOf(
//            InventoryItem(
//                id = "1",
//                name = "사과",
//                quantity = 10,
//                isSoldOut = false,
//                imageUrl = "aa"
//            ),
//    InventoryItem(
//        id = "2",
//        name = "바나나",
//        quantity = 0,
//        isSoldOut = true,
//        imageUrl = "bb"
//    ),
//    InventoryItem(
//        id = "3",
//        name = "포도",
//        quantity = 3,
//        isSoldOut = false,
//        imageUrl = "cc"
//    ),
//    InventoryItem(
//        id = "4",
//        name = "오렌지",
//        quantity = 5,
//        isSoldOut = false,
//        imageUrl = "dd"
//    )),
//    modifier = Modifier.fillMaxSize())
}