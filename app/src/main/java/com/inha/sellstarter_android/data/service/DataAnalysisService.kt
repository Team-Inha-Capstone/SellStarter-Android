package com.inha.sellstarter_android.data.service

import com.inha.sellstarter_android.data.model.request.inventory.InventoryFlowGraphRequestDto
import com.inha.sellstarter_android.data.model.response.inventory.InventoryFlowGraphResponseDto
import com.inha.sellstarter_android.data.util.Constants.CHART
import com.inha.sellstarter_android.data.util.Constants.INVENTORY
import com.inha.sellstarter_android.util.base.BaseResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface DataAnalysisService {
    @POST("$INVENTORY/$CHART/{userId}")
    suspend fun getInventoryFlowGraph(
        @Path("userId") userId: Int = 4,
        @Body inventoryFlowGraphRequest: InventoryFlowGraphRequestDto
    ): BaseResponseDto<InventoryFlowGraphResponseDto>
}