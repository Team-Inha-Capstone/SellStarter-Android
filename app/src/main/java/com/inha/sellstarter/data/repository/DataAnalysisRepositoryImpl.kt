package com.inha.sellstarter.data.repository

import com.inha.sellstarter.data.datasource.remote.DataAnalysisDataSource
import com.inha.sellstarter.data.model.request.inventory.InventoryFlowGraphRequestDto
import com.inha.sellstarter.domain.repository.DataAnalysisRepository
import javax.inject.Inject

class DataAnalysisRepositoryImpl
    @Inject
    constructor(
        private val dataAnalysisDataSource: DataAnalysisDataSource,
    ) : DataAnalysisRepository {
        override suspend fun loadInventoryFlowGraph(inventoryFlowGraphRequestDto: InventoryFlowGraphRequestDto): Result<String> {
            return runCatching {
                dataAnalysisDataSource.loadInventoryFlowGraph(inventoryFlowGraphRequestDto).data.url
                    ?: ""
            }
        }

        override suspend fun loadAnalysisReport(): Result<String> {
            return runCatching {
                dataAnalysisDataSource.loadAnalysisReport().data.url
            }
        }
    }
