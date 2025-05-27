package com.inha.sellstarter_android.presentation.inventory.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.inha.sellstarter_android.R
import com.inha.sellstarter_android.ui.theme.AppTypography

@Composable
fun InventoryDetailGraph(
    titleText : String,
    modifier: Modifier
) {
    Text(
        text = titleText,
        style = AppTypography.headlineSmall,
        modifier = Modifier.padding(top = 24.dp, bottom = 12.dp)
    )

    Image(
        painter = painterResource(R.drawable.ic_graph),
        contentDescription = "dummyGraph",
        contentScale = ContentScale.FillBounds,
        modifier = modifier.height(200.dp)
    )
}