package com.inha.sellstarter_android.data.service

import com.inha.sellstarter_android.data.model.request.inventory.InventoryCountRequestDto
import com.inha.sellstarter_android.data.model.request.inventory.InventoryCreateRequestDto
import com.inha.sellstarter_android.data.model.response.inventory.InventoryDetailResponseDto
import com.inha.sellstarter_android.data.model.response.inventory.InventoryListResponseDto
import com.inha.sellstarter_android.data.util.Constants.API
import com.inha.sellstarter_android.data.util.Constants.CORE
import com.inha.sellstarter_android.data.util.Constants.COUNT
import com.inha.sellstarter_android.data.util.Constants.INVENTORY
import com.inha.sellstarter_android.data.util.Constants.LIST
import com.inha.sellstarter_android.util.base.BaseResponseDto
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface InventoryService {

    @Multipart
    @POST("$API/$CORE/$INVENTORY/{userId}")
    suspend fun postInventoryCreate(
        @Path("userId") userId: Int = 4,
        @Part("data") inventoryCreateRequest: InventoryCreateRequestDto,
        @Part image: MultipartBody.Part?,
    ): BaseResponseDto<InventoryDetailResponseDto>


    @GET("$API/$CORE/$INVENTORY/{userId}/$LIST")
    suspend fun getInventoryList(
        @Path("userId") userId: Int = 4,
        @Query("searchStr") search: String?,
        @Query("status") status: Boolean,
        @Query("page") page: Int,
        @Query("size") size: Int,
    ): BaseResponseDto<InventoryListResponseDto>

    @GET("$API/$CORE/$INVENTORY/{userId}/{barcodeId}")
    suspend fun getInventoryDetail(
        @Path("userId") userId: Int = 4,
        @Path("barcodeId") barcodeId: String,
    ): BaseResponseDto<InventoryDetailResponseDto>

    @POST("$API/$CORE/$INVENTORY/{userId}/{barcodeId}/$COUNT")
    suspend fun postInventoryCount(
        @Path("userId") userId: Int = 4,
        @Path("barcodeId") barcodeId: String,
        @Body inventoryCountRequest: InventoryCountRequestDto
    ): BaseResponseDto<InventoryDetailResponseDto>

    @GET("$API/$CORE/$INVENTORY/{userId}/$LIST")
    suspend fun getInventorySearch(
        @Path("userId") userId: Int = 4,
        @Query("searchStr") search: String? = null,
        @Query("status") status: Boolean,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): BaseResponseDto<InventoryListResponseDto>
}