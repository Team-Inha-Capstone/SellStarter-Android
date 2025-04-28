package com.inha.sellstarter_android.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.inha.sellstarter_android.R
import com.inha.sellstarter_android.presentation.common.component.chip.ChipGroup
import com.inha.sellstarter_android.presentation.model.ChipState
import com.inha.sellstarter_android.ui.theme.Grey0
import com.inha.sellstarter_android.ui.theme.Purple100
import com.inha.sellstarter_android.ui.theme.Typography

@Composable
fun OrderStatisticsContent(modifier: Modifier){
    // 주문 추이 & ChipGroup
    var selectedChipIndex by remember { mutableStateOf(0) }
    val chipStates = listOf("주별", "월별").mapIndexed { index, label ->
        ChipState(label, remember { mutableStateOf(index == selectedChipIndex) })
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Text(
            text = "스토어 주문 추이",
            style = Typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )

        ChipGroup(
            elements = chipStates,
            onChipClick = { text, isSelected, index ->
                selectedChipIndex = index
                chipStates.forEachIndexed { i, chipState ->
                    chipState.isSelected.value = i == index
                }
            },
            selectedColor = Purple100,
            unselectedColor = Grey0,
            chipModifier = Modifier.padding(horizontal = 4.dp)
        )
    }

    Spacer(modifier = Modifier.height(12.dp))

    Image(
        painter = painterResource(id = R.drawable.ic_placeholder),
        contentDescription = "chart",
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .clip(RoundedCornerShape(12.dp)),
    )
}