package com.inha.sellstarter_android.data.datasource.remote

import com.inha.sellstarter_android.data.model.request.inventory.InventoryFlowGraphRequestDto
import com.inha.sellstarter_android.data.model.response.analysis.AnalysisReportResponseDto
import com.inha.sellstarter_android.data.model.response.inventory.InventoryFlowGraphResponseDto
import com.inha.sellstarter_android.util.base.BaseResponseDto

interface DataAnalysisDataSource {
    suspend fun loadInventoryFlowGraph(
        inventoryFlowGraphRequestDto: InventoryFlowGraphRequestDto
    ): BaseResponseDto<InventoryFlowGraphResponseDto>

    suspend fun loadAnalysisReport(): BaseResponseDto<AnalysisReportResponseDto>
}