package com.inha.sellstarter_android.domain.usecase.inventory

import com.inha.sellstarter_android.domain.model.InventoryItem
import com.inha.sellstarter_android.domain.repository.InventoryRepository
import javax.inject.Inject

class InventoryListUseCase @Inject constructor(
    private val inventoryRepository: InventoryRepository
) {
    suspend fun invoke(
        status: Boolean = true,
        page: Int,
        size: Int,
    ): Result<List<InventoryItem>> {
        return inventoryRepository.getInventoryList(
            status,
            page,
            size
        )
    }
}