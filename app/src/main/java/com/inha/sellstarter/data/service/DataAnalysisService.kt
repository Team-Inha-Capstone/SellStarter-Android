package com.inha.sellstarter.data.service

import com.inha.sellstarter.data.model.request.inventory.InventoryFlowGraphRequestDto
import com.inha.sellstarter.data.model.response.analysis.AnalysisReportResponseDto
import com.inha.sellstarter.data.model.response.inventory.InventoryFlowGraphResponseDto
import com.inha.sellstarter.data.util.Constants.ANALYSIS
import com.inha.sellstarter.data.util.Constants.CHART
import com.inha.sellstarter.data.util.Constants.INVENTORY
import com.inha.sellstarter.data.util.Constants.REPORT
import com.inha.sellstarter.util.base.BaseResponseDto
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface DataAnalysisService {
    @POST("$INVENTORY/$CHART/{userId}")
    suspend fun loadInventoryFlowGraph(
        @Path("userId") userId: Int = 4,
        @Body inventoryFlowGraphRequest: InventoryFlowGraphRequestDto,
    ): BaseResponseDto<InventoryFlowGraphResponseDto>

    @POST("$ANALYSIS/$REPORT/{userId}")
    suspend fun loadDataAnalysisReport(
        @Path("userId") userId: Int = 4,
    ): BaseResponseDto<AnalysisReportResponseDto>
}
