package com.inha.sellstarter_android.presentation.mypage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inha.sellstarter_android.domain.ShoppingMallType
import com.inha.sellstarter_android.domain.Users
import com.inha.sellstarter_android.presentation.common.screen.TitleScreen
import com.inha.sellstarter_android.presentation.mypage.help.HelpSection
import com.inha.sellstarter_android.presentation.mypage.help.helpItems
import com.inha.sellstarter_android.presentation.mypage.storemanage.MyPageStoreAPISection

@Composable
fun MyPageScreen(
    users: Users,
    modifier: Modifier
) {
    Column(
        modifier = modifier
    ) {
        TitleScreen(
            title = "마이페이지"
        )

        MyPageProfileSection(
            users = users,
            modifier = Modifier.padding(
                vertical = 8.dp,
                horizontal = 12.dp)
        )

        MyPageStoreAPISection(
            users = users,
            onClickAddKey = { },
            onClickModifyKey = { },
            modifier = Modifier.padding(
                vertical = 8.dp,
                horizontal = 24.dp)
        )

        AppFontSizeSection(
            onClickEdit = { },
            modifier = Modifier.padding(
                vertical = 8.dp,
                horizontal = 24.dp)
        )

        HelpSection(
            items = helpItems,
            modifier = Modifier.padding(
                vertical = 8.dp,
                horizontal = 24.dp)
        )
    }


}


@Preview(showBackground = true)
@Composable
fun PreviewMyPage() {
    MyPageScreen(
        users = Users(1, "듀가나디 잡화점", ShoppingMallType.HOUSEHOLD_GOODS),
        modifier = Modifier.fillMaxSize()
    )
}