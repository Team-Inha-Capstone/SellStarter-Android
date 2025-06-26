package com.inha.sellstarter_android.domain.repository

import com.inha.sellstarter_android.data.model.request.inventory.InventoryCountRequestDto
import com.inha.sellstarter_android.data.model.request.inventory.InventoryCreateRequestDto
import com.inha.sellstarter_android.domain.model.Inventory
import com.inha.sellstarter_android.domain.model.InventoryListPage
import okhttp3.MultipartBody

interface InventoryRepository {
    suspend fun registerInventoryItem(
        inventoryCreateRequest: InventoryCreateRequestDto,
        image: MultipartBody.Part?
    ): Result<Inventory>

    suspend fun loadInventoryList(
        search: String?,
        status: Boolean,
        page: Int,
        size: Int
    ): Result<InventoryListPage>

    suspend fun loadInventoryDetail(
        barcodeId: String
    ): Result<Inventory>

    suspend fun updateInventoryCount(
        barcodeId: String,
        inventoryCountRequest: InventoryCountRequestDto
    ): Result<Inventory>

    suspend fun searchInventories(
        search: String,
        status: Boolean,
        page: Int,
        size: Int
    ): Result<InventoryListPage>
}