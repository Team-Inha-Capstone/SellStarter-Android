package com.inha.sellstarter_android.presentation.order.confim.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.inha.sellstarter_android.ui.theme.Grey100
import com.inha.sellstarter_android.ui.theme.Grey900
import com.inha.sellstarter_android.ui.theme.Typography

@Composable
fun OrderTabRow(
    selectedIndex: Int,
    onTabSelected: (Int) -> Unit
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        listOf("신규 주문", "피킹 완료").forEachIndexed { index, title ->
            Text(
                text = title,
                modifier = Modifier
                    .padding(end = 16.dp)
                    .clickable { onTabSelected(index) },
                color = if (selectedIndex == index) Grey900 else Grey100,
                style = Typography.bodyLarge
            )
        }
    }
}