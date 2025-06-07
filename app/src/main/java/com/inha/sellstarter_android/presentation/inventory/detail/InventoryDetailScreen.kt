package com.inha.sellstarter_android.presentation.inventory.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.inha.sellstarter_android.domain.model.Inventory
import com.inha.sellstarter_android.presentation.common.component.TitleAndText
import com.inha.sellstarter_android.presentation.common.component.OneButton
import com.inha.sellstarter_android.presentation.common.screen.TitleScreen
import com.inha.sellstarter_android.ui.theme.Grey0
import com.inha.sellstarter_android.ui.theme.Purple200

@Composable
fun InventoryDetailScreen(
    inventory: Inventory,
    graphUrl: String,
    onBack: () -> Unit,
    onClickEditCount: (Int) -> Unit,
    modifier: Modifier,
) {
    var showEditDialog by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        TitleScreen(
            title = "재고 상세확인"
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp)
        ) {
            AsyncImage(
                model = inventory.imageUrl,
                contentDescription = "inventoryDetailImage",
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )

            TitleAndText(
                titleText = "상품명",
                contentText = inventory.name,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            TitleAndText(
                titleText = "보유재고",
                contentText = "${inventory.quantity}개",
                isAvailableEdit = true,
                onClickEdit = { showEditDialog = true },
                modifier = Modifier
            )

            TitleAndText(
                titleText = "바코드번호",
                contentText = "${inventory.id}",
                modifier = Modifier
            )

            TitleAndText(
                titleText = "재고 위치",
                contentText = inventory.location,
                modifier = Modifier
            )

            TitleAndText(
                titleText = "재고 유통기한",
                contentText = inventory.expiration,
                modifier = Modifier
            )

            InventoryDetailGraph(
                titleText = "재고 주문 추이 및 예측",
                graphUrl = graphUrl,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1000.dp)
            )

            Spacer(modifier = Modifier.size(16.dp))
        }
    }

    if (showEditDialog) {
        InventoryCountEditDialog(
            currentCount = inventory.quantity,
            onDismiss = { showEditDialog = false },
            onConfirm = { newCount ->
                onClickEditCount(newCount)
                showEditDialog = false
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewInventoryDetailScreen() {

}