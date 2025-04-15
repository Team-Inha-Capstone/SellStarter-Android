package com.inha.sellstarter_android.presentation.common.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.inha.sellstarter_android.ui.theme.Blue200
import com.inha.sellstarter_android.ui.theme.Grey100
import com.inha.sellstarter_android.ui.theme.Red200

@Composable
fun DefaultTextField(
    value: String,
    onValueChange: (String) -> Unit,
    innerTextFieldStyle: TextStyle,
    singleLine: Boolean,
    borderColor : Color,
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
    )
}