package com.inha.sellstarter_android.presentation.mypage

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.inha.sellstarter_android.presentation.common.component.TitleAndText

@Composable
fun AppFontSizeSection(
    onClickEdit: () -> Unit,
    modifier: Modifier
) {

    TitleAndText(
        titleText = "앱 글자 크기 조정",
        contentText = "현재 크기 : 보통",
        isAvailableEdit = true,
        onClickEdit = onClickEdit,
        modifier = modifier
    )

}