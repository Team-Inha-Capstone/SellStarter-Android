package com.inha.sellstarter.domain.model

import com.inha.sellstarter.domain.model.type.ChannelPlatform
import com.inha.sellstarter.domain.model.type.OrderStatusType

data class OrderListPage(
    val orders: List<OrderSummary>,
    val page: Int,
    val size: Int,
    val totalElements: Int,
    val totalPages: Int,
)

data class OrderSummary(
    val orderId: String,
    val orderDate: String,
    val channel: ChannelPlatform,
    val inventoryItem: String?,
) // 주문 리스트 내 주문 요약용 데이터 의미로 Summary 네이밍

data class OrderDetailInfo(
    val orderInfo: OrderInfo,
    val pickingInfo: PickingInfo,
    val buyerInfo: BuyerInfo,
)

data class OrderInfo(
    val orderId: String,
    val channelName: String,
    val orderStatus: OrderStatusType,
)

data class BuyerInfo(
    val purchaserName: String,
    val purchaserAddress: String,
    val purchaserRequest: String,
)
