package com.inha.sellstarter_android.presentation.common.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.inha.sellstarter_android.ui.theme.Grey900
import com.inha.sellstarter_android.ui.theme.Purple200
import com.inha.sellstarter_android.ui.theme.AppTypography

@Composable
fun TitleAndPurplelinedTextField(
    titleText: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        text = titleText,
        style = MaterialTheme.typography.headlineMedium,
        modifier = Modifier.padding(vertical = 8.dp,)
    )

    DefaultTextField(
        value = value,
        onValueChange = onValueChange,
        innerTextFieldStyle = MaterialTheme.typography.bodyMedium.copy(color = Grey900),
        singleLine = true,
        borderColor = Purple200,
        placeholder = "값을 입력해 주세요.",
        modifier = modifier
            .padding(vertical = 4.dp)
            .height(55.dp)
            .fillMaxWidth()
    )
}