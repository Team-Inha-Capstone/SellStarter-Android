package com.inha.sellstarter.domain.repository

import com.inha.sellstarter.data.model.request.inventory.InventoryFlowGraphRequestDto

interface DataAnalysisRepository {
    suspend fun loadInventoryFlowGraph(inventoryFlowGraphRequestDto: InventoryFlowGraphRequestDto): Result<String>

    suspend fun loadAnalysisReport(): Result<String>
}
