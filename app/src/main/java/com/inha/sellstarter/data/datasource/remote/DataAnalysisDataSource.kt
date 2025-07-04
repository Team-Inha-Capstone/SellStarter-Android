package com.inha.sellstarter.data.datasource.remote

import com.inha.sellstarter.data.model.request.inventory.InventoryFlowGraphRequestDto
import com.inha.sellstarter.data.model.response.analysis.AnalysisReportResponseDto
import com.inha.sellstarter.data.model.response.inventory.InventoryFlowGraphResponseDto
import com.inha.sellstarter.util.base.BaseResponseDto

interface DataAnalysisDataSource {
    suspend fun loadInventoryFlowGraph(
        inventoryFlowGraphRequestDto: InventoryFlowGraphRequestDto,
    ): BaseResponseDto<InventoryFlowGraphResponseDto>

    suspend fun loadAnalysisReport(): BaseResponseDto<AnalysisReportResponseDto>
}
