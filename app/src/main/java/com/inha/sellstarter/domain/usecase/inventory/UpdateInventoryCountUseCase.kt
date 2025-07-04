package com.inha.sellstarter.domain.usecase.inventory

import com.inha.sellstarter.data.model.request.inventory.InventoryCountRequestDto
import com.inha.sellstarter.domain.model.Inventory
import com.inha.sellstarter.domain.repository.InventoryRepository
import javax.inject.Inject

class UpdateInventoryCountUseCase
    @Inject
    constructor(
        private val inventoryRepository: InventoryRepository,
    ) {
        suspend operator fun invoke(
            barcodeId: String,
            inventoryCountRequest: InventoryCountRequestDto,
        ): Result<Inventory> {
            return inventoryRepository.updateInventoryCount(
                barcodeId,
                inventoryCountRequest,
            )
        }
    }
