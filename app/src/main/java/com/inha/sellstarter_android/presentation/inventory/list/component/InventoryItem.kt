package com.inha.sellstarter_android.presentation.inventory.list.component

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.inha.sellstarter_android.domain.model.InventorySummary
import com.inha.sellstarter_android.presentation.common.component.chip.Chip
import com.inha.sellstarter_android.ui.theme.AppTypography
import com.inha.sellstarter_android.ui.theme.Grey0
import com.inha.sellstarter_android.ui.theme.Grey100
import com.inha.sellstarter_android.ui.theme.Grey900
import com.inha.sellstarter_android.ui.theme.Purple50
import com.inha.sellstarter_android.ui.theme.Red200
import com.inha.sellstarter_android.ui.theme.SellStarterAndroidTheme

@Composable
fun InventoryItem(
    inventory: InventorySummary,
    modifier: Modifier
) {
    Card(
        colors = CardDefaults.cardColors(
            contentColor = Grey0,
            containerColor = Grey0,
            disabledContainerColor = Grey0,
            disabledContentColor = Grey0
        ),
        border = BorderStroke(1.dp, Purple50),
        modifier = modifier
            .fillMaxSize()
    ) {

        Box(
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 12.dp)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        ) {
            AsyncImage(
                model = inventory.imageUrl,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(190.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.FillBounds,
                contentDescription = "inventoryImages"
            )
            if (inventory.isSoldOut) {
                Chip(
                    isSelected = true,
                    selectedColor = Red200,
                    text = "재고 품절",
                    fontColor = Grey0,
                    fontStyle = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier
                        .padding(vertical = 1.dp)
                        .wrapContentWidth(),
                    onClick = { }
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 2.dp)
                .heightIn(min = 48.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = inventory.name,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.headlineMedium,
                color = Grey900,
            )
        }

        Text(
            text = "보유 수량: ${inventory.quantity}개",
            style = MaterialTheme.typography.headlineMedium,
            color = Grey900,
            modifier = Modifier.padding(horizontal = 24.dp)
        )

        Text(
            text = "옵션 : ${inventory.option}",
            style = MaterialTheme.typography.bodyMedium,
            color = Grey100,
            modifier = Modifier.padding(horizontal = 24.dp)
        )
        Spacer(modifier = Modifier.padding(8.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewInventoryItem() {
    SellStarterAndroidTheme {
        InventoryItem(
            inventory = InventorySummary(
                id = "4",
                name = "오렌지",
                quantity = 5,
                isSoldOut = true,
                option = "",
                imageUrl = "dd"
            ),
            modifier = Modifier.size(500.dp)
        )
    }
}