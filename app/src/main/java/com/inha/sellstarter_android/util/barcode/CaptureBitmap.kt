package com.inha.sellstarter_android.util.barcode

import android.graphics.Bitmap
import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.drawToBitmap

@Composable
fun CaptureBitmap(
    content: @Composable ()->Unit
) : () -> Bitmap {
    val context = LocalContext.current

    val composeView = remember { ComposeView(context) }
    fun captureBitmap(): Bitmap = composeView.drawToBitmap()

    AndroidView(
        factory = {
            composeView.apply {
                setContent {
                    content.invoke()
                }
            }
        }
    )

    return ::captureBitmap
}