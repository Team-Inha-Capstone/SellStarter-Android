package com.inha.sellstarter.presentation.common.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.inha.sellstarter.ui.theme.Blue200
import com.inha.sellstarter.ui.theme.Grey100
import com.inha.sellstarter.ui.theme.Grey900
import com.inha.sellstarter.ui.theme.Red200

@Composable
fun OutlinedTextField(
    value: String,
    placeholder: String,
    isAvailable: Boolean = false,
    isError: Boolean = false,
    availableDescription: String,
    errorDescription: String,
    singleLine: Boolean,
    onValueChange: (String) -> Unit,
    innerTextFieldStyle: TextStyle,
    modifier: Modifier,
) {
    Column(modifier = modifier) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = innerTextFieldStyle,
            decorationBox = { innerTextField ->
                if (value.isBlank()) {
                    Text(
                        text = placeholder,
                        style = innerTextFieldStyle.copy(color = Grey100),
                    )
                }
                innerTextField()
            },
            singleLine = singleLine,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color =
                            if (isAvailable) {
                                Blue200
                            } else if (isError) {
                                Red200
                            } else {
                                Grey100
                            },
                        shape = RoundedCornerShape(10.dp),
                    )
                    .padding(vertical = 24.dp, horizontal = 20.dp),
        )

        Text(
            text =
                if (isAvailable) {
                    availableDescription
                } else if (isError) {
                    errorDescription
                } else {
                    ""
                },
            modifier = Modifier.padding(4.dp),
            color =
                if (isAvailable) {
                    Blue200
                } else if (isError) {
                    Red200
                } else {
                    Grey100
                },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TextFieldPreview() {
    OutlinedTextField(
        value = "냠냠",
        placeholder = "닉네임을 입력하세요. (50자 제한)",
        isError = true,
        isAvailable = false,
        onValueChange = { },
        availableDescription = "* 사용 가능한 닉네임 입니다.",
        errorDescription = "* 중복된 닉네임으로 사용할 수 없습니다.",
        singleLine = true,
        innerTextFieldStyle = LocalTextStyle.current.copy(color = Grey900, fontSize = 16.sp),
        modifier =
            Modifier
                .wrapContentSize()
                .padding(vertical = 4.dp),
    )
}
