package com.inha.sellstarter_android.presentation.common.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inha.sellstarter_android.ui.theme.Grey100
import com.inha.sellstarter_android.ui.theme.AppTypography

@Composable
fun DefaultTextField(
    value: String,
    onValueChange: (String) -> Unit,
    innerTextFieldStyle: TextStyle,
    singleLine: Boolean,
    borderColor: Color,
    modifier: Modifier
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        textStyle = innerTextFieldStyle,
        singleLine = singleLine,
        modifier = modifier
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(12.dp),
    )
}


@Preview(showBackground = true)
@Composable
fun PreviewTextField() {
    DefaultTextField(
        value = "무엇이든 물어보세요",
        onValueChange = {},
        innerTextFieldStyle = AppTypography.labelSmall,
        borderColor = Grey100,
        singleLine = false,
        modifier = Modifier
    )
}