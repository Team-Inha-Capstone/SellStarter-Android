package com.inha.sellstarter_android.presentation.mypage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.inha.sellstarter_android.domain.model.ShoppingMallType
import com.inha.sellstarter_android.domain.model.Users
import com.inha.sellstarter_android.presentation.common.screen.TitleScreen
import com.inha.sellstarter_android.presentation.mypage.component.appfont.AppFontSizeContent
import com.inha.sellstarter_android.presentation.mypage.component.MyPageProfileContent
import com.inha.sellstarter_android.presentation.mypage.component.appfont.AppFontSizeDialog
import com.inha.sellstarter_android.presentation.mypage.component.appfont.FontSizeType
import com.inha.sellstarter_android.presentation.mypage.component.help.HelpContent
import com.inha.sellstarter_android.presentation.mypage.component.help.helpItems
import com.inha.sellstarter_android.presentation.mypage.component.storemanage.MyPageStoreAPIContent

@Composable
fun MyPageScreen(
    users: Users,
    onFontScaleChanged: (Float) -> Unit,
    modifier: Modifier,
) {
    val fontSizeViewModel: FontSizeViewModel = hiltViewModel()
    val fontScale by fontSizeViewModel.fontScale.collectAsState()
    val currentFontSizeType = FontSizeType.fromScale(fontScale)
    var isFontDialogVisible by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
    ) {
        TitleScreen(
            title = "마이페이지"
        )

        MyPageProfileContent(
            users = users,
            modifier = Modifier.padding(
                vertical = 12.dp,
                horizontal = 12.dp
            )
        )

        MyPageStoreAPIContent(
            users = users,
            onClickAddKey = { },
            onClickModifyKey = { },
            modifier = Modifier.padding(
                vertical = 12.dp,
                horizontal = 24.dp
            )
        )

        AppFontSizeContent(
            onClickEdit = { isFontDialogVisible = true },
            fontScale = currentFontSizeType,
            modifier = Modifier.padding(
                vertical = 12.dp,
                horizontal = 24.dp
            )
        )

        HelpContent(
            items = helpItems,
            modifier = Modifier.padding(
                vertical = 8.dp,
                horizontal = 24.dp
            )
        )
    }

    if (isFontDialogVisible) {
        AppFontSizeDialog(
            currentLimit = currentFontSizeType,
            onLimitSelected = { onFontScaleChanged(it) },
            onConfirm = { isFontDialogVisible = false },
            onDismiss = { isFontDialogVisible = false }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewMyPage() {
    MyPageScreen(
        users = Users(1, "듀가나디 잡화점", ShoppingMallType.HOUSEHOLD_GOODS),
        modifier = Modifier.fillMaxSize(),
        onFontScaleChanged = { }
    )
}

