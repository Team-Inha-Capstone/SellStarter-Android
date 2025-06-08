package com.inha.sellstarter_android.domain.repository

import com.inha.sellstarter_android.data.model.request.inventory.InventoryCountRequestDto
import com.inha.sellstarter_android.data.model.request.inventory.InventoryCreateRequestDto
import com.inha.sellstarter_android.domain.model.Inventory
import com.inha.sellstarter_android.domain.model.InventoryListPage
import com.inha.sellstarter_android.domain.model.InventorySummary
import okhttp3.MultipartBody

interface InventoryRepository {
    suspend fun getInventoryList(
        search: String?,
        status: Boolean,
        page: Int,
        size: Int
    ): Result<InventoryListPage>

    suspend fun getInventoryDetail(
        barcodeId: String
    ): Result<Inventory>

    suspend fun postInventoryCount(
        barcodeId: String,
        inventoryCountRequest: InventoryCountRequestDto
    ): Result<Inventory>

    suspend fun getInventorySearch(
        search: String,
        status: Boolean,
        page: Int,
        size: Int
    ): Result<InventoryListPage>

    suspend fun postInventoryCreate(
        inventoryCreateRequest: InventoryCreateRequestDto,
        image: MultipartBody.Part?
    ): Result<Inventory>
}