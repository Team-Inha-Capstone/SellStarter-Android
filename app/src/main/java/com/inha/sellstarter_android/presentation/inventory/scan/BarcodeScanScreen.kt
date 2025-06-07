package com.inha.sellstarter_android.presentation.inventory.scan

import android.view.View
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.zxing.BarcodeFormat
import com.google.zxing.ResultPoint
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.DecoratedBarcodeView
import com.journeyapps.barcodescanner.DefaultDecoderFactory

@Composable
fun BarcodeScannerScreen(
    barcodeId: String,
    onBack: () -> Unit,
    onSubmitPicking: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    Box(modifier = modifier) {
        AndroidView(
            factory = { ctx ->
                DecoratedBarcodeView(ctx).apply {
                    setDecoderFactory(
                        DefaultDecoderFactory(listOf(BarcodeFormat.CODE_39, BarcodeFormat.CODE_128))
                    )
                    resume()
                    statusView.visibility = View.GONE
                    decodeContinuous(object : BarcodeCallback {
                        override fun barcodeResult(result: BarcodeResult) {
                            if (result.text == barcodeId) {
                                onSubmitPicking(barcodeId)
                            } else {
                                onBack()
                            }
                            pause()
                        }

                        override fun possibleResultPoints(resultPoints: List<ResultPoint>) {}
                    })
                }
            },
            modifier = modifier
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


