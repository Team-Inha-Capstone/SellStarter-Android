package com.inha.sellstarter_android.domain.model.type

enum class OrderStatusType(val displayName: String) {
    ORDER_COMPLETED("주문완료"),
    PIKING_COMPLETED("피킹완료");

    companion object {
        fun from(raw: String): OrderStatusType =
            when (raw.uppercase()) {
                "ORDER_COMPLETED" -> ORDER_COMPLETED
                "PIKING_COMPLETED" -> PIKING_COMPLETED
                else -> ORDER_COMPLETED
            }
    }
}