package com.inha.sellstarter_android.data.model.request.inventory

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InventoryCreateRequestDto(
    @SerialName("inventory_name")
    val inventoryName: String,

    @SerialName("inventory_count")
    val inventoryCount: Int,

    @SerialName("inventory_option")
    val inventoryOption: String,

    @SerialName("inventory_location")
    val inventoryLocation: String,

    @SerialName("expiration")
    val expiration: String,

    @SerialName("barcode_id")
    val barcodeId: String
)