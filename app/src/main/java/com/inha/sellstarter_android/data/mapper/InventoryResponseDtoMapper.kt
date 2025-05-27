package com.inha.sellstarter_android.data.mapper

import com.inha.sellstarter_android.data.model.response.inventory.InventoryDetailResponseDto
import com.inha.sellstarter_android.data.model.response.inventory.InventoryItemDto
import com.inha.sellstarter_android.data.model.response.inventory.InventoryListResponseDto
import com.inha.sellstarter_android.domain.model.Inventory
import com.inha.sellstarter_android.domain.model.InventoryItem

fun InventoryListResponseDto.toDomain(): List<InventoryItem> {
    return content.map { it.toDomain() }
}

fun InventoryItemDto.toDomain(): InventoryItem {
    return InventoryItem(
        id = this.barcodeId,
        name = this.inventoryName,
        quantity = this.inventoryCount,
        imageUrl = this.imageUrl ?: "",
        option = this.option ?: "옵션없음",
        isSoldOut = this.inventoryCount == 0
    )
}
fun InventoryDetailResponseDto.toDomain() : Inventory {
    return Inventory(
        id =  this.barcodeId,
        name = this.inventoryName,
        quantity = this.inventoryCount,
        imageUrl = this.imageUrl,
        option = this.inventoryOption ?: "옵션없음",
        expiration = this.expiration ?: "",
        location = this.location,
        isSoldOut = this.inventoryCount == 0
    )
}