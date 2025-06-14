package com.inha.sellstarter_android.presentation.inventory.register.barcode

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.inha.sellstarter_android.presentation.common.component.OneButton
import com.inha.sellstarter_android.presentation.common.screen.ErrorScreen
import com.inha.sellstarter_android.presentation.common.screen.dialog.TwoButtonDialog
import com.inha.sellstarter_android.presentation.inventory.InventoryViewModel
import com.inha.sellstarter_android.ui.theme.Grey0
import com.inha.sellstarter_android.ui.theme.Grey100
import com.inha.sellstarter_android.ui.theme.Purple200
import com.inha.sellstarter_android.util.barcode.BarcodeUtils
import com.inha.sellstarter_android.util.barcode.CaptureBitmap
import com.inha.sellstarter_android.util.barcode.saveBitmapToGallery
import com.inha.sellstarter_android.util.base.UiState

@Composable
fun BarcodeConfirmDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    viewModel: InventoryViewModel = hiltViewModel()
) {
    val draft by rememberUpdatedState(viewModel.draft)
    val context = LocalContext.current

    val registerState by viewModel.registerState.collectAsState()

    LaunchedEffect(registerState) {
        if (registerState is UiState.Success) {
            viewModel.clearDraft()
            onConfirm()
        }
    }

    if (draft != null) {

        val barcodeImage = remember(draft!!.barcodeId) {
            BarcodeUtils.generateBarcodeBitmap(draft!!.barcodeId)
        }

        val captureCallback = CaptureBitmap {
            BarcodeCaptureContent(
                draft = draft!!,
                barcodeImage = barcodeImage
            )
        }

        TwoButtonDialog(
            content = {
                BarcodeContent(
                    draft = draft!!,
                    barcodeImage = barcodeImage
                )
                OneButton(
                    text = "📸 이미지 저장",
                    onClick = {
                        val bitmap = captureCallback.invoke()
                        val success = saveBitmapToGallery(context, bitmap, draft!!.inventoryName)
                        Toast.makeText(
                            context,
                            if (success) "이미지 저장 완료" else "저장 실패",
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    buttonBackgroundColor = Purple200,
                    fontColor = Grey0,
                    enabled = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp)
                )
            },
            leftButtonText = "취소",
            rightButtonText = "완료",
            leftButtonColor = Grey100,
            rightButtonColor = Purple200,
            onLeftClick = onDismiss,
            onRightClick = {
                viewModel.registerInventory(
                    imageUri = viewModel.draftImageUri,
                    context = context,
                    inventoryCreateRequest = draft!!
                )
            }
        )
    } else {
        ErrorScreen(errorText = "재고 정보를 가져오는 데 실패했습니다.")
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBarcodeConfirmDialog() {
    val mockBitmap =
        remember { BarcodeUtils.generateBarcodeBitmap(content = "ABC", width = 300, height = 100) }

}