package com.inha.sellstarter_android.data.repository

import android.util.Log
import com.inha.sellstarter_android.data.datasource.remote.OrderDataSource
import com.inha.sellstarter_android.data.mapper.toDomain
import com.inha.sellstarter_android.data.model.request.order.OrderInventoryPickingRequestDto
import com.inha.sellstarter_android.data.service.OrderService
import com.inha.sellstarter_android.domain.model.OrderDetailInfo
import com.inha.sellstarter_android.domain.model.OrderListPage
import com.inha.sellstarter_android.domain.repository.OrderRepository
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(
    private val dataSource: OrderDataSource
) : OrderRepository {

    override suspend fun loadOrderConfirmList(page: Int, size: Int): Result<OrderListPage> {
        Log.e(
            "hyeon",
            dataSource.fetchOrderConfirmList(page = page, size = size).toDomain().toString()
        )
        return runCatching {
            dataSource.fetchOrderConfirmList(page = page, size = size).toDomain()
        }
    }

    override suspend fun loadOrderConfirmationDetail(orderId: String): Result<OrderDetailInfo> {
        return runCatching {
            dataSource.fetchOrderConfirmationDetail(orderId)
                .toDomain()
        }
    }

    override suspend fun checkPickingAvailable(orderId: String): Result<Boolean> {
        return runCatching {
            dataSource.isPickingAvailable(orderId)
                .checkResult
        }
    }

    override suspend fun completeOrderPickings(orderId: String): Result<Unit> {
        return runCatching {
            dataSource.completeOrderPickings(orderId)
        }
    }

    override suspend fun completeSinglePicking(
        orderId: String,
        request: OrderInventoryPickingRequestDto
    ): Result<Unit> {
        return runCatching {
            dataSource.completeSinglePicking(orderId, request)
        }
    }

    override suspend fun loadCompletedPickingList(
        page: Int,
        size: Int
    ): Result<OrderListPage> {
        return runCatching {
            dataSource.fetchCompletedPickingList(page = page, size = size)
                .toDomain()
        }
    }

    override suspend fun shipOrder(orderId: String): Result<Unit> {
        return runCatching {
            dataSource.confirmOrderShipment(orderId)
        }
    }

    override suspend fun cancelOrder(orderId: String): Result<Unit> {
        return runCatching {
            dataSource.cancelOrder(orderId)
        }
    }
}