package com.inha.sellstarter_android.domain.usecase.inventory

import com.inha.sellstarter_android.domain.model.InventorySummary
import com.inha.sellstarter_android.domain.repository.InventoryRepository
import javax.inject.Inject

class InventorySearchUseCase @Inject constructor(
    private val inventoryRepository: InventoryRepository
) {
    suspend fun invoke(
        search : String,
        status : Boolean,
        page : Int,
        size : Int
    ) : Result<List<InventorySummary>> {
        return inventoryRepository.getInventorySearch(search = search, status = status,page = page,size = size)
    }
}