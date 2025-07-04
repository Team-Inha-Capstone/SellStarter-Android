package com.inha.sellstarter.domain.usecase.inventory

import com.inha.sellstarter.domain.model.InventoryListPage
import com.inha.sellstarter.domain.repository.InventoryRepository
import javax.inject.Inject

class SearchInventoriesUseCase
    @Inject
    constructor(
        private val inventoryRepository: InventoryRepository,
    ) {
        suspend operator fun invoke(
            search: String,
            status: Boolean,
            page: Int,
            size: Int,
        ): Result<InventoryListPage> {
            return inventoryRepository.searchInventories(
                search = search,
                status = status,
                page = page,
                size = size,
            )
        }
    }
