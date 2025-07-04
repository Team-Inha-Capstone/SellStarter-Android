package com.inha.sellstarter.data.model.response.inventory

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InventoryDetailResponseDto(
    @SerialName("inventory_name")
    val inventoryName: String,
    @SerialName("inventory_count")
    val inventoryCount: Int,
    @SerialName("inventory_option")
    val inventoryOption: String?,
    @SerialName("location")
    val location: String,
    @SerialName("barcode_id")
    val barcodeId: String,
    @SerialName("image_url")
    val imageUrl: String,
    @SerialName("expiration")
    val expiration: String?,
)

@Serializable
data class InventoryFlowGraphResponseDto(
    @SerialName("url")
    val url: String?,
)
