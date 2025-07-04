package com.inha.sellstarter.domain.usecase.inventory

import com.inha.sellstarter.domain.model.Inventory
import com.inha.sellstarter.domain.repository.InventoryRepository
import javax.inject.Inject

class LoadInventoryDetailUseCase
    @Inject
    constructor(
        private val inventoryRepository: InventoryRepository,
    ) {
        suspend operator fun invoke(barcodeId: String): Result<Inventory> {
            return inventoryRepository.loadInventoryDetail(barcodeId)
        }
    }
