package com.inha.sellstarter_android.presentation.inventory.scan

import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.zxing.BarcodeFormat
import com.inha.sellstarter_android.domain.model.Inventory
import com.inha.sellstarter_android.presentation.inventory.scan.component.InvalidBarcodeDialog
import com.inha.sellstarter_android.presentation.inventory.scan.component.ValidBarcodeDialog
import com.journeyapps.barcodescanner.DecoratedBarcodeView
import com.journeyapps.barcodescanner.DefaultDecoderFactory
import kotlinx.coroutines.launch

@Composable
fun BarcodeScannerScreen(
    onBack: () -> Unit,
    onSubmitPicking: (String, Int) -> Unit,
    modifier: Modifier,
) {
    val context = LocalContext.current

    var scannedBarcode by remember { mutableStateOf<String?>(null) }
    var showValidDialog by remember { mutableStateOf(false) }
    var showInvalidDialog by remember { mutableStateOf(false) }

    var selectedQty by remember { mutableStateOf(1) }

    val scope = rememberCoroutineScope()

    Box(modifier = modifier) {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { ctx ->
                DecoratedBarcodeView(ctx).apply {
                    barcodeView.decoderFactory = DefaultDecoderFactory(
                        listOf(BarcodeFormat.CODE_39, BarcodeFormat.CODE_128)
                    )
                    initializeFromIntent(Intent())
                    resume()
                    decodeSingle { result ->
                        val content = result.text
                        scannedBarcode = content
                        // 서버 api로 유효한 코드인지 확인
                        scope.launch {
                            val isValid =
                                true //
                            // view모델 메서드로 넣은 validateBarcodeFromServer(content) -> return Inventory
                            if (isValid) {
                                showValidDialog = true
                            } else {
                                showInvalidDialog = true
                            }
                        }
                    }
                }
            }
        )

        Text(
            text = "재고 바코드를 화면에 스캔해주세요.",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(24.dp),
            style = MaterialTheme.typography.bodyMedium
        )

        // 유효한 바코드 → 수량 입력 Dialog
        if (showValidDialog && scannedBarcode != null) {
            ValidBarcodeDialog(
                inventory = Inventory("1", "사과", 10, "aa", expiration = "2022-01-01", false, location = "위치", option = "option"),
                quantity = selectedQty,
                onQuantityChange = { selectedQty = it },
                onDismiss = {
                    showValidDialog = false
                    scannedBarcode = null
                },
                onConfirm = {
                    onSubmitPicking(scannedBarcode!!, selectedQty)
                    showValidDialog = false
                    scannedBarcode = null
                }
            )
        }

        if (showInvalidDialog) {
            InvalidBarcodeDialog(
                onDismiss = onBack
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBarcodeScanScreen() {
    BarcodeScannerScreen(
        modifier = Modifier.fillMaxSize(),
        onBack = { },
        onSubmitPicking = { _, _ -> }
    )
}

