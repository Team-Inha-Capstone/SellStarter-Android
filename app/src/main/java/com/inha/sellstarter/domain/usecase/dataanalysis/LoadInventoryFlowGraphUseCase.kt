package com.inha.sellstarter.domain.usecase.dataanalysis

import com.inha.sellstarter.data.model.request.inventory.InventoryFlowGraphRequestDto
import com.inha.sellstarter.domain.repository.DataAnalysisRepository
import javax.inject.Inject

class LoadInventoryFlowGraphUseCase
    @Inject
    constructor(
        private val dataAnalysisRepository: DataAnalysisRepository,
    ) {
        suspend operator fun invoke(inventoryFlowGraphRequestDto: InventoryFlowGraphRequestDto): Result<String> {
            return dataAnalysisRepository.loadInventoryFlowGraph(inventoryFlowGraphRequestDto)
        }
    }
