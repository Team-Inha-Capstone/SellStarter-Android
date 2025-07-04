package com.inha.sellstarter.data.mapper

import com.inha.sellstarter.data.model.response.order.OrderDetailResponseDto
import com.inha.sellstarter.data.model.response.order.OrderInventoryItemDto
import com.inha.sellstarter.data.model.response.order.OrderItemDto
import com.inha.sellstarter.data.model.response.order.OrderListResponseDto
import com.inha.sellstarter.domain.model.BuyerInfo
import com.inha.sellstarter.domain.model.OrderDetailInfo
import com.inha.sellstarter.domain.model.OrderInfo
import com.inha.sellstarter.domain.model.OrderListPage
import com.inha.sellstarter.domain.model.OrderPickingInventory
import com.inha.sellstarter.domain.model.OrderSummary
import com.inha.sellstarter.domain.model.PickingInfo
import com.inha.sellstarter.domain.model.type.ChannelPlatform
import com.inha.sellstarter.domain.model.type.OrderStatusType
import com.inha.sellstarter.util.extension.toPrettyDate

fun OrderListResponseDto.toDomain(): OrderListPage =
    OrderListPage(
        orders = this.contents.map { it.toDomain() },
        page = this.page,
        size = this.size,
        totalElements = this.totalElements,
        totalPages = this.totalPages,
    )

fun OrderItemDto.toDomain(): OrderSummary =
    OrderSummary(
        orderId = this.orderId,
        orderDate = this.orderDate.toPrettyDate(),
        channel = ChannelPlatform.fromChannelId(this.channelId),
        inventoryItem = this.inventoryItem ?: "상품명 없음",
    )

fun OrderDetailResponseDto.toDomain(): OrderDetailInfo {
    return OrderDetailInfo(
        orderInfo =
            OrderInfo(
                orderId = orderId,
                channelName = channel,
                orderStatus = OrderStatusType.from(orderStatus),
            ),
        pickingInfo =
            PickingInfo(
                items = inventoryList.map { it.toDomain() },
                allPicked = allPicked,
            ),
        buyerInfo =
            BuyerInfo(
                purchaserName = purchaserName ?: "이름 정보 없음",
                purchaserAddress = purchaserAddress ?: "주소 정보 없음",
                purchaserRequest = purchaserRequest ?: "요청사항 없음",
            ),
    )
}

fun OrderInventoryItemDto.toDomain(): OrderPickingInventory =
    OrderPickingInventory(
        inventoryName = this.inventoryName,
        barcodeId = this.barcodeId,
        inventoryCount = this.inventoryCount,
        isPicked = this.isPicked,
    )
