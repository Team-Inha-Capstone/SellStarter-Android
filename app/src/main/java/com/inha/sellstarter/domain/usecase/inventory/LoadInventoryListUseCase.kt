package com.inha.sellstarter.domain.usecase.inventory

import com.inha.sellstarter.domain.model.InventoryListPage
import com.inha.sellstarter.domain.repository.InventoryRepository
import javax.inject.Inject

class LoadInventoryListUseCase
    @Inject
    constructor(
        private val inventoryRepository: InventoryRepository,
    ) {
        suspend operator fun invoke(
            search: String?,
            status: Boolean = true,
            page: Int,
            size: Int,
        ): Result<InventoryListPage> {
            return inventoryRepository.loadInventoryList(
                search,
                status,
                page,
                size,
            )
        }
    }
