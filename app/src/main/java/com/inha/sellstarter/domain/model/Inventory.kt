package com.inha.sellstarter.domain.model

// 상세 inventory 도메인 모델
data class Inventory(
    val id: String,
    val name: String,
    val quantity: Int,
    val imageUrl: String,
    val expiration: String,
    val isSoldOut: Boolean,
    val option: String,
    val location: String,
)

data class InventoryListPage(
    val inventories: List<InventorySummary>,
    val page: Int,
    val size: Int,
    val totalElements: Int,
    val totalPages: Int,
)

// 리스트 내 inventory 도메인 모델
data class InventorySummary(
    val id: String,
    val name: String,
    val quantity: Int,
    val isSoldOut: Boolean,
    val option: String?,
    val imageUrl: String?,
)
