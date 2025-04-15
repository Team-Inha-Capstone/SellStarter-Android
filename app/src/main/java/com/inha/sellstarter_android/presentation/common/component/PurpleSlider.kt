package com.inha.sellstarter_android.presentation.common.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.inha.sellstarter_android.ui.theme.Grey100
import com.inha.sellstarter_android.ui.theme.Grey50
import com.inha.sellstarter_android.ui.theme.Grey900
import com.inha.sellstarter_android.ui.theme.Orange200
import com.inha.sellstarter_android.ui.theme.Purple200

@Composable
fun PurpleSlider(
    text: String,
    fontColor: Color,
    fontSize: Int,
    step: Int,
    currentValue: Float,
    valueRange: ClosedFloatingPointRange<Float>,
    onSliderChange: (Float) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = text,
            color = fontColor,
            fontSize = fontSize.sp,
            modifier = Modifier.padding(6.dp)
        )

        Slider(
            steps = step,
            value = currentValue,
            valueRange = valueRange,
            onValueChange = onSliderChange,
            colors = SliderColors(
                thumbColor = Orange200,
                activeTrackColor = Purple200,
                activeTickColor = Purple200,
                inactiveTickColor = Grey100,
                inactiveTrackColor = Grey50,
                disabledThumbColor = Color.DarkGray,
                disabledActiveTrackColor = Color.LightGray,
                disabledInactiveTrackColor = Color.Gray,
                disabledActiveTickColor = Color.Black,
                disabledInactiveTickColor = Grey100,
            ),
            modifier = Modifier,
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewSlider() {
        PurpleSlider (
            text = "글자크기",
            fontColor = Grey900,
            fontSize = 16,
            valueRange = 1f..3f,
            step = 1,
            currentValue = 2f,
            onSliderChange = { },
        )
}