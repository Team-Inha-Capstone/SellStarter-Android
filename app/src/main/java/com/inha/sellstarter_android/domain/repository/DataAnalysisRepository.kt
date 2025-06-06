package com.inha.sellstarter_android.domain.repository

import com.inha.sellstarter_android.data.model.request.inventory.InventoryFlowGraphRequestDto

interface DataAnalysisRepository {
    suspend fun getInventoryFlowGraph(
        inventoryFlowGraphRequestDto: InventoryFlowGraphRequestDto
    ): Result<String>

    suspend fun fetchAnalysisReport(): Result<String>
}