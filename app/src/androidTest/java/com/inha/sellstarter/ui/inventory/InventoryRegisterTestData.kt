package com.inha.sellstarter.ui.inventory

import android.net.Uri
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import com.inha.sellstarter.data.model.request.inventory.InventoryCreateRequestDto
import com.inha.sellstarter.presentation.inventory.register.InventoryForm
import com.inha.sellstarter.presentation.inventory.register.InventoryRegisterScreen

object InventoryRegisterTestData {
    const val TAG_IMAGE_PICKER = "ImagePicker"
    const val TAG_INVENTORY_NAME_INPUT = "InventoryNameInput"
    const val TAG_INVENTORY_COUNT_INPUT = "InventoryCountInput"
    const val TAG_INVENTORY_LOCATION_INPUT = "InventoryLocationInput"
    const val TAG_INVENTORY_OPTION_INPUT = "InventoryOptionInput"
    const val TAG_INVENTORY_EXPIRATION_INPUT = "InventoryExpirationInput"
    const val TAG_REGISTER_BUTTON = "RegisterButton"

    fun setInventoryRegisterScreen(
        rule: ComposeContentTestRule,
        name: String = "",
        count: String = "",
        location: String = "",
        option: String = "",
        expiration: String = "",
        imageUri: Uri? = null,
        onImageClick: () -> Unit = {},
        onRegister: (InventoryCreateRequestDto, Uri?) -> Unit = { _, _ -> },
    ) {
        rule.setContent {
            InventoryRegisterScreen(
                modifier = Modifier,
                onRegister = onRegister,
            )
        }
    }

    /**
     * InventoryForm 만 단독으로 세팅하는 헬퍼
     */
    fun setInventoryForm(
        rule: ComposeContentTestRule,
        name: String = "",
        count: String = "",
        location: String = "",
        option: String = "",
        expiration: String = "",
        imageUri: Uri? = null,
        onImageClick: () -> Unit = {},
        onNameChange: (String) -> Unit = {},
        onCountChange: (String) -> Unit = {},
        onLocationChange: (String) -> Unit = {},
        onOptionChange: (String) -> Unit = {},
        onExpirationChange: (String) -> Unit = {},
    ) {
        rule.setContent {
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
                modifier = Modifier,
            )
        }
    }
}
