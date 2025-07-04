package com.inha.sellstarter.domain.usecase.inventory

data class InventoryUseCases(
    val loadInventoryListUseCase: LoadInventoryListUseCase,
    val loadInventoryDetailUseCase: LoadInventoryDetailUseCase,
    val updateInventoryCountUseCase: UpdateInventoryCountUseCase,
    val searchInventoriesUseCase: SearchInventoriesUseCase,
    val registerInventoryUseCase: RegisterInventoryItemUseCase,
)
