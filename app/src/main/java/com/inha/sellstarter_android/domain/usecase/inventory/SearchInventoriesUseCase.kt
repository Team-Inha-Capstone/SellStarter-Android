package com.inha.sellstarter_android.domain.usecase.inventory

import com.inha.sellstarter_android.domain.model.InventoryListPage
import com.inha.sellstarter_android.domain.repository.InventoryRepository
import javax.inject.Inject

class SearchInventoriesUseCase @Inject constructor(
    private val inventoryRepository: InventoryRepository
) {
    suspend operator fun invoke(
        search: String,
        status: Boolean,
        page: Int,
        size: Int
    ): Result<InventoryListPage> {
        return inventoryRepository.searchInventories(
            search = search,
            status = status,
            page = page,
            size = size
        )
    }
}