package com.inha.sellstarter_android.domain.model

// 상세 inventory 도메인 모델
data class Inventory(
    val id: String,
    val name: String,
    val quantity: Int,
    val imageUrl: String,
    val expiration: String,
    val isSoldOut: Boolean,
    val option: String,
    val location: String
)

// 리스트 내 inventory 도메인 모델
data class InventoryItem(
    val id : String,
    val name : String,
    val quantity : Int,
    val isSoldOut: Boolean,
    val option : String?,
    val imageUrl : String?
)