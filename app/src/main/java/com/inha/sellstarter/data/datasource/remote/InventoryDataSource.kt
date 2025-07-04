package com.inha.sellstarter.data.datasource.remote

import com.inha.sellstarter.data.model.request.inventory.InventoryCountRequestDto
import com.inha.sellstarter.data.model.request.inventory.InventoryCreateRequestDto
import com.inha.sellstarter.data.model.response.inventory.InventoryDetailResponseDto
import com.inha.sellstarter.data.model.response.inventory.InventoryListResponseDto
import com.inha.sellstarter.util.base.BaseResponseDto
import okhttp3.MultipartBody

interface InventoryDataSource {
    suspend fun registerInventoryItem(
        image: MultipartBody.Part?,
        inventoryCreateRequestDto: InventoryCreateRequestDto,
    ): BaseResponseDto<InventoryDetailResponseDto>

    suspend fun loadInventoryList(
        search: String?,
        status: Boolean,
        page: Int,
        size: Int,
    ): BaseResponseDto<InventoryListResponseDto>

    suspend fun loadInventoryDetail(barcodeId: String): BaseResponseDto<InventoryDetailResponseDto>

    suspend fun updateInventoryCount(
        barcodeId: String,
        inventoryCountRequestDto: InventoryCountRequestDto,
    ): BaseResponseDto<InventoryDetailResponseDto>

    suspend fun searchInventories(
        search: String,
        status: Boolean,
        page: Int,
        size: Int,
    ): BaseResponseDto<InventoryListResponseDto>
}
