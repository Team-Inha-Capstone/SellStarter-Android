package com.inha.sellstarter.domain.repository

import com.inha.sellstarter.data.model.request.order.OrderInventoryPickingRequestDto
import com.inha.sellstarter.domain.model.OrderDetailInfo
import com.inha.sellstarter.domain.model.OrderListPage

interface OrderRepository {
    suspend fun loadOrderConfirmList(
        page: Int,
        size: Int,
    ): Result<OrderListPage>

    suspend fun loadOrderConfirmationDetail(orderId: String): Result<OrderDetailInfo>

    suspend fun checkPickingAvailable(orderId: String): Result<Boolean>

    suspend fun completeOrderPickings(orderId: String): Result<Unit>

    suspend fun completeSinglePicking(
        orderId: String,
        request: OrderInventoryPickingRequestDto,
    ): Result<Unit>

    suspend fun loadCompletedPickingList(
        page: Int,
        size: Int,
    ): Result<OrderListPage>

    suspend fun shipOrder(orderId: String): Result<Unit>

    suspend fun cancelOrder(orderId: String): Result<Unit>
}
