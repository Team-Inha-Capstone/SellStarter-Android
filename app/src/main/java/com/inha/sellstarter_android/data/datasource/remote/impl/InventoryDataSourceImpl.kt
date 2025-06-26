package com.inha.sellstarter_android.data.datasource.remote.impl

import com.inha.sellstarter_android.data.datasource.remote.InventoryDataSource
import com.inha.sellstarter_android.data.model.request.inventory.InventoryCountRequestDto
import com.inha.sellstarter_android.data.model.request.inventory.InventoryCreateRequestDto
import com.inha.sellstarter_android.data.model.response.inventory.InventoryDetailResponseDto
import com.inha.sellstarter_android.data.model.response.inventory.InventoryListResponseDto
import com.inha.sellstarter_android.data.service.InventoryService
import com.inha.sellstarter_android.util.base.BaseResponseDto
import okhttp3.MultipartBody
import javax.inject.Inject

class InventoryDataSourceImpl @Inject constructor(
    private val inventoryService: InventoryService
) : InventoryDataSource {
    override suspend fun loadInventoryList(
        search: String?,
        status: Boolean,
        page: Int,
        size: Int
    ): BaseResponseDto<InventoryListResponseDto> {
        return inventoryService.loadInventoryList(
            search = search,
            status = status, page = page, size = size
        )
    }

    override suspend fun loadInventoryDetail(barcodeId: String): BaseResponseDto<InventoryDetailResponseDto> {
        return inventoryService.loadInventoryDetail(
            barcodeId = barcodeId
        )
    }

    override suspend fun updateInventoryCount(
        barcodeId: String,
        inventoryCountRequestDto: InventoryCountRequestDto
    ): BaseResponseDto<InventoryDetailResponseDto> {
        return inventoryService.updateInventoryCount(
            barcodeId = barcodeId,
            inventoryCountRequest = inventoryCountRequestDto
        )
    }

    override suspend fun searchInventories(
        search: String,
        status: Boolean,
        page: Int,
        size: Int
    ): BaseResponseDto<InventoryListResponseDto> {
        return inventoryService.searchInventories(
            search = search,
            status = status,
            page = page,
            size = size
        )
    }

    override suspend fun registerInventoryItem(
        image: MultipartBody.Part?,
        inventoryCreateRequestDto: InventoryCreateRequestDto
    ): BaseResponseDto<InventoryDetailResponseDto> {
        return inventoryService.registerInventoryItem(
            image = image,
            inventoryCreateRequest = inventoryCreateRequestDto,
        )
    }
}