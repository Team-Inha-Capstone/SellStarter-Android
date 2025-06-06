package com.inha.sellstarter_android.domain.repository

import com.inha.sellstarter_android.data.model.request.order.OrderInventoryPickingRequestDto
import com.inha.sellstarter_android.domain.model.OrderDetailInfo
import com.inha.sellstarter_android.domain.model.OrderListPage
import com.inha.sellstarter_android.domain.model.OrderSummary

interface OrderRepository {
    suspend fun fetchOrderConfirmList(
        page: Int, size: Int
    ): Result<OrderListPage>

    suspend fun fetchOrderConfirmationDetail(orderId: String): Result<OrderDetailInfo>
    suspend fun isPickingAvailable(orderId: String): Result<Boolean>

    suspend fun completeOrderPickings(
        orderId: String
    ): Result<Unit>

    suspend fun completeSinglePicking(
        orderId: String,
        request: OrderInventoryPickingRequestDto
    ): Result<Unit>

    suspend fun fetchCompletedPickingList(
        page: Int, size: Int
    ): Result<OrderListPage>

    suspend fun confirmOrderShipment(orderId: String): Result<Unit>

    suspend fun cancelOrder(orderId: String): Result<Unit>
}