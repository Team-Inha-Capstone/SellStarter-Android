package com.inha.sellstarter_android.domain.usecase.inventory

import com.inha.sellstarter_android.domain.model.InventoryListPage
import com.inha.sellstarter_android.domain.model.InventorySummary
import com.inha.sellstarter_android.domain.repository.InventoryRepository
import javax.inject.Inject

class InventoryListUseCase @Inject constructor(
    private val inventoryRepository: InventoryRepository
) {
    suspend fun invoke(
        search: String?,
        status: Boolean = true,
        page: Int,
        size: Int,
    ): Result<InventoryListPage> {
        return inventoryRepository.getInventoryList(
            search,
            status,
            page,
            size
        )
    }
}