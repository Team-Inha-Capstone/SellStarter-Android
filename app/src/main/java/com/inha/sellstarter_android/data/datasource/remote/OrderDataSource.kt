package com.inha.sellstarter_android.data.datasource.remote

import com.inha.sellstarter_android.data.model.request.order.OrderInventoryPickingRequestDto
import com.inha.sellstarter_android.data.model.response.order.OrderDetailResponseDto
import com.inha.sellstarter_android.data.model.response.order.OrderListResponseDto
import com.inha.sellstarter_android.data.model.response.order.PickingAvailableResponseDto

interface OrderDataSource {
    suspend fun fetchOrderConfirmList(page: Int, size: Int): OrderListResponseDto
    suspend fun fetchOrderConfirmationDetail(orderId: String): OrderDetailResponseDto
    suspend fun isPickingAvailable(orderId: String): PickingAvailableResponseDto
    suspend fun completeOrderPickings(orderId: String): Unit
    suspend fun completeSinglePicking(
        orderId: String,
        request: OrderInventoryPickingRequestDto
    ): Unit

    suspend fun fetchCompletedPickingList(page: Int, size: Int): OrderListResponseDto
    suspend fun confirmOrderShipment(orderId: String): Unit
    suspend fun cancelOrder(orderId: String): Unit
}