package com.inha.sellstarter_android.presentation.common.screen.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inha.sellstarter_android.presentation.common.component.OneButton
import com.inha.sellstarter_android.ui.theme.Grey0
import com.inha.sellstarter_android.ui.theme.Grey100
import com.inha.sellstarter_android.ui.theme.Purple200
import com.inha.sellstarter_android.ui.theme.AppTypography

@Composable
fun TwoButtonDialog(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
    leftButtonText: String,
    rightButtonText: String,
    leftButtonColor: Color,
    rightButtonColor: Color,
    onLeftClick: () -> Unit,
    onRightClick: () -> Unit,
    leftButtonEnabled: Boolean = true,
    rightButtonEnabled: Boolean = true
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Grey100.copy(alpha = 1f)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Grey0)
                .padding(vertical = 16.dp, horizontal = 24.dp),

            verticalArrangement = Arrangement.Center
        ) {
            content()
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OneButton(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                        .height(55.dp),
                    text = leftButtonText,
                    buttonBackgroundColor = leftButtonColor,
                    enabled = leftButtonEnabled,
                    onClick = onLeftClick
                )

                OneButton(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                        .height(55.dp),
                    text = rightButtonText,
                    buttonBackgroundColor = rightButtonColor,
                    enabled = rightButtonEnabled,
                    onClick = onRightClick
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTwoButtonDialogScreen() {
    TwoButtonDialog(
        content = {
            Text(
                text = "컨텐츠내용",
                style = AppTypography.headlineMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        },
        leftButtonText = "취소",
        rightButtonText = "삭제",
        leftButtonColor = Grey100,
        rightButtonColor = Purple200,
        onLeftClick = { },
        onRightClick = { }
    )
}