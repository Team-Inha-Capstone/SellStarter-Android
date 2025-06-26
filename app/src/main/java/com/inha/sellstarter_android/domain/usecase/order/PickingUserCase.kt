package com.inha.sellstarter_android.domain.usecase.order

import com.inha.sellstarter_android.data.model.request.order.OrderInventoryPickingRequestDto
import com.inha.sellstarter_android.domain.repository.OrderRepository
import javax.inject.Inject

class IsPickingAvailableUseCase @Inject constructor(
    private val repository: OrderRepository
) {
    suspend operator fun invoke(orderId: String): Result<Boolean> =
        repository.checkPickingAvailable(orderId)
}

class CompleteOrderPickingsUseCase @Inject constructor(
    private val repository: OrderRepository
) {
    suspend operator fun invoke(orderId: String): Result<Unit> =
        repository.completeOrderPickings(orderId)
}

class CompleteSinglePickingUseCase @Inject constructor(
    private val repository: OrderRepository
) {
    suspend operator fun invoke(
        orderId: String,
        request: OrderInventoryPickingRequestDto
    ): Result<Unit> = repository.completeSinglePicking(orderId, request)
}