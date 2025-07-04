package com.inha.sellstarter.data.datasource.remote.impl

import com.inha.sellstarter.data.datasource.remote.OrderDataSource
import com.inha.sellstarter.data.model.request.order.OrderInventoryPickingRequestDto
import com.inha.sellstarter.data.model.response.order.OrderDetailResponseDto
import com.inha.sellstarter.data.model.response.order.OrderListResponseDto
import com.inha.sellstarter.data.model.response.order.PickingAvailableResponseDto
import com.inha.sellstarter.data.service.OrderService
import javax.inject.Inject

class OrderDataSourceImpl
    @Inject
    constructor(
        private val orderService: OrderService,
    ) : OrderDataSource {
        override suspend fun loadOrderConfirmList(
            page: Int,
            size: Int,
        ): OrderListResponseDto {
            return orderService.loadOrderConfirmList(
                page = page,
                size = size,
            ).data
        }

        override suspend fun loadOrderConfirmationDetail(orderId: String): OrderDetailResponseDto {
            return orderService.loadOrderConfirmationDetail(orderId = orderId).data
        }

        override suspend fun checkPickingAvailable(orderId: String): PickingAvailableResponseDto {
            return orderService.checkPickingAvailable(orderId = orderId).data
        }

        override suspend fun completeOrderPickings(orderId: String) {
            orderService.completeOrderPickings(orderId = orderId)
        }

        override suspend fun completeSinglePicking(
            orderId: String,
            request: OrderInventoryPickingRequestDto,
        ) {
            orderService.completeSinglePicking(orderId = orderId, request = request)
        }

        override suspend fun loadCompletedPickingList(
            page: Int,
            size: Int,
        ): OrderListResponseDto {
            return orderService.loadCompletedPickingList(
                page = page,
                size = size,
            ).data
        }

        override suspend fun shipOrder(orderId: String) {
            orderService.shipOrder(orderId = orderId)
        }

        override suspend fun cancelOrder(orderId: String) {
            orderService.cancelOrder(orderId = orderId)
        }
    }
