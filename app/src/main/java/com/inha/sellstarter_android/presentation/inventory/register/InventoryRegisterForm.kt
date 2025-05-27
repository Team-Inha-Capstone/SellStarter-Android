package com.inha.sellstarter_android.presentation.inventory.register

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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.inha.sellstarter_android.presentation.common.component.TitleAndPurplelinedTextField
import com.inha.sellstarter_android.presentation.inventory.register.component.InventoryExpirationDateContent
import com.inha.sellstarter_android.ui.theme.Grey50
import com.inha.sellstarter_android.ui.theme.Grey900

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
    modifier : Modifier,
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Grey50)
                .clickable { onImageClick() },
            contentAlignment = Alignment.Center
        ) {
            if (imageUri != null) {
                AsyncImage(
                    model = imageUri,
                    contentDescription = "selected image",
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                Text(text = "이미지를 선택하세요", color = Grey900)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        TitleAndPurplelinedTextField(value = name, onValueChange = onNameChange, titleText = "상품명")
        TitleAndPurplelinedTextField(value = count, onValueChange = onCountChange, titleText = "재고수량")
        TitleAndPurplelinedTextField(value = location, onValueChange = onLocationChange, titleText = "재고위치")
        TitleAndPurplelinedTextField(value = option, onValueChange = onOptionChange, titleText = "옵션")
        TitleAndPurplelinedTextField(value = expiration, onValueChange = onExpirationChange, titleText = "유통기한 (예: 2025-06-01)")
    }
}
