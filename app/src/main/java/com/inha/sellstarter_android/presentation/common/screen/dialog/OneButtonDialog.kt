package com.inha.sellstarter_android.presentation.common.screen.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inha.sellstarter_android.presentation.common.component.OneButton
import com.inha.sellstarter_android.ui.theme.Typography

@Composable
fun OneButtonDialog(
    modifier: Modifier = Modifier,
    contentText: String = "",
    buttonText: String,
    buttonEnabled: Boolean = false,
    onButtonClick: () -> Unit,
) {
    Column(
        modifier =
        modifier
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(24.dp),
            textAlign = TextAlign.Center,
            style = Typography.headlineMedium,
            text = contentText,
        )

        Row(
            modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 4.dp, bottom = 24.dp),
        ) {
            OneButton(
                modifier =
                Modifier
                    .wrapContentHeight()
                    .weight(1f)
                    .padding(horizontal = 32.dp),
                text = buttonText,
                enabled = buttonEnabled,
                onClick = onButtonClick,
            )
        }
    }
}

@Composable
@Preview()
fun ButtonDialogPreview() {
    OneButtonDialog(
        modifier = Modifier.wrapContentSize(),
        contentText =
        "해당 재고의 바코드가 아닙니다.\n올바른 바코드를 스캔해주세요.",
        buttonText = "예",
        onButtonClick = { },
    )
}
