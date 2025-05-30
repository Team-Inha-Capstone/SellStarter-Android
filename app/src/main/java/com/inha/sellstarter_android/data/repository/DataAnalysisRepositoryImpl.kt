package com.inha.sellstarter_android.data.repository

import com.inha.sellstarter_android.data.datasource.remote.DataAnalysisDataSource
import com.inha.sellstarter_android.data.model.request.inventory.InventoryFlowGraphRequestDto
import com.inha.sellstarter_android.domain.repository.DataAnalysisRepository
import javax.inject.Inject

class DataAnalysisRepositoryImpl @Inject constructor(
    private val dataAnalysisDataSource: DataAnalysisDataSource
) : DataAnalysisRepository {
    override suspend fun getInventoryFlowGraph(inventoryFlowGraphRequestDto: InventoryFlowGraphRequestDto): Result<String> {
        return runCatching {
            dataAnalysisDataSource.getInventoryFlowGraph(inventoryFlowGraphRequestDto).data.url
        }
    }
}