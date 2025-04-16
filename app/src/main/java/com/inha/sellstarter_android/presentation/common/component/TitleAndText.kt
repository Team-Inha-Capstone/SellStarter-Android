package com.inha.sellstarter_android.presentation.common.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.inha.sellstarter_android.ui.theme.Grey100
import com.inha.sellstarter_android.ui.theme.Typography

@Composable
fun TitleAndText(
    titleText : String,
    contentText : String,
    isAvailableEdit : Boolean = false,
    onClickEdit : () -> Unit = { },
    modifier: Modifier
) {
    Text(
        text = titleText,
        style = Typography.headlineSmall,
        modifier = Modifier.padding(top = 24.dp)
    )

    Row {
        Text(
            text = contentText,
            style = Typography.labelLarge,
        )
        if (isAvailableEdit) {
            IconButton(
                onClick = { onClickEdit() },
                modifier = Modifier.size(24.dp)
                    .padding(start = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Clear",
                    tint = Grey100
                )
            }
        }
    }
}