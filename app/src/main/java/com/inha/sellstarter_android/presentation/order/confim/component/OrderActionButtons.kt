package com.inha.sellstarter_android.presentation.order.confim.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.inha.sellstarter_android.presentation.common.component.OneButton
import com.inha.sellstarter_android.ui.theme.Purple40

@Composable
fun OrderActionButtons(
    isEnabled: Boolean,
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
            buttonBackgroundColor = Purple40,
            enabled = true,
            modifier = Modifier
                .height(30.dp)
                .width(80.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        OneButton(
            text = "선택 항목 처리완료",
            onClick = onSubmit,
            fontStyle = MaterialTheme.typography.bodyMedium,
            enabled = isEnabled,
            modifier = Modifier
                .height(30.dp)
                .width(120.dp)
        )
    }
}