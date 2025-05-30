package com.inha.sellstarter_android.presentation.mypage.component.appfont

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inha.sellstarter_android.presentation.common.component.PurpleSlider
import com.inha.sellstarter_android.presentation.common.screen.dialog.OneButtonDialog
import com.inha.sellstarter_android.ui.theme.Grey900

@Composable
fun AppFontSizeDialog(
    currentLimit: FontSizeType,
    onLimitSelected: (Float) -> Unit,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    var selectedLimit by remember { mutableStateOf(currentLimit.scale) }

    OneButtonDialog(
        contentText = "앱 내 글자 크기를 조절하세요.",
        buttonText = "적용",
        buttonEnabled = selectedLimit != currentLimit.scale,
        onButtonClick = {
            onLimitSelected(selectedLimit)
            onConfirm()
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {
                PurpleSlider(
                    text = "현재 글자 크기 : ${currentLimit.label}",
                    fontColor = Grey900,
                    fontStyle = MaterialTheme.typography.headlineMedium,
                    step = 3, //  tep = 3, // 총 4 구간: 0.8, 1.0, 1.2, 1.5
                    currentValue = selectedLimit,
                    valueRange = 0.8f..1.5f,
                    onSliderChange = { selectedLimit = it }
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewAppFontSizeDialog() {
    AppFontSizeDialog(
        onDismiss = { },
        onConfirm = { },
        currentLimit = FontSizeType.MEDIUM,
        onLimitSelected = { }
    )
}