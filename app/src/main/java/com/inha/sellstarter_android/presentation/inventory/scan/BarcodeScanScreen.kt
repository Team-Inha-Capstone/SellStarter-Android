package com.inha.sellstarter_android.presentation.inventory.scan

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.google.zxing.BarcodeFormat
import com.google.zxing.ResultPoint
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.DecoratedBarcodeView
import com.journeyapps.barcodescanner.DefaultDecoderFactory

@Composable
fun BarcodeScanScreen(
    modifier: Modifier,
    onSuccessBarcodeScan:(String) -> Unit,
) {
    val context = LocalContext.current
    AndroidView(
        factory = { ctx ->
            val barcodeView =
                DecoratedBarcodeView(ctx).apply {
                    setDecoderFactory(DefaultDecoderFactory(listOf(BarcodeFormat.CODE_39)))
                    resume() // 카메라 시작
                    decodeContinuous(
                        object : BarcodeCallback {
                            override fun barcodeResult(result: BarcodeResult) {
                                // QR 코드가 인식되었을 때의 처리 로직
                                onSuccessBarcodeScan(result.toString())
                                pause() // 스캔 후 일시 정지 (필요 시)
                            }
                            override fun possibleResultPoints(resultPoints: List<ResultPoint>) {}
                        },
                    )
                }
            barcodeView // DecoratedBarcodeView 반환
        },
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewBarcodeScanScreen(){
    BarcodeScanScreen(
        modifier = Modifier.fillMaxSize(),
        onSuccessBarcodeScan = { }
    )
}

