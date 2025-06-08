package com.inha.sellstarter_android.presentation.mypage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inha.sellstarter_android.data.model.request.mypage.UserApiDeleteRequestDto
import com.inha.sellstarter_android.data.model.request.mypage.UserApiRequestDto
import com.inha.sellstarter_android.data.model.request.mypage.UserApiUpdateRequest
import com.inha.sellstarter_android.domain.model.UserInfo
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
    modifier: Modifier,
    userInfo: UserInfo,
    fontScaleType: FontSizeType,
    onFontScaleChanged: (Float) -> Unit,
    onCreateApiKey: (UserApiRequestDto) -> Unit,
    onUpdateApiKey: (UserApiUpdateRequest) -> Unit,
    onDeleteApiKey: (UserApiDeleteRequestDto) -> Unit
) {
    var isFontDialogVisible by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        TitleScreen(title = "마이페이지")

        MyPageProfileContent(
            users = userInfo,
            modifier = Modifier.fillMaxWidth()
        )

        MyPageStoreAPIContent(
            users = userInfo,
            onCreateApiKey = onCreateApiKey,
            onUpdateApiKey = onUpdateApiKey,
            onDeleteApiKey = onDeleteApiKey,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth()
        )

        AppFontSizeContent(
            onClickEdit = { isFontDialogVisible = true },
            fontScale = fontScaleType,
            modifier = Modifier.fillMaxWidth()
        )

        HelpContent(
            items = helpItems,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth()
        )
    }

    if (isFontDialogVisible) {
        AppFontSizeDialog(
            currentLimit = fontScaleType,
            onLimitSelected = onFontScaleChanged,
            onConfirm = { isFontDialogVisible = false },
            onDismiss = { isFontDialogVisible = false }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMyPage() {
}

