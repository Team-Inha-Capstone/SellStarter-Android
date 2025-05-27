package com.inha.sellstarter_android.presentation.inventory.list.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inha.sellstarter_android.presentation.common.component.DefaultTextField
import com.inha.sellstarter_android.ui.theme.Grey100
import com.inha.sellstarter_android.ui.theme.Grey50
import com.inha.sellstarter_android.ui.theme.Grey900

@Composable
fun SearchBar(
    value: String = "검색해 보세요.",
    onValueChange: (String) -> Unit,
    onSearch: () -> Unit,
    modifier: Modifier = Modifier.height(45.dp)
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Grey50,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(horizontal = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = Grey900,
                modifier = Modifier.padding(start = 8.dp)
                    .clickable {
                    onSearch()
                }
            )

            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            ) {

                DefaultTextField(
                    value = value,
                    onValueChange = onValueChange,
                    innerTextFieldStyle = MaterialTheme.typography.bodyMedium.copy(color = Grey900),
                    singleLine = true,
                    borderColor = Color.Transparent,
                    modifier = Modifier
                        .wrapContentSize()
                        .onKeyEvent { event ->
                            if (event.nativeKeyEvent.keyCode == android.view.KeyEvent.KEYCODE_ENTER) {
                                onSearch()
                                true
                            } else false
                        }
                )

            }

            if (value.isNotEmpty()) {
                IconButton(onClick = { onValueChange("") }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Clear",
                        tint = Grey100
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSearchBar() {
    SearchBar(
        value = "재고를 검색해 보세요. ex) 사과",
        onValueChange = {},
        onSearch = {},
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 12.dp)
            .height(50.dp)
    )
}

