package com.inha.sellstarter.presentation.mypage.component.appfont

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.inha.sellstarter.presentation.common.component.TitleAndText
import com.inha.sellstarter.ui.theme.Grey0

@Composable
fun AppFontSizeContent(
    onClickEdit: () -> Unit,
    fontScale: FontSizeType,
    modifier: Modifier,
) {
    Box(
        modifier =
            modifier
                .background(Grey0)
                .padding(vertical = 12.dp),
    ) {
        TitleAndText(
            titleText = "🖋️ 앱 글자 크기 조정",
            contentText = "현재 크기 : ${fontScale.label}",
            isAvailableEdit = true,
            onClickEdit = onClickEdit,
            modifier = Modifier.padding(horizontal = 24.dp),
        )
    }
}
