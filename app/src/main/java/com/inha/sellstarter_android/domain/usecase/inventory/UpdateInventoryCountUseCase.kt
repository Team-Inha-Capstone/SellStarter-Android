package com.inha.sellstarter_android.domain.usecase.inventory

import com.inha.sellstarter_android.data.model.request.inventory.InventoryCountRequestDto
import com.inha.sellstarter_android.domain.model.Inventory
import com.inha.sellstarter_android.domain.repository.InventoryRepository
import javax.inject.Inject

class UpdateInventoryCountUseCase @Inject constructor(
    private val inventoryRepository: InventoryRepository
) {
    suspend operator fun invoke(
        barcodeId : String,
        inventoryCountRequest: InventoryCountRequestDto
    ): Result<Inventory> {
        return inventoryRepository.updateInventoryCount(
            barcodeId,
            inventoryCountRequest
        )
    }
}