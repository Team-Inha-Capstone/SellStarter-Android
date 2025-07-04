package com.inha.sellstarter.data.datasource.remote.impl

import com.inha.sellstarter.data.datasource.remote.DataAnalysisDataSource
import com.inha.sellstarter.data.model.request.inventory.InventoryFlowGraphRequestDto
import com.inha.sellstarter.data.model.response.analysis.AnalysisReportResponseDto
import com.inha.sellstarter.data.model.response.inventory.InventoryFlowGraphResponseDto
import com.inha.sellstarter.data.service.DataAnalysisService
import com.inha.sellstarter.util.base.BaseResponseDto
import javax.inject.Inject

class DataAnalysisDataSourceImpl
    @Inject
    constructor(
        private val dataAnalysisService: DataAnalysisService,
    ) : DataAnalysisDataSource {
        override suspend fun loadInventoryFlowGraph(
            inventoryFlowGraphRequestDto: InventoryFlowGraphRequestDto,
        ): BaseResponseDto<InventoryFlowGraphResponseDto> {
            return dataAnalysisService.loadInventoryFlowGraph(
                inventoryFlowGraphRequest = inventoryFlowGraphRequestDto,
            )
        }

        override suspend fun loadAnalysisReport(): BaseResponseDto<AnalysisReportResponseDto> {
            return dataAnalysisService.loadDataAnalysisReport()
        }
    }
