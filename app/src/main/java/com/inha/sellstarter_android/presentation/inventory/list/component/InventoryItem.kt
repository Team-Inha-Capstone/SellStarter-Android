package com.inha.sellstarter_android.presentation.inventory.list.component

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
import androidx.compose.ui.text.style.TextAlign
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

@Composable
fun InventoryItem(
    inventory: InventorySummary,
    modifier: Modifier
) {
    Card(
        border = BorderStroke(1.dp, Purple50),
        colors = CardDefaults.cardColors(
            contentColor = Grey0,
            containerColor = Grey0
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
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
                    .height(150.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop,
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
                .padding(horizontal = 24.dp, vertical = 4.dp)
                .heightIn(min = 48.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = inventory.name,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = AppTypography.headlineSmall,
                color = Grey900,
            )
        }

        Text(
            text = "보유 수량: ${inventory.quantity}개",
            style = MaterialTheme.typography.bodyLarge,
            color = Grey900,
            modifier = Modifier.padding(horizontal = 24.dp)
        )

        Text(
            text = "옵션 : ${inventory.option}",
            style = MaterialTheme.typography.bodySmall,
            color = Grey100,
            modifier = Modifier.padding(horizontal = 24.dp)
        )
        Spacer(modifier = Modifier.padding(8.dp))
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewInventoryItem() {
    InventoryItem(
        inventory = InventorySummary(
            id = "4",
            name = "오렌지",
            quantity = 5,
            isSoldOut = false,
            option = "",
            imageUrl = "dd"
        ),
        modifier = Modifier.size(500.dp)
    )
}