package com.inha.sellstarter_android.data.datasource.remote.impl

import com.inha.sellstarter_android.data.datasource.remote.DataAnalysisDataSource
import com.inha.sellstarter_android.data.model.request.inventory.InventoryFlowGraphRequestDto
import com.inha.sellstarter_android.data.model.response.analysis.AnalysisReportResponseDto
import com.inha.sellstarter_android.data.model.response.inventory.InventoryFlowGraphResponseDto
import com.inha.sellstarter_android.data.service.DataAnalysisService
import com.inha.sellstarter_android.util.base.BaseResponseDto
import javax.inject.Inject

class DataAnalysisDataSourceImpl @Inject constructor(
    private val dataAnalysisService: DataAnalysisService
) : DataAnalysisDataSource {
    override suspend fun getInventoryFlowGraph(inventoryFlowGraphRequestDto: InventoryFlowGraphRequestDto): BaseResponseDto<InventoryFlowGraphResponseDto> {
        return dataAnalysisService.loadInventoryFlowGraph(
            inventoryFlowGraphRequest = inventoryFlowGraphRequestDto
        )
    }

    override suspend fun fetchAnalysisReport(): BaseResponseDto<AnalysisReportResponseDto> {
        return dataAnalysisService.loadDataAnalysisReport()
    }
}