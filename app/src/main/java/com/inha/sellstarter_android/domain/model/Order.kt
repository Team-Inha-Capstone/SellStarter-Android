package com.inha.sellstarter_android.domain.model

data class Order(
    val id: Int,
    val productName: String,
    val optionDetail: String,
    val orderDate: String,
    val storeTag: String, // N, etc
    val status: OrderStatus
)

enum class OrderStatus {
    NEW, PICKED
}
