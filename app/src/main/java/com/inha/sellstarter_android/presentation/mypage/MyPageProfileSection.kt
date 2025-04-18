package com.inha.sellstarter_android.presentation.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inha.sellstarter_android.R
import com.inha.sellstarter_android.domain.ShoppingMallType
import com.inha.sellstarter_android.domain.Users
import com.inha.sellstarter_android.presentation.common.component.TitleAndText

@Composable
fun MyPageProfileSection(
    users: Users,
    modifier: Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.ic_profile),
            contentDescription = "profileImage",
            modifier = Modifier
                .size(100.dp)
                .padding(12.dp)
        )

        TitleAndText(
            titleText = users.storeName,
            contentText = "업종 : ${users.category.displayName}",
            modifier = Modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMyPageProfileSection() {
    MyPageProfileSection(
        users = Users(1, "듀가나디 잡화점", ShoppingMallType.HOUSEHOLD_GOODS),
        modifier = Modifier
    )
}