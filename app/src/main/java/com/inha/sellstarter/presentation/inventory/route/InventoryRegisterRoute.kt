package com.inha.sellstarter.presentation.inventory.route

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.inha.sellstarter.data.model.request.inventory.InventoryCreateRequestDto
import com.inha.sellstarter.presentation.inventory.InventoryViewModel
import com.inha.sellstarter.presentation.inventory.register.InventoryRegisterScreen
import com.inha.sellstarter.util.barcode.BarcodeUtils

@Composable
fun InventoryRegisterRoute(
    onRegisterSuccess: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InventoryViewModel = hiltViewModel(),
) {
    var name by rememberSaveable { mutableStateOf("") }
    var count by rememberSaveable { mutableStateOf("") }
    var location by rememberSaveable { mutableStateOf("") }
    var expiration by rememberSaveable { mutableStateOf("") }
    var option by rememberSaveable { mutableStateOf("") }

    var imageUri by rememberSaveable<Uri?>(
        stateSaver = Saver(
            save = { it?.toString() },
            restore = { it.let(Uri::parse) }
        )
    ) {
        mutableStateOf(null)
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri -> imageUri = uri }
    )

    InventoryRegisterScreen(
        name = name,
        onNameChange = { name = it },
        count = count,
        onCountChange = { count = it },
        location = location,
        onLocationChange = { location = it },
        expiration = expiration,
        onExpirationChange = { expiration = it },
        option = option,
        onOptionChange = { option = it },
        imageUri = imageUri,
        onImageClick = { launcher.launch("image/*") },
        onRegister = {
            val dto = InventoryCreateRequestDto(
                inventoryName = name,
                inventoryCount = count.toIntOrNull() ?: 0,
                inventoryLocation = location,
                expiration = expiration,
                inventoryOption = option,
                barcodeId = BarcodeUtils.generateBarcodeId(),
            )
            viewModel.saveDraft(dto, imageUri)
            onRegisterSuccess()
        },
        modifier = modifier,
    )
}