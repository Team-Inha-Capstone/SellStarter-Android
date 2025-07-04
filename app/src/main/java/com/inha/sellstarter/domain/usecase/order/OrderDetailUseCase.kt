package com.inha.sellstarter.domain.usecase.order

import com.inha.sellstarter.domain.model.OrderDetailInfo
import com.inha.sellstarter.domain.repository.OrderRepository
import javax.inject.Inject

class LoadOrderConfirmationDetailUseCase
    @Inject
    constructor(
        private val repository: OrderRepository,
    ) {
        suspend operator fun invoke(orderId: String): Result<OrderDetailInfo> = repository.loadOrderConfirmationDetail(orderId)
    }

class ShipOrderUseCase
    @Inject
    constructor(
        private val repository: OrderRepository,
    ) {
        suspend operator fun invoke(orderId: String): Result<Unit> = repository.shipOrder(orderId)
    }

class CancelOrderUseCase
    @Inject
    constructor(
        private val repository: OrderRepository,
    ) {
        suspend operator fun invoke(orderId: String): Result<Unit> = repository.cancelOrder(orderId)
    }
