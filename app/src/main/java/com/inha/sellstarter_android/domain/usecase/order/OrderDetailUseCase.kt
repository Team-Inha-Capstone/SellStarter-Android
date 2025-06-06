package com.inha.sellstarter_android.domain.usecase.order

import com.inha.sellstarter_android.domain.model.OrderDetailInfo
import com.inha.sellstarter_android.domain.repository.OrderRepository
import javax.inject.Inject

class FetchOrderConfirmationDetailUseCase @Inject constructor(
    private val repository: OrderRepository
) {
    suspend operator fun invoke(orderId: String): Result<OrderDetailInfo> =
        repository.fetchOrderConfirmationDetail(orderId)
}

class ConfirmOrderShipmentUseCase @Inject constructor(
    private val repository: OrderRepository
) {
    suspend operator fun invoke(orderId: String): Result<Unit> =
        repository.confirmOrderShipment(orderId)
}

class CancelOrderUseCase @Inject constructor(
    private val repository: OrderRepository
) {
    suspend operator fun invoke(orderId: String): Result<Unit> =
        repository.cancelOrder(orderId)
}