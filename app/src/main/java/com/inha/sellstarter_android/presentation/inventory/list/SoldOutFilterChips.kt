package com.inha.sellstarter_android.presentation.inventory.list

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.inha.sellstarter_android.presentation.common.component.chip.ChipGroup
import com.inha.sellstarter_android.presentation.model.ChipState

@Composable
fun SoldOutFilterChips(
    onChipSelected: (selected: Boolean, filterType: String) -> Unit,
    selectedColor : Color,
    unSelectedColor : Color,
    modifier: Modifier
) {
    val chipList = remember {
        mutableStateListOf(
            ChipState("전체재고", mutableStateOf(true)),
            ChipState("품절재고", mutableStateOf(false))
        )
    }

    ChipGroup(
        elements = chipList,
        chipFontSize = 14,
        modifier = modifier,
        selectedColor = selectedColor,
        unselectedColor = unSelectedColor,
        chipModifier = Modifier.padding(end = 8.dp),
        onChipClick = { text, currentState, index ->
            val newState = !currentState
            chipList[index].isSelected.value = newState
            onChipSelected(newState, text)
        }
    )
}