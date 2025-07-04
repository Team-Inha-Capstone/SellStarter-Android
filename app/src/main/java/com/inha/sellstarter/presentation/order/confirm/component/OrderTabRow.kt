package com.inha.sellstarter.presentation.order.confirm.component

import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.inha.sellstarter.ui.theme.Grey0
import com.inha.sellstarter.ui.theme.Grey100
import com.inha.sellstarter.ui.theme.Purple200

@Composable
fun OrderTabRow(
    selectedIndex: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier,
) {
    val tabs = listOf("신규 주문", "피킹 완료")
    TabRow(
        selectedTabIndex = selectedIndex,
        backgroundColor = Grey0,
        contentColor = Purple200,
        modifier = modifier,
    ) {
        tabs.forEachIndexed { index, title ->
            Tab(
                selected = (selectedIndex == index),
                onClick = { onTabSelected(index) },
                text = {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleMedium,
                        color = if (selectedIndex == index) Purple200 else Grey100,
                    )
                },
                modifier = Modifier.testTag(
                    if (index == 0)
                        "NewOrderTab"
                    else
                        "DoneOrderTab"
                )

            )
        }
    }
}
