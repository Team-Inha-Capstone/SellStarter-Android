package com.inha.sellstarter.data.repository

import android.util.Log
import com.inha.sellstarter.data.datasource.remote.OrderDataSource
import com.inha.sellstarter.data.mapper.toDomain
import com.inha.sellstarter.data.model.request.order.OrderInventoryPickingRequestDto
import com.inha.sellstarter.domain.model.OrderDetailInfo
import com.inha.sellstarter.domain.model.OrderListPage
import com.inha.sellstarter.domain.repository.OrderRepository
import javax.inject.Inject

class OrderRepositoryImpl
    @Inject
    constructor(
        private val dataSource: OrderDataSource,
    ) : OrderRepository {
        override suspend fun loadOrderConfirmList(
            page: Int,
            size: Int,
        ): Result<OrderListPage> {
            Log.e(
                "hyeon",
                dataSource.loadOrderConfirmList(page = page, size = size).toDomain().toString(),
            )
            return runCatching {
                dataSource.loadOrderConfirmList(page = page, size = size).toDomain()
            }
        }

        override suspend fun loadOrderConfirmationDetail(orderId: String): Result<OrderDetailInfo> {
            return runCatching {
                dataSource.loadOrderConfirmationDetail(orderId)
                    .toDomain()
            }
        }

        override suspend fun checkPickingAvailable(orderId: String): Result<Boolean> {
            return runCatching {
                dataSource.checkPickingAvailable(orderId)
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
            request: OrderInventoryPickingRequestDto,
        ): Result<Unit> {
            return runCatching {
                dataSource.completeSinglePicking(orderId, request)
            }
        }

        override suspend fun loadCompletedPickingList(
            page: Int,
            size: Int,
        ): Result<OrderListPage> {
            return runCatching {
                dataSource.loadCompletedPickingList(page = page, size = size)
                    .toDomain()
            }
        }

        override suspend fun shipOrder(orderId: String): Result<Unit> {
            return runCatching {
                dataSource.shipOrder(orderId)
            }
        }

        override suspend fun cancelOrder(orderId: String): Result<Unit> {
            return runCatching {
                dataSource.cancelOrder(orderId)
            }
        }
    }
