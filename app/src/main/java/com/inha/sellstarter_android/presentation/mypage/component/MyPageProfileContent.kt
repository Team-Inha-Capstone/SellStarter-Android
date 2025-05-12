package com.inha.sellstarter_android.presentation.mypage.component

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
import com.inha.sellstarter_android.domain.model.ShoppingMallType
import com.inha.sellstarter_android.domain.model.UserInfo
import com.inha.sellstarter_android.domain.model.Users
import com.inha.sellstarter_android.presentation.common.component.TitleAndText

@Composable
fun MyPageProfileContent(
    users: UserInfo,
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
            titleText = users.userName,
            contentText = "업종 : ${users.shoppingCategory}",
            modifier = Modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMyPageProfileSection() {
}