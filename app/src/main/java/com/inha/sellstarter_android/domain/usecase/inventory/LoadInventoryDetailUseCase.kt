package com.inha.sellstarter_android.domain.usecase.inventory

import com.inha.sellstarter_android.domain.model.Inventory
import com.inha.sellstarter_android.domain.repository.InventoryRepository
import javax.inject.Inject

class LoadInventoryDetailUseCase @Inject constructor(
    private val inventoryRepository: InventoryRepository
) {
    suspend operator fun invoke(
        barcodeId : String
    ) : Result<Inventory> {
        return inventoryRepository.loadInventoryDetail(barcodeId)
    }
}