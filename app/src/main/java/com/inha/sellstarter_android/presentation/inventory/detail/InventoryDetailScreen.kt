package com.inha.sellstarter_android.presentation.inventory.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
    onClickPicking : () -> Unit,
    modifier: Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize()
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
                model = inventory.image,
                contentDescription = "inventoryDetailImage",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )

            TitleAndText(
                titleText = "상품명",
                contentText = inventory.name,
                modifier = Modifier
            )

            TitleAndText(
                titleText = "보유재고",
                contentText = "${inventory.quantity}개",
                isAvailableEdit = true,
                onClickEdit = { },
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
                contentText = inventory.expirationDate,
                modifier = Modifier
            )

            InventoryDetailGraph(
                titleText = "재고 주문 추이 및 예측",
                modifier = Modifier.fillMaxWidth()
            )
        }

        OneButton(
            text = "재고피킹",
            buttonBackgroundColor = Purple200,
            fontColor = Grey0,
            enabled = true,
            onClick = onClickPicking,
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        )

    }
}


@Preview(showBackground = true)
@Composable
fun PreviewInventoryDetailScreen() {

    InventoryDetailScreen(
        inventory = Inventory(1, "사과", 10, "aa", true, "2022-10-13", "2022-10-13"),
        onClickPicking = { },
        modifier = Modifier.fillMaxSize()
    )

}