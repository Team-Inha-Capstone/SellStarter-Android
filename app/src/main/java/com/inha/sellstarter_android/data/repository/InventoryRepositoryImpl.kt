package com.inha.sellstarter_android.data.repository

import com.inha.sellstarter_android.data.datasource.remote.InventoryDataSource
import com.inha.sellstarter_android.data.mapper.toDomain
import com.inha.sellstarter_android.data.model.request.inventory.InventoryCountRequestDto
import com.inha.sellstarter_android.data.model.request.inventory.InventoryCreateRequestDto
import com.inha.sellstarter_android.domain.model.Inventory
import com.inha.sellstarter_android.domain.model.InventorySummary
import com.inha.sellstarter_android.domain.repository.InventoryRepository
import okhttp3.MultipartBody
import javax.inject.Inject

class InventoryRepositoryImpl @Inject constructor(
    private val inventoryDataSource: InventoryDataSource
) : InventoryRepository {
    override suspend fun getInventoryList(
        status: Boolean,
        page: Int,
        size: Int
    ): Result<List<InventorySummary>> {
        return runCatching {
            inventoryDataSource.getInventoryList(
                status = status, page = page, size = size
            ).data.toDomain()
        }
    }

    override suspend fun getInventoryDetail(barcodeId: String): Result<Inventory> {
        return runCatching {
            inventoryDataSource.getInventoryDetail(barcodeId = barcodeId).data.toDomain()
        }
    }

    override suspend fun postInventoryCount(
        barcodeId: String,
        inventoryCountRequest: InventoryCountRequestDto
    ): Result<Inventory> {
        return runCatching {
            inventoryDataSource.postInventoryCount(
                barcodeId,
                inventoryCountRequest
            ).data.toDomain()
        }
    }

    override suspend fun getInventorySearch(
        search: String,
        status: Boolean,
        page: Int,
        size: Int
    ): Result<List<InventorySummary>> {
        return runCatching {
            inventoryDataSource.getInventorySearch(
                search = search, status = status, page = page, size = size
            ).data.toDomain()
        }
    }

    override suspend fun postInventoryCreate(
        inventoryCreateRequest: InventoryCreateRequestDto,
        image: MultipartBody.Part?
    ): Result<Inventory> {

        return runCatching {
            inventoryDataSource.postInventoryCreate(
                image = image,
                inventoryCreateRequestDto = inventoryCreateRequest
            ).data.toDomain()
        }
    }
}