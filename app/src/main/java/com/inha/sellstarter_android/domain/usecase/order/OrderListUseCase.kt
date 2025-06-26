package com.inha.sellstarter_android.domain.usecase.order

import com.inha.sellstarter_android.domain.model.OrderListPage
import com.inha.sellstarter_android.domain.repository.OrderRepository
import javax.inject.Inject

class FetchOrderConfirmListUseCase @Inject constructor(
    private val repository: OrderRepository
) {
    suspend operator fun invoke(page: Int, size: Int): Result<OrderListPage> =
        repository.loadOrderConfirmList(page = page, size = size)
}

class FetchCompletedPickingListUseCase @Inject constructor(
    private val repository: OrderRepository
) {
    suspend operator fun invoke(page: Int, size: Int): Result<OrderListPage> =
        repository.loadCompletedPickingList(page = page, size = size)
}