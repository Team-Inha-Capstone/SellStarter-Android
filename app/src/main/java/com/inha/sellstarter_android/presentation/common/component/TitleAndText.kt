package com.inha.sellstarter_android.presentation.common.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inha.sellstarter_android.ui.theme.Grey100
import com.inha.sellstarter_android.ui.theme.SellStarterAndroidTheme

@Composable
fun TitleAndText(
    titleText : String,
    contentText : String,
    isAvailableEdit : Boolean = false,
    onClickEdit : () -> Unit = { },
    modifier: Modifier
) {
    Column(
        modifier.padding(vertical = 8.dp)
    ) {
        Text(
            text = titleText,
            style = MaterialTheme.typography.headlineSmall,
        )
        Row {
            Text(
                text = contentText,
                style = MaterialTheme.typography.labelLarge,
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
}

@Preview(showBackground = true)
@Composable
fun PreviewTitleAndText() {
    SellStarterAndroidTheme {
        TitleAndText(
            titleText = "가나디 잡화점",
            contentText = "업종",
            modifier = Modifier.padding(vertical = 24.dp)
        )
    }
}