package com.inha.sellstarter.data.datasource.remote

import com.inha.sellstarter.data.model.request.order.OrderInventoryPickingRequestDto
import com.inha.sellstarter.data.model.response.order.OrderDetailResponseDto
import com.inha.sellstarter.data.model.response.order.OrderListResponseDto
import com.inha.sellstarter.data.model.response.order.PickingAvailableResponseDto

interface OrderDataSource {
    suspend fun loadOrderConfirmList(
        page: Int,
        size: Int,
    ): OrderListResponseDto

    suspend fun loadOrderConfirmationDetail(orderId: String): OrderDetailResponseDto

    suspend fun checkPickingAvailable(orderId: String): PickingAvailableResponseDto

    suspend fun completeOrderPickings(orderId: String): Unit

    suspend fun completeSinglePicking(
        orderId: String,
        request: OrderInventoryPickingRequestDto,
    ): Unit

    suspend fun loadCompletedPickingList(
        page: Int,
        size: Int,
    ): OrderListResponseDto

    suspend fun shipOrder(orderId: String): Unit

    suspend fun cancelOrder(orderId: String): Unit
}
