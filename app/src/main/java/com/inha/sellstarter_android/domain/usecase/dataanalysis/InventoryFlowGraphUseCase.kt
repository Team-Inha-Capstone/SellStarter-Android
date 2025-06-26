package com.inha.sellstarter_android.domain.usecase.dataanalysis

import com.inha.sellstarter_android.data.model.request.inventory.InventoryFlowGraphRequestDto
import com.inha.sellstarter_android.domain.repository.DataAnalysisRepository
import javax.inject.Inject

class InventoryFlowGraphUseCase @Inject constructor(
    private val dataAnalysisRepository: DataAnalysisRepository
) {
    suspend operator fun invoke(
        inventoryFlowGraphRequestDto: InventoryFlowGraphRequestDto
    ): Result<String> {
        return dataAnalysisRepository.loadInventoryFlowGraph(inventoryFlowGraphRequestDto)
    }
}