package com.inha.sellstarter.domain.usecase.order

import com.inha.sellstarter.data.model.request.order.OrderInventoryPickingRequestDto
import com.inha.sellstarter.domain.repository.OrderRepository
import javax.inject.Inject

class CheckPickingAvailableUseCase
    @Inject
    constructor(
        private val repository: OrderRepository,
    ) {
        suspend operator fun invoke(orderId: String): Result<Boolean> = repository.checkPickingAvailable(orderId)
    }

class CompleteOrderPickingsUseCase
    @Inject
    constructor(
        private val repository: OrderRepository,
    ) {
        suspend operator fun invoke(orderId: String): Result<Unit> = repository.completeOrderPickings(orderId)
    }

class CompleteSinglePickingUseCase
    @Inject
    constructor(
        private val repository: OrderRepository,
    ) {
        suspend operator fun invoke(
            orderId: String,
            request: OrderInventoryPickingRequestDto,
        ): Result<Unit> = repository.completeSinglePicking(orderId, request)
    }
