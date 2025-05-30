package com.inha.sellstarter_android.presentation.inventory.scan

import android.content.Intent
import android.util.Log
import android.view.View
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
import com.google.zxing.ResultPoint
import com.inha.sellstarter_android.domain.model.Inventory
import com.inha.sellstarter_android.presentation.inventory.scan.component.InvalidBarcodeDialog
import com.inha.sellstarter_android.presentation.inventory.scan.component.ValidBarcodeDialog
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.DecoratedBarcodeView
import com.journeyapps.barcodescanner.DefaultDecoderFactory
import kotlinx.coroutines.launch

@Composable
fun BarcodeScannerScreen(
    barcodeId: String,
    onBack: () -> Unit,
    onSubmitPicking: (String) -> Unit,
    modifier: Modifier,
) {
    val context = LocalContext.current

    var scannedBarcode by remember { mutableStateOf<String?>(null) }
    var showValidDialog by remember { mutableStateOf(false) }
    var showInvalidDialog by remember { mutableStateOf(false) }

    var selectedQty by remember { mutableStateOf(1) }

    Box(modifier = modifier) {
        AndroidView(
            factory = { ctx ->
                val barcodeView =
                    DecoratedBarcodeView(ctx).apply {
                        setDecoderFactory(
                            DefaultDecoderFactory(
                                listOf(
                                    BarcodeFormat.CODE_39,
                                    BarcodeFormat.CODE_128
                                )
                            )
                        )
                        resume() // 카메라 시작
                        this.statusView.visibility = View.GONE // Zxing 아래 텍스트 지우기
                        decodeContinuous(
                            object : BarcodeCallback {
                                override fun barcodeResult(result: BarcodeResult) {
                                    if (result.toString() == barcodeId) {
                                        onSubmitPicking(barcodeId)
                                    } else {
                                        onBack()
                                    }
                                    pause() // 중복 방지
                                }

                                override fun possibleResultPoints(resultPoints: List<ResultPoint>) {}
                            },
                        )
                    }
                barcodeView // DecoratedBarcodeView 반환
            },
            modifier = modifier,
        )


        Text(
            text = "재고 바코드를 화면에 스캔해주세요.",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(24.dp),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBarcodeScanScreen() {
}


