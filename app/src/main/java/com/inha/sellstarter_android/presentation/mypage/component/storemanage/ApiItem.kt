package com.inha.sellstarter_android.presentation.mypage.component.storemanage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inha.sellstarter_android.domain.ShoppingMallPlatform
import com.inha.sellstarter_android.ui.theme.Grey0
import com.inha.sellstarter_android.ui.theme.Grey100
import com.inha.sellstarter_android.ui.theme.Grey50
import com.inha.sellstarter_android.ui.theme.Typography

@Composable
fun ApiKeyItem(
    apiKey: ApiKey,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onEditClick },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .background(Grey0)
                .clip(shape = RoundedCornerShape(4.dp))
                .border(width = 1.dp, color = Grey100, shape = RoundedCornerShape(4.dp))
        ) {
            Image(
                painter = painterResource(apiKey.platformImage),
                contentDescription = "platformImage",
                modifier = Modifier
                    .size(50.dp)
                    .padding(horizontal = 8.dp)
            )
        }

        Row(
            modifier = Modifier
                .weight(0.9f)
                .padding(horizontal = 4.dp)
                .border(width = 1.dp, color = Grey100, shape = RoundedCornerShape(4.dp))
        ) {
            Text(
                text = apiKey.key,
                color = Grey100,
                style = Typography.bodyLarge,
                modifier = Modifier
                    .weight(0.7f)
                    .padding(8.dp)
                    .align(Alignment.CenterVertically)
            )
            IconButton(
                onClick = onDeleteClick,
                modifier = Modifier.weight(0.1f)
            ) {
                Icon(Icons.Default.Close, contentDescription = "Delete")
            }

        }

        IconButton(
            onClick = onDeleteClick,
            modifier = Modifier.weight(0.1f)
        ) {
            Icon(Icons.Default.Edit, contentDescription = "Delete")
        }
    }

}


@Preview(showBackground = true)
@Composable
fun PreviewApiItem() {
    ApiKeyItem(
        apiKey = ApiKey(
            "123", ShoppingMallPlatform.NAVER.displayName, ShoppingMallPlatform.NAVER.displayImage
        ),
        onDeleteClick = { },
        onEditClick = { },
    )
}