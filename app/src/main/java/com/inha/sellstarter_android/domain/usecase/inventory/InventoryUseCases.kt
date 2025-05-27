package com.inha.sellstarter_android.domain.usecase.inventory

data class InventoryUseCases (
    val inventoryListUseCase: InventoryListUseCase,
    val inventoryDetailUseCase: InventoryDetailUseCase,
    val inventoryEditCountUseCase: InventoryEditCountUseCase,
    val inventorySearchUseCase: InventorySearchUseCase,
    val inventoryRegisterUseCase: InventoryRegisterUseCase
)