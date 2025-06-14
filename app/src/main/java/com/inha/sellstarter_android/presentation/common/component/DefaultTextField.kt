package com.inha.sellstarter_android.presentation.common.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inha.sellstarter_android.ui.theme.Grey100
import com.inha.sellstarter_android.ui.theme.AppTypography
import com.inha.sellstarter_android.ui.theme.Grey20
import com.inha.sellstarter_android.ui.theme.Grey50

@Composable
fun DefaultTextField(
    value: String,
    onValueChange: (String) -> Unit,
    innerTextFieldStyle: TextStyle,
    singleLine: Boolean,
    borderColor: Color,
    placeholder: String = "",
    modifier: Modifier
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        textStyle = innerTextFieldStyle,
        singleLine = singleLine,
        decorationBox = { innerTextField ->
            Box(
                modifier = modifier.wrapContentHeight(),
                contentAlignment = Alignment.CenterStart
            ) {
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        style = innerTextFieldStyle,
                        color = Grey50
                    )
                }
                innerTextField()
            }
        },
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
        value = "hihihi",
        onValueChange = {},
        innerTextFieldStyle = AppTypography.labelSmall,
        borderColor = Grey100,
        placeholder = "라라랄",
        singleLine = false,
        modifier = Modifier
    )
}