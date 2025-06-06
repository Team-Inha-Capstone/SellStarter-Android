package com.inha.sellstarter_android.data.model.response.order

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OrderDetailResponseDto(
    @SerialName("order_id")
    val orderId: String,
    @SerialName("channel")
    val channel: String,
    @SerialName("order_status")
    val orderStatus: String,
    @SerialName("inventory_list")
    val inventoryList: List<OrderInventoryItemDto>,
    @SerialName("all_picked")
    val allPicked: Boolean,
    @SerialName("purchaser_name")
    val purchaserName: String,
    @SerialName("purchser_address")
    val purchaserAddress: String,
    @SerialName("purchaser_request")
    val purchaserRequest: String
)

@Serializable
data class OrderInventoryItemDto(
    @SerialName("inventory_name")
    val inventoryName: String,
    @SerialName("barcode_id")
    val barcodeId: String,
    @SerialName("inventory_count")
    val inventoryCount: Int,
    @SerialName("is_picked")
    val isPicked: Boolean
)
