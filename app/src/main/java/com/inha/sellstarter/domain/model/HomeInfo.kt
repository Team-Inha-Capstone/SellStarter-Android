package com.inha.sellstarter.domain.model

data class HomeInfo(
    val shopName: String,
    val newOrder: Int,
    val pickingCompleted: Int,
    val shippingCompleted: Int,
    val cancelRequest: Int,
    val returnRequest: Int,
    val analysisSubscribed: Boolean,
)
