package com.inha.sellstarter_android.data.mapper

import com.inha.sellstarter_android.data.model.response.inventory.InventoryDetailResponseDto
import com.inha.sellstarter_android.data.model.response.inventory.InventoryItemDto
import com.inha.sellstarter_android.data.model.response.inventory.InventoryListResponseDto
import com.inha.sellstarter_android.domain.model.Inventory
import com.inha.sellstarter_android.domain.model.InventoryListPage
import com.inha.sellstarter_android.domain.model.InventorySummary

fun InventoryListResponseDto.toDomain(): InventoryListPage {
    return InventoryListPage(
        inventories = this.content.map { it.toDomain() },
        page = this.page,
        size = this.size,
        totalElements = this.totalElements,
        totalPages = this.totalPages
    )
}

fun InventoryItemDto.toDomain(): InventorySummary {
    return InventorySummary(
        id = this.barcodeId,
        name = this.inventoryName,
        quantity = this.inventoryCount,
        imageUrl = this.imageUrl ?: "",
        option = this.option ?: "옵션없음",
        isSoldOut = this.inventoryCount == 0
    )
}

fun InventoryDetailResponseDto.toDomain(): Inventory {
    return Inventory(
        id = this.barcodeId,
        name = this.inventoryName,
        quantity = this.inventoryCount,
        imageUrl = this.imageUrl,
        option = this.inventoryOption ?: "옵션 없음",
        expiration = this.expiration ?: "유통기한 없음",
        location = this.location,
        isSoldOut = this.inventoryCount == 0
    )
}