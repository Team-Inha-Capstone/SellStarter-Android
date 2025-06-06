package com.inha.sellstarter_android.presentation.order.confirm.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inha.sellstarter_android.presentation.common.component.OneButton
import com.inha.sellstarter_android.ui.theme.Purple200
import com.inha.sellstarter_android.ui.theme.SellStarterAndroidTheme

@Composable
fun OrderActionButtons(
    isEnabled: Boolean,
    currentTabIndex: Int,
    onSelectAll: () -> Unit,
    onSubmit: () -> Unit
) {
    Row(
        modifier = Modifier
            .wrapContentWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        OneButton(
            text = "전체 선택",
            onClick = onSelectAll,
            fontStyle = MaterialTheme.typography.bodyMedium,
            buttonBackgroundColor = Purple200,
            enabled = true,
            modifier = Modifier
                .height(40.dp)
                .wrapContentSize()
        )

        Spacer(modifier = Modifier.width(8.dp))

        OneButton(
            text = if(currentTabIndex == 0) "선택항목 피킹완료" else "선택항목 출고완료",
            onClick = onSubmit,
            fontStyle = MaterialTheme.typography.bodyMedium,
            buttonBackgroundColor = Purple200,
            enabled = isEnabled,
            modifier = Modifier
                .height(40.dp)
                .wrapContentSize()
        )
    }
}

@Preview(showBackground = true, name = "OrderActionButtons - Disabled")
@Composable
private fun OrderActionButtonsPreviewDisabled() {
    SellStarterAndroidTheme {
        OrderActionButtons(
            isEnabled = false,
            currentTabIndex = 1,
            onSelectAll = { },
            onSubmit = { }
        )
    }
}
