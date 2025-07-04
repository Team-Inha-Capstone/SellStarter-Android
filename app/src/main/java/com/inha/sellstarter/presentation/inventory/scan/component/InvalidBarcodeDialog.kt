package com.inha.sellstarter.presentation.inventory.scan.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.inha.sellstarter.presentation.common.screen.dialog.OneButtonDialog
import com.inha.sellstarter.ui.theme.SellStarterAndroidTheme

@Composable
fun InvalidBarcodeDialog(
    onDismiss: () -> Unit,
    modifier: Modifier,
) {
    OneButtonDialog(
        contentText = "존재하지 않는 재고입니다.\n올바른 바코드를 스캔해주세요.",
        buttonEnabled = true,
        buttonText = "확인",
        onButtonClick = onDismiss,
        modifier = modifier,
        content = { },
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewInvalidBarcodeDialog() {
    SellStarterAndroidTheme {
        InvalidBarcodeDialog(
            onDismiss = {},
            modifier = Modifier,
        )
    }
}
