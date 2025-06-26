package com.inha.sellstarter_android.data.repository

import com.inha.sellstarter_android.data.datasource.remote.InventoryDataSource
import com.inha.sellstarter_android.data.mapper.toDomain
import com.inha.sellstarter_android.data.model.request.inventory.InventoryCountRequestDto
import com.inha.sellstarter_android.data.model.request.inventory.InventoryCreateRequestDto
import com.inha.sellstarter_android.domain.model.Inventory
import com.inha.sellstarter_android.domain.model.InventoryListPage
import com.inha.sellstarter_android.domain.repository.InventoryRepository
import okhttp3.MultipartBody
import javax.inject.Inject

class InventoryRepositoryImpl @Inject constructor(
    private val inventoryDataSource: InventoryDataSource
) : InventoryRepository {
    override suspend fun loadInventoryList(
        search: String?,
        status: Boolean,
        page: Int,
        size: Int
    ): Result<InventoryListPage> {
        return runCatching {
            inventoryDataSource.loadInventoryList(
                search = search, status = status, page = page, size = size
            ).data.toDomain()
        }
    }

    override suspend fun loadInventoryDetail(barcodeId: String): Result<Inventory> {
        return runCatching {
            inventoryDataSource.loadInventoryDetail(barcodeId = barcodeId).data.toDomain()
        }
    }

    override suspend fun updateInventoryCount(
        barcodeId: String,
        inventoryCountRequest: InventoryCountRequestDto
    ): Result<Inventory> {
        return runCatching {
            inventoryDataSource.updateInventoryCount(
                barcodeId,
                inventoryCountRequest
            ).data.toDomain()
        }
    }

    override suspend fun searchInventories(
        search: String,
        status: Boolean,
        page: Int,
        size: Int
    ): Result<InventoryListPage> {
        return runCatching {
            inventoryDataSource.searchInventories(
                search = search, status = status, page = page, size = size
            ).data.toDomain()
        }
    }

    override suspend fun registerInventoryItem(
        inventoryCreateRequest: InventoryCreateRequestDto,
        image: MultipartBody.Part?
    ): Result<Inventory> {

        return runCatching {
            inventoryDataSource.registerInventoryItem(
                image = image,
                inventoryCreateRequestDto = inventoryCreateRequest
            ).data.toDomain()
        }
    }
}