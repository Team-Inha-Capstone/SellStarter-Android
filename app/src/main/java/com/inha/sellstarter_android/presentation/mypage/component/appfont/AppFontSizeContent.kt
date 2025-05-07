package com.inha.sellstarter_android.presentation.mypage.component.appfont

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.inha.sellstarter_android.presentation.common.component.TitleAndText
import com.inha.sellstarter_android.presentation.mypage.FontScaleViewModel

@Composable
fun AppFontSizeContent(
    onClickEdit: () -> Unit,
    fontScale: FontSizeType,
    modifier: Modifier
) {

    TitleAndText(
        titleText = "앱 글자 크기 조정",
        contentText = "현재 크기 : ${fontScale.label}",
        isAvailableEdit = true,
        onClickEdit = onClickEdit,
        modifier = modifier
    )

}