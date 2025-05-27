package com.inha.sellstarter_android.domain.usecase.inventory

import com.inha.sellstarter_android.data.model.request.inventory.InventoryCreateRequestDto
import com.inha.sellstarter_android.domain.model.Inventory
import com.inha.sellstarter_android.domain.repository.InventoryRepository
import okhttp3.MultipartBody
import javax.inject.Inject

class InventoryRegisterUseCase @Inject constructor(
    private val inventoryRepository: InventoryRepository
) {
    suspend fun invoke(
        image: MultipartBody.Part?,
        inventoryCreateRequest: InventoryCreateRequestDto
    ): Result<Inventory> {
        return inventoryRepository.postInventoryCreate(
            inventoryCreateRequest = inventoryCreateRequest,
            image = image
        )
    }
}