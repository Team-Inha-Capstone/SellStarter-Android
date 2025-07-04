package com.inha.sellstarter.presentation.inventory.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.inha.sellstarter.domain.model.Inventory
import com.inha.sellstarter.presentation.common.component.TitleAndText
import com.inha.sellstarter.presentation.common.screen.TitleScreen
import com.inha.sellstarter.ui.theme.Grey0
import com.inha.sellstarter.ui.theme.SellStarterAndroidTheme

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
        modifier =
            modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(color = Grey0),
    ) {
        TitleScreen(
            title = "재고 상세확인",
        )

        Column(
            modifier =
                Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 24.dp),
        ) {
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
            ) {
                AsyncImage(
                    model = inventory.imageUrl,
                    contentDescription = "inventoryDetailImage",
                    modifier =
                        Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .size(160.dp),
                    contentScale = ContentScale.Crop,
                )

                Column(
                    modifier =
                        Modifier
                            .weight(1f)
                            .align(Alignment.CenterVertically)
                            .padding(horizontal = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    TitleAndText(
                        titleText = "상품명",
                        contentText = inventory.name,
                        modifier = Modifier,
                    )

                    TitleAndText(
                        titleText = "보유재고",
                        contentText = "${inventory.quantity}개",
                        isAvailableEdit = true,
                        onClickEdit = { showEditDialog = true },
                        modifier = Modifier,
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            TitleAndText(
                titleText = "바코드번호",
                contentText = inventory.id,
                modifier = Modifier,
            )

            TitleAndText(
                titleText = "재고 위치",
                contentText = inventory.location,
                modifier = Modifier,
            )

            TitleAndText(
                titleText = "재고 유통기한",
                contentText = inventory.expiration,
                modifier = Modifier,
            )

            InventoryDetailGraph(
                titleText = "재고 주문 추이 및 예측",
                graphUrl = graphUrl,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
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
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewInventoryDetailScreen() {
    SellStarterAndroidTheme {
        val sampleInventory =
            Inventory(
                id = "1234567890123",
                name = "샘플 상품",
                quantity = 50,
                imageUrl = "", // 임시 이미지
                expiration = "2025-12-31",
                isSoldOut = false,
                option = "옵션 A",
                location = "창고 1번 선반",
            )

        InventoryDetailScreen(
            inventory = sampleInventory,
            graphUrl = "",
            // 그래프용 임시 이미지
            onBack = {},
            onClickEditCount = {},
            modifier = Modifier,
        )
    }
}
