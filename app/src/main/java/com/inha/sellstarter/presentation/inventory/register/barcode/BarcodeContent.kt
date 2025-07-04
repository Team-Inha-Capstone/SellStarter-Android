package com.inha.sellstarter.presentation.inventory.register.barcode

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import com.inha.sellstarter.data.model.request.inventory.InventoryCreateRequestDto
import com.inha.sellstarter.ui.theme.Grey0

@Composable
fun BarcodeContent(
    draft: InventoryCreateRequestDto,
    barcodeImage: Bitmap?,
) {
    Column(
        modifier =
            Modifier
                .background(Grey0)
                .padding(16.dp)
                .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "상품 이름 : ${draft.inventoryName}",
            style = MaterialTheme.typography.bodyLarge,
        )

        Text(
            text = "재고 수량 : ${draft.inventoryCount} 개",
            style = MaterialTheme.typography.bodyLarge,
        )
        Text(
            text = "재고 위치 : ${draft.inventoryLocation}",
            style = MaterialTheme.typography.bodyLarge,
        )

        Spacer(modifier = Modifier.height(12.dp))

        barcodeImage?.let {
            Image(
                bitmap = it.asImageBitmap(),
                contentDescription = null,
                modifier =
                    Modifier
                        .width(250.dp)
                        .height(150.dp),
            )
        } ?: Text("바코드 생성 실패")

        Spacer(modifier = Modifier.height(8.dp))
        Text("바코드 생성 완료")
    }
}
