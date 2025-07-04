package com.inha.sellstarter.data.model.response.inventory

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InventoryItemDto(
    @SerialName("barcode_id")
    val barcodeId: String,
    @SerialName("inventory_name")
    val inventoryName: String,
    @SerialName("inventory_count")
    val inventoryCount: Int,
    @SerialName("option")
    val option: String?,
    @SerialName("image_url")
    val imageUrl: String?,
)
