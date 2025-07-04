package com.inha.sellstarter.data.model.response.inventory

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InventoryListResponseDto(
    @SerialName("content")
    val content: List<InventoryItemDto>,
    @SerialName("page")
    val page: Int,
    @SerialName("size")
    val size: Int,
    @SerialName("total_elements")
    val totalElements: Int,
    @SerialName("total_pages")
    val totalPages: Int,
)
