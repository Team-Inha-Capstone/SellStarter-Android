package com.inha.sellstarter_android.presentation.inventory.list.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.inha.sellstarter_android.presentation.common.component.chip.ChipGroup
import com.inha.sellstarter_android.presentation.model.ChipState
import com.inha.sellstarter_android.ui.theme.AppTypography

@Composable
fun SoldOutFilterChips(
    selectedIndex: Int,
    onChipSelected: (index: Int, filterType: String) -> Unit,
    selectedColor: Color,
    unSelectedColor: Color,
    modifier: Modifier = Modifier
) {
    val chipItems = listOf("전체재고", "품절재고")

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        chipItems.forEachIndexed { index, label ->
            FilterChip(
                selected = selectedIndex == index,
                onClick = {
                    onChipSelected(index, label)
                },
                label = {
                    Text(
                        text = label,
                        style = AppTypography.bodyMedium
                    )
                },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = selectedColor,
                    containerColor = unSelectedColor
                ),
                modifier = Modifier
                    .testTag("FilterChip_$index")
            )
        }
    }
}
