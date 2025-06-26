package com.inha.sellstarter_android.domain.repository

import com.inha.sellstarter_android.data.model.request.inventory.InventoryFlowGraphRequestDto

interface DataAnalysisRepository {
    suspend fun loadInventoryFlowGraph(
        inventoryFlowGraphRequestDto: InventoryFlowGraphRequestDto
    ): Result<String>

    suspend fun loadAnalysisReport(): Result<String>
}