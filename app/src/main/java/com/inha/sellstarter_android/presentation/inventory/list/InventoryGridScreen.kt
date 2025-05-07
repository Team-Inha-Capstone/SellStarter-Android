package com.inha.sellstarter_android.presentation.inventory.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inha.sellstarter_android.domain.model.Inventory
import com.inha.sellstarter_android.presentation.common.screen.TitleScreen
import com.inha.sellstarter_android.presentation.inventory.list.component.InventoryItem
import com.inha.sellstarter_android.presentation.inventory.list.component.SearchBar
import com.inha.sellstarter_android.presentation.inventory.list.component.SoldOutFilterChips
import com.inha.sellstarter_android.ui.theme.Grey0
import com.inha.sellstarter_android.ui.theme.Purple100

@Composable
fun InventoryGridScreen(
    inventoryList: List<Inventory>,
    modifier: Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        TitleScreen(
            title = "스토어 재고 확인"
        )

        val hint = "ex) 재고를 검색해보세요."
        var text by remember { mutableStateOf(hint) }

        SearchBar(
            value = text,
            onValueChange = {},
            onSearch = {},
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 12.dp)
                .height(50.dp)
        )

        Spacer(modifier = Modifier.size(12.dp))

        SoldOutFilterChips(
            selectedColor = Purple100,
            unSelectedColor = Grey0,
            onChipSelected = { selected, filterType ->  },
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 12.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            content = {
                items(inventoryList, key = { it.id }) { item ->
                    InventoryItem(
                        inventory = item,
                        modifier = Modifier
                            .height(300.dp)
                    )
                }
            },
            modifier = Modifier.padding(12.dp)
        )


    }
}

@Preview(showBackground = true)
@Composable
fun PreviewInventoryGridScreen() {

    InventoryGridScreen(
        inventoryList = listOf(
            Inventory(1, "사과", 10, "aa", true, "2022-10-13", "2022-10-13"),
            Inventory(2, "오렌지", 10, "aa", false, "2022-10-13", "2022-10-13"),
            Inventory(3, "레몬", 10, "aa", false, "2022-10-13", "2022-10-13"),
            Inventory(4, "초콜릿", 10, "aa", true, "2022-10-13", "2022-10-13"),
            Inventory(5, "옷", 10, "aa", false, "2022-10-13", "2022-10-13"),
            Inventory(6, "휴지", 10, "aa", true, "2022-10-13", "2022-10-13"),
            Inventory(7, "레몬수", 10, "aa", false, "2022-10-13", "2022-10-13"),
            Inventory(8, "마우스", 10, "aa", false, "2022-10-13", "2022-10-13"),

        ),
        modifier = Modifier.fillMaxSize()
    )

}