package com.inha.sellstarter.util.paging

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun PaginationBar(
    currentPage: Int,
    totalPages: Int,
    onPageSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.Center,
    ) {
        IconButton(
            onClick = { onPageSelected((currentPage - 1).coerceAtLeast(0)) },
            enabled = currentPage > 0,
        ) { Icon(Icons.Default.KeyboardArrowLeft, contentDescription = "이전") }
        (0 until totalPages).forEach { page ->
            TextButton(onClick = { onPageSelected(page) }) {
                Text(
                    text = page.toString(),
                    fontWeight = if (page == currentPage) FontWeight.Bold else FontWeight.Normal,
                )
            }
        }
        IconButton(
            onClick = { onPageSelected((currentPage + 1).coerceAtMost(totalPages)) },
            enabled = currentPage < totalPages - 1,
        ) { Icon(Icons.Default.KeyboardArrowRight, contentDescription = "다음") }
    }
}
