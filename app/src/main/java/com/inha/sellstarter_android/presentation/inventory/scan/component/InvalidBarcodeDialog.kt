package com.inha.sellstarter_android.presentation.inventory.scan.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inha.sellstarter_android.presentation.common.screen.dialog.OneButtonDialog

@Composable
fun InvalidBarcodeDialog(onDismiss: () -> Unit) {
    OneButtonDialog(
        contentText = "존재하지 않는 재고입니다.\n올바른 바코드를 스캔해주세요.",
        buttonEnabled = true,
        buttonText = "확인",
        onButtonClick = onDismiss,
        modifier = Modifier.fillMaxSize(),
        content = { }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewInValidBarcodeDialog() {
    InvalidBarcodeDialog(
        { }
    )
}