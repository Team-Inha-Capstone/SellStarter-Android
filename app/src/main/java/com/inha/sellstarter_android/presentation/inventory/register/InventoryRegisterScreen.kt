package com.inha.sellstarter_android.presentation.inventory.register

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.inha.sellstarter_android.data.model.request.inventory.InventoryCreateRequestDto
import com.inha.sellstarter_android.presentation.common.component.OneButton
import com.inha.sellstarter_android.presentation.common.screen.TitleScreen
import com.inha.sellstarter_android.presentation.inventory.InventoryViewModel
import com.inha.sellstarter_android.ui.theme.Grey0
import com.inha.sellstarter_android.ui.theme.Purple200
import com.inha.sellstarter_android.util.barcode.BarcodeUtils
@Composable
fun InventoryRegisterScreen(
    modifier: Modifier = Modifier,
    onRegister: (InventoryCreateRequestDto, Uri?) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var count by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var expiration by remember { mutableStateOf("") }
    var option by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri -> if (uri != null) imageUri = uri }
    )

    Column(modifier = modifier.fillMaxSize()) {

        TitleScreen(title = "재고 등록하기")

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
            modifier = Modifier.weight(1f)
        )

        OneButton(
            text = "재고등록",
            buttonBackgroundColor = Purple200,
            fontColor = Grey0,
            enabled = name.isNotBlank() && count.isNotBlank() && location.isNotBlank(),
            onClick = {
                val dto = InventoryCreateRequestDto(
                    inventoryName = name,
                    inventoryCount = count.toIntOrNull() ?: 0,
                    inventoryLocation = location,
                    expiration = expiration,
                    inventoryOption = option,
                    barcodeId = BarcodeUtils.generateBarcodeId()
                )
                onRegister(dto, imageUri)
            },
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


}