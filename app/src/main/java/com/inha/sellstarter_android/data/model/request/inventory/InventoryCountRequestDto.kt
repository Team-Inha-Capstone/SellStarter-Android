package com.inha.sellstarter_android.data.model.request.inventory

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InventoryCountRequestDto(
    @SerialName("current_count")
    val currentCount: Int,
    @SerialName("inventory_count")
    val inventoryCount: Int
)