package com.inha.sellstarter.data.model.request.order

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OrderInventoryPickingRequestDto(
    @SerialName("barcode_id")
    val barcodeId: String,
)
