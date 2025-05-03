package com.inha.sellstarter_android.presentation.inventory.register.component

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.inha.sellstarter_android.presentation.common.component.DatePickerModal
import com.inha.sellstarter_android.presentation.common.component.TitleAndText
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun InventoryExpirationDateContent(
    modifier: Modifier = Modifier
) {

    var selectedDateMillis by remember { mutableStateOf<Long?>(null) }
    var showDatePicker by remember { mutableStateOf(false) }

    val selectedDateText = remember(selectedDateMillis) {
        selectedDateMillis?.let {
            val formatter = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())
            formatter.format(Date(it))
        } ?: "날짜를 선택해주세요"
    }

    TitleAndText(
        titleText = "재고 유통기한",
        contentText = selectedDateText,
        isAvailableEdit = true,
        onClickEdit = { showDatePicker = true },
        modifier = modifier
    )

    if (showDatePicker) {
        DatePickerModal(
            onDateSelected = { millis ->
                selectedDateMillis = millis
            },
            onDismiss = { showDatePicker = false },
            modifier = Modifier.padding(24.dp)
        )
    }
}
