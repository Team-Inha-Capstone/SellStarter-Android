package com.inha.sellstarter_android.data.model.response.order

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class OrderListResponseDto(
    @SerialName("contents")
    val contents: List<OrderItemDto>,
    @SerialName("page")
    val page: Int,
    @SerialName("size")
    val size: Int,
    @SerialName("total_elements")
    val totalElements: Int,
    @SerialName("total_pages")
    val totalPages: Int
)

@Serializable
data class OrderItemDto(
    @SerialName("order_id")
    val orderId: String,
    @SerialName("order_date")
    val orderDate: String,
    @SerialName("channel_id")
    val channelId: Int,
    @SerialName("channel_name")
    val channelName: String,
    @SerialName("inventory_item")
    val inventoryItem: String?
)

@Serializable
data class PickingAvailableResponseDto(
    @SerialName("check_result")
    val checkResult: Boolean
)

