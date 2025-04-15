package com.inha.sellstarter_android.presentation.common.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inha.sellstarter_android.presentation.onboarding.ShoppingMallType
import com.inha.sellstarter_android.ui.theme.Grey100
import com.inha.sellstarter_android.ui.theme.Grey900
import com.inha.sellstarter_android.ui.theme.Orange200
import com.inha.sellstarter_android.ui.theme.Typography

@Composable
fun <T : Enum<T>> RadioButtonGroup(
    options: Array<T>,
    selectedOption: T,
    onOptionSelected: (T) -> Unit,
    labelMapper: (T) -> String = { it.name },
    modifier: Modifier = Modifier,
    title: String? = null
) {
    Column(modifier = modifier.padding(8.dp)) {
        title?.let {
            Text(
                text = it,
                style = Typography.headlineMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        options.forEach { option ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onOptionSelected(option) }
                    .padding(vertical = 4.dp)
            ) {
                RadioButton(
                    selected = option == selectedOption,
                    onClick = { onOptionSelected(option) },
                    colors =  RadioButtonDefaults.colors(
                        selectedColor = Orange200,
                        unselectedColor = Grey100,
                        disabledSelectedColor = Grey100,
                        disabledUnselectedColor = Grey900
                    )
                )
                Text(
                    text = labelMapper(option),
                    style = Typography.bodyLarge,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RadioButtonGroupPreview() {
    MaterialTheme {
        var selected by remember { mutableStateOf(ShoppingMallType.GROCERY) }

       RadioButtonGroup(
            options = ShoppingMallType.values(),
            selectedOption = selected,
            onOptionSelected = { selected = it },
            labelMapper = { it.category},
            title = "쇼핑몰 카테고리 선택"
        )
    }
}

