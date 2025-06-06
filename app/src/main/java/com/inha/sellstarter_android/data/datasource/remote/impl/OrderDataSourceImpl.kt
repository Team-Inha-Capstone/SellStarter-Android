package com.inha.sellstarter_android.data.datasource.remote.impl

import com.inha.sellstarter_android.data.datasource.remote.OrderDataSource
import com.inha.sellstarter_android.data.model.request.order.OrderInventoryPickingRequestDto
import com.inha.sellstarter_android.data.model.response.order.OrderDetailResponseDto
import com.inha.sellstarter_android.data.model.response.order.OrderListResponseDto
import com.inha.sellstarter_android.data.model.response.order.PickingAvailableResponseDto
import com.inha.sellstarter_android.data.service.OrderService
import javax.inject.Inject

class OrderDataSourceImpl @Inject constructor(
    private val orderService: OrderService
) : OrderDataSource {

    override suspend fun fetchOrderConfirmList(
        page: Int,
        size: Int
    ): OrderListResponseDto {
        return orderService.fetchOrderConfirmList(
            page = page,
            size = size
        ).data
    }

    override suspend fun fetchOrderConfirmationDetail(orderId: String): OrderDetailResponseDto {
        return orderService.fetchOrderConfirmationDetail(orderId = orderId).data
    }

    override suspend fun isPickingAvailable(
        orderId: String
    ): PickingAvailableResponseDto {
        return orderService.isPickingAvailable(orderId = orderId).data
    }

    override suspend fun completeOrderPickings(orderId: String) {
        orderService.completeOrderPickings(orderId = orderId)
    }

    override suspend fun completeSinglePicking(
        orderId: String,
        request: OrderInventoryPickingRequestDto
    ) {
        orderService.completeSinglePicking(orderId = orderId, request = request)
    }

    override suspend fun fetchCompletedPickingList(
        page: Int,
        size: Int
    ): OrderListResponseDto {
        return orderService.fetchCompletedPickingList(
            page = page,
            size = size
        ).data
    }

    override suspend fun confirmOrderShipment(orderId: String) {
        orderService.confirmOrderShipment(orderId = orderId)
    }

    override suspend fun cancelOrder(orderId: String) {
        orderService.cancelOrder(orderId = orderId)
    }
}