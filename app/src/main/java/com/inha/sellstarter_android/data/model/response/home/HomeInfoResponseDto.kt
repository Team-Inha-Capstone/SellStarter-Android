package com.inha.sellstarter_android.data.model.response.home

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HomeInfoResponseDto(
    @SerialName("shop_name")
    val shopName : String,
    @SerialName("new_order")
    val newOrder: Int,
    @SerialName("picking_completed")
    val pickingCompleted: Int,
    @SerialName("shipping_completed")
    val shippingCompleted: Int,
    @SerialName("cancel_request")
    val cancelRequest: Int,
    @SerialName("return_request")
    val returnRequest: Int,
    @SerialName("analysis_subscribed")
    val analysisSubscribed: Boolean
)