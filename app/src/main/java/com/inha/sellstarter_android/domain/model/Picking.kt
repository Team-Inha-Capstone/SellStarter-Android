package com.inha.sellstarter_android.domain.model

data class PickingInfo(
    val items: List<OrderPickingInventory>,
    val allPicked: Boolean
)

data class OrderPickingInventory(
    val inventoryName: String,
    val barcodeId: String,
    val inventoryCount: Int,
    val isPicked: Boolean
)