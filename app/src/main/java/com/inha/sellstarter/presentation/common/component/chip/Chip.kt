package com.inha.sellstarter.presentation.common.component.chip

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.inha.sellstarter.ui.theme.Grey0
import com.inha.sellstarter.ui.theme.Grey900
import com.inha.sellstarter.ui.theme.Orange200
import com.inha.sellstarter.ui.theme.Purple200

@Composable
fun Chip(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    selectedColor: Color = Orange200,
    unselectedColor: Color = Grey0,
    text: String = "",
    fontSize: Int = 14,
    fontColor: Color = Grey900,
    fontStyle: TextStyle = MaterialTheme.typography.labelMedium,
    onClick: () -> Unit,
) {
    Surface(
        color = if (isSelected) selectedColor else unselectedColor,
        shape = RoundedCornerShape(100.dp),
        modifier =
            modifier
                .border(
                    width = 1.dp,
                    shape = RoundedCornerShape(100.dp),
                    color = selectedColor,
                )
                .clickable(onClick = onClick),
    ) {
        Box(
            modifier =
                Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .wrapContentSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = text,
                textAlign = TextAlign.Center,
                style = fontStyle,
                color = fontColor,
                fontSize = fontSize.sp,
                modifier = Modifier.align(Alignment.Center),
            )
        }
    }
}

@Composable
@Preview
fun OurCourageChipPreview() {
    Chip(
        selectedColor = Purple200,
        unselectedColor = Grey0,
        isSelected = true,
        text = "여자",
        modifier =
            Modifier
                .wrapContentSize()
                .padding(20.dp),
        onClick = { },
        fontSize = 14,
        fontColor = Grey900,
    )
}
