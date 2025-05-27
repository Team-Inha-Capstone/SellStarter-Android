package com.inha.sellstarter_android.data.datasource.remote

import com.inha.sellstarter_android.data.model.request.inventory.InventoryCountRequestDto
import com.inha.sellstarter_android.data.model.request.inventory.InventoryCreateRequestDto
import com.inha.sellstarter_android.data.model.response.inventory.InventoryDetailResponseDto
import com.inha.sellstarter_android.data.model.response.inventory.InventoryListResponseDto
import com.inha.sellstarter_android.util.base.BaseResponseDto
import okhttp3.MultipartBody

interface InventoryDataSource {
    suspend fun getInventoryList(
        status : Boolean,
        page : Int,
        size : Int
    ) : BaseResponseDto<InventoryListResponseDto>

    suspend fun getInventoryDetail(
        barcodeId : String,
    ) : BaseResponseDto<InventoryDetailResponseDto>

    suspend fun postInventoryCount(
        barcodeId: String,
        inventoryCountRequestDto: InventoryCountRequestDto
    ) : BaseResponseDto<InventoryDetailResponseDto>

    suspend fun getInventorySearch(
        search : String,
        status : Boolean,
        page : Int,
        size : Int
    ) : BaseResponseDto<InventoryListResponseDto>

    suspend fun postInventoryCreate(
        image : MultipartBody.Part?,
        inventoryCreateRequestDto: InventoryCreateRequestDto,
    ) : BaseResponseDto<InventoryDetailResponseDto>
}