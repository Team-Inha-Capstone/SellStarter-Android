package com.inha.sellstarter_android.data.datasource.remote.impl

import com.inha.sellstarter_android.data.datasource.remote.InventoryDataSource
import com.inha.sellstarter_android.data.model.request.inventory.InventoryCountRequestDto
import com.inha.sellstarter_android.data.model.request.inventory.InventoryCreateRequestDto
import com.inha.sellstarter_android.data.model.request.inventory.InventoryFlowGraphRequestDto
import com.inha.sellstarter_android.data.model.response.inventory.InventoryDetailResponseDto
import com.inha.sellstarter_android.data.model.response.inventory.InventoryFlowGraphResponseDto
import com.inha.sellstarter_android.data.model.response.inventory.InventoryListResponseDto
import com.inha.sellstarter_android.data.service.InventoryService
import com.inha.sellstarter_android.util.base.BaseResponseDto
import okhttp3.MultipartBody
import javax.inject.Inject

class InventoryDataSourceImpl @Inject constructor(
    private val inventoryService: InventoryService
) : InventoryDataSource {
    override suspend fun getInventoryList(
        search: String?,
        status: Boolean,
        page: Int,
        size: Int
    ): BaseResponseDto<InventoryListResponseDto> {
        return inventoryService.getInventoryList(
            search = search,
            status = status, page = page, size = size
        )
    }

    override suspend fun getInventoryDetail(barcodeId: String): BaseResponseDto<InventoryDetailResponseDto> {
        return inventoryService.getInventoryDetail(
            barcodeId = barcodeId
        )
    }

    override suspend fun postInventoryCount(
        barcodeId: String,
        inventoryCountRequestDto: InventoryCountRequestDto
    ): BaseResponseDto<InventoryDetailResponseDto> {
        return inventoryService.postInventoryCount(
            barcodeId = barcodeId,
            inventoryCountRequest = inventoryCountRequestDto
        )
    }

    override suspend fun getInventorySearch(
        search: String,
        status: Boolean,
        page: Int,
        size: Int
    ): BaseResponseDto<InventoryListResponseDto> {
        return inventoryService.getInventorySearch(
            search = search,
            status = status,
            page = page,
            size = size
        )
    }

    override suspend fun postInventoryCreate(
        image: MultipartBody.Part?,
        inventoryCreateRequestDto: InventoryCreateRequestDto
    ): BaseResponseDto<InventoryDetailResponseDto> {
        return inventoryService.postInventoryCreate(
            image = image,
            inventoryCreateRequest = inventoryCreateRequestDto,
        )
    }
}