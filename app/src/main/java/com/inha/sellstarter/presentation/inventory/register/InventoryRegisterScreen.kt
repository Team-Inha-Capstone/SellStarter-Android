package com.inha.sellstarter.presentation.inventory.register

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inha.sellstarter.data.model.request.inventory.InventoryCreateRequestDto
import com.inha.sellstarter.presentation.common.component.OneButton
import com.inha.sellstarter.presentation.common.screen.TitleScreen
import com.inha.sellstarter.ui.theme.Grey0
import com.inha.sellstarter.ui.theme.Purple200
import com.inha.sellstarter.ui.theme.SellStarterAndroidTheme
import com.inha.sellstarter.util.barcode.BarcodeUtils

@Composable
fun InventoryRegisterScreen(
    name: String,
    onNameChange: (String) -> Unit,
    count: String,
    onCountChange: (String) -> Unit,
    location: String,
    onLocationChange: (String) -> Unit,
    expiration: String,
    onExpirationChange: (String) -> Unit,
    option: String,
    onOptionChange: (String) -> Unit,
    imageUri: Uri?,
    onImageClick: () -> Unit,
    onRegister: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Grey0)
    ) {
        TitleScreen(
            title = "재고 등록하기",
            description = "스토어 내 재고를 등록하세요.",
        )

        InventoryForm(
            name = name,
            onNameChange = onNameChange,
            count = count,
            onCountChange = onCountChange,
            location = location,
            onLocationChange = onLocationChange,
            option = option,
            onOptionChange = onOptionChange,
            expiration = expiration,
            onExpirationChange = onExpirationChange,
            imageUri = imageUri,
            onImageClick = onImageClick,
            modifier = Modifier.weight(1f),
        )

        OneButton(
            text = "재고등록",
            buttonBackgroundColor = Purple200,
            fontColor = Grey0,
            enabled = name.isNotBlank() && count.isNotBlank() && location.isNotBlank(),
            onClick = onRegister,
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
                .height(55.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewInventoryRegisterScreen() {
    SellStarterAndroidTheme {
        InventoryRegisterScreen(
            name = "사과",
            onNameChange = {},
            count = "10",
            onCountChange = {},
            location = "냉장고",
            onLocationChange = {},
            expiration = "2025-12-31",
            onExpirationChange = {},
            option = "유기농",
            onOptionChange = {},
            imageUri = null,
            onImageClick = {},
            onRegister = {},
            modifier = Modifier
        )
    }
}