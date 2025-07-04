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
    modifier: Modifier = Modifier,
    onRegister: (InventoryCreateRequestDto, Uri?) -> Unit,
) {
    var name by remember { mutableStateOf("") }
    var count by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var expiration by remember { mutableStateOf("") }
    var option by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val launcher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent(),
            onResult = { uri -> if (uri != null) imageUri = uri },
        )

    Column(
        modifier =
            modifier
                .fillMaxSize()
                .background(Grey0),
    ) {
        TitleScreen(
            title = "재고 등록하기",
            description = "스토어 내 재고를 등록하세요.",
        )

        InventoryForm(
            name = name,
            onNameChange = { name = it },
            count = count,
            onCountChange = { count = it },
            location = location,
            onLocationChange = { location = it },
            option = option,
            onOptionChange = { option = it },
            expiration = expiration,
            onExpirationChange = { expiration = it },
            imageUri = imageUri,
            onImageClick = { launcher.launch("image/*") },
            modifier = Modifier.weight(1f),
        )

        OneButton(
            text = "재고등록",
            buttonBackgroundColor = Purple200,
            fontColor = Grey0,
            enabled = name.isNotBlank() && count.isNotBlank() && location.isNotBlank(),
            onClick = {
                val dto =
                    InventoryCreateRequestDto(
                        inventoryName = name,
                        inventoryCount = count.toIntOrNull() ?: 0,
                        inventoryLocation = location,
                        expiration = expiration,
                        inventoryOption = option,
                        barcodeId = BarcodeUtils.generateBarcodeId(),
                    )
                onRegister(dto, imageUri)
            },
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
                    .height(55.dp)
                    .testTag("RegisterButton"),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewInventoryRegisterScreen() {
    SellStarterAndroidTheme {
        InventoryRegisterScreen(
            onRegister = { dto, uri ->
            },
        )
    }
}
