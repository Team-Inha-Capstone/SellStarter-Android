package com.inha.sellstarter.presentation.common.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inha.sellstarter.ui.theme.AppTypography
import com.inha.sellstarter.ui.theme.Grey100
import com.inha.sellstarter.ui.theme.Grey50

@Composable
fun DefaultTextField(
    value: String,
    onValueChange: (String) -> Unit,
    innerTextFieldStyle: TextStyle,
    singleLine: Boolean,
    borderColor: Color,
    placeholder: String = "",
    keyboardType: KeyboardType = KeyboardType.Text,
    filter: (String) -> String = { it },
    modifier: Modifier,
) {
    BasicTextField(
        value = value,
        onValueChange = { new ->
            val filtered = filter(new)
            onValueChange(filtered)
        },
        textStyle = innerTextFieldStyle,
        singleLine = singleLine,
        decorationBox = { innerTextField ->
            Box(
                modifier = modifier.wrapContentHeight(),
                contentAlignment = Alignment.CenterStart,
            ) {
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        style = innerTextFieldStyle,
                        color = Grey50,
                    )
                }
                innerTextField()
            }
        },
        keyboardOptions =
            KeyboardOptions(
                keyboardType = keyboardType,
            ),
        modifier =
            modifier
                .border(
                    width = 1.dp,
                    color = borderColor,
                    shape = RoundedCornerShape(10.dp),
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
        modifier = Modifier,
    )
}
