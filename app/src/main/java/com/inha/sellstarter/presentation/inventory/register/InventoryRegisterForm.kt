package com.inha.sellstarter.presentation.inventory.register

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.inha.sellstarter.presentation.common.component.TitleAndPurpleLinedTextField
import com.inha.sellstarter.ui.theme.Grey50
import com.inha.sellstarter.ui.theme.Grey900

@Composable
fun InventoryForm(
    name: String,
    onNameChange: (String) -> Unit,
    count: String,
    onCountChange: (String) -> Unit,
    location: String,
    onLocationChange: (String) -> Unit,
    option: String,
    onOptionChange: (String) -> Unit,
    expiration: String,
    onExpirationChange: (String) -> Unit,
    imageUri: Uri?,
    onImageClick: () -> Unit,
    modifier: Modifier,
) {
    Column(
        modifier =
            modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp),
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Grey50)
                    .testTag("ImagePicker")
                    .clickable { onImageClick() },
            contentAlignment = Alignment.Center,
        ) {
            if (imageUri != null) {
                AsyncImage(
                    model = imageUri,
                    contentDescription = "selected image",
                    modifier = Modifier.fillMaxSize(),
                )
            } else {
                Text(text = "이미지를 선택하세요", color = Grey900)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        TitleAndPurpleLinedTextField(
            value = name,
            onValueChange = onNameChange,
            titleText = "상품명",
            modifier = Modifier.testTag("InventoryNameInput"),
        )
        TitleAndPurpleLinedTextField(
            value = count,
            onValueChange = onCountChange,
            titleText = "재고수량",
            keyboardType = KeyboardType.Number,
            filter = { input -> input.filter(Char::isDigit) },
            modifier = Modifier.testTag("InventoryCountInput"),
        )
        TitleAndPurpleLinedTextField(
            value = location,
            onValueChange = onLocationChange,
            titleText = "재고위치",
            modifier = Modifier.testTag("InventoryLocationInput"),
        )
        TitleAndPurpleLinedTextField(
            value = option,
            onValueChange = onOptionChange,
            titleText = "옵션",
            modifier = Modifier.testTag("InventoryOptionInput"),
        )
        TitleAndPurpleLinedTextField(
            value = expiration,
            onValueChange = onExpirationChange,
            titleText = "유통기한 (예: 2025-06-01)",
            modifier = Modifier.testTag("InventoryExpirationInput"),
        )
    }
}
