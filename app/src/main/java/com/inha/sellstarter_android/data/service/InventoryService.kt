package com.inha.sellstarter_android.data.service
import com.inha.sellstarter_android.data.model.request.inventory.InventoryCountRequestDto
import com.inha.sellstarter_android.data.model.response.inventory.InventoryDetailResponseDto
import com.inha.sellstarter_android.data.model.response.inventory.InventoryListResponseDto
import com.inha.sellstarter_android.data.util.Constants.API
import com.inha.sellstarter_android.data.util.Constants.CORE
import com.inha.sellstarter_android.data.util.Constants.INVENTORY
import com.inha.sellstarter_android.data.util.Constants.USER
import com.inha.sellstarter_android.util.base.BaseResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface InventoryService {
    @GET("$API/$CORE/$INVENTORY/{userId}")
    suspend fun getInventoryList(
        @Path("userId") userId : Int = 4,
        @Query("status") status : Boolean,
        @Query("page") page : Int,
        @Query("size") size : Int,
    ): BaseResponseDto<InventoryListResponseDto>

    @GET("$API/$CORE/$INVENTORY/{userId}/{barcodeId}")
    suspend fun getInventoryDetail(
        @Path("userId") userId : Int = 4,
        @Path("barcodeId") barcodeId : Int,
    ): BaseResponseDto<InventoryDetailResponseDto>

    @POST("$API/$CORE/$INVENTORY/{userId}/{barcodeId}/count")
    suspend fun postInventoryCount(
        @Body inventoryCountRequest : InventoryCountRequestDto
    ) : BaseResponseDto<InventoryDetailResponseDto>

    @GET("$API/$CORE/$USER/{userId}")
    suspend fun getInventorySearch(
        @Query("search") search : String,
        @Query("status") status :Boolean,
        @Query("page") page : Int,
        @Query("size") size : Int
    ) : BaseResponseDto<InventoryListResponseDto>


}