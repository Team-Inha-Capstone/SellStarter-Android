package com.inha.sellstarter.presentation.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inha.sellstarter.data.model.request.mypage.UserApiDeleteRequestDto
import com.inha.sellstarter.data.model.request.mypage.UserApiRequestDto
import com.inha.sellstarter.data.model.request.mypage.UserApiUpdateRequest
import com.inha.sellstarter.domain.model.UserInfo
import com.inha.sellstarter.presentation.common.screen.TitleScreen
import com.inha.sellstarter.presentation.mypage.component.MyPageProfileContent
import com.inha.sellstarter.presentation.mypage.component.appfont.AppFontSizeContent
import com.inha.sellstarter.presentation.mypage.component.appfont.AppFontSizeDialog
import com.inha.sellstarter.presentation.mypage.component.appfont.FontSizeType
import com.inha.sellstarter.presentation.mypage.component.help.HelpContent
import com.inha.sellstarter.presentation.mypage.component.help.helpItems
import com.inha.sellstarter.presentation.mypage.component.storemanage.MyPageStoreAPIContent
import com.inha.sellstarter.ui.theme.Grey0
import com.inha.sellstarter.ui.theme.Grey50

@Composable
fun MyPageScreen(
    modifier: Modifier,
    userInfo: UserInfo,
    fontScaleType: FontSizeType,
    onFontScaleChanged: (Float) -> Unit,
    onCreateApiKey: (UserApiRequestDto) -> Unit,
    onUpdateApiKey: (UserApiUpdateRequest) -> Unit,
    onDeleteApiKey: (UserApiDeleteRequestDto) -> Unit,
) {
    var isFontDialogVisible by remember { mutableStateOf(false) }

    Column(
        modifier =
            modifier
                .background(color = Grey0)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
    ) {
        TitleScreen(title = "마이페이지")

        MyPageProfileContent(
            users = userInfo,
            modifier = Modifier.fillMaxWidth(),
        )

        Divider(
            color = Grey50,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(0.7.dp)
                    .padding(horizontal = 20.dp),
        )

        MyPageStoreAPIContent(
            users = userInfo,
            onCreateApiKey = onCreateApiKey,
            onUpdateApiKey = onUpdateApiKey,
            onDeleteApiKey = onDeleteApiKey,
            modifier =
                Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth(),
        )

        Divider(
            color = Grey50,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(0.7.dp)
                    .padding(horizontal = 20.dp),
        )

        AppFontSizeContent(
            onClickEdit = { isFontDialogVisible = true },
            fontScale = fontScaleType,
            modifier = Modifier.fillMaxWidth(),
        )

        Divider(
            color = Grey50,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(0.7.dp)
                    .padding(horizontal = 20.dp),
        )

        HelpContent(
            items = helpItems,
            modifier =
                Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth(),
        )
    }

    if (isFontDialogVisible) {
        AppFontSizeDialog(
            currentLimit = fontScaleType,
            onLimitSelected = onFontScaleChanged,
            onConfirm = { isFontDialogVisible = false },
            onDismiss = { isFontDialogVisible = false },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMyPage() {
}
