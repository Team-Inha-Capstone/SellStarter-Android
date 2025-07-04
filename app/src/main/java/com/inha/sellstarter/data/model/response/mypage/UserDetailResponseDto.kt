package com.inha.sellstarter.data.model.response.mypage

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDetailResponseDto(
    @SerialName("user_name")
    val userName: String,
    @SerialName("shopping_category")
    val shoppingCategory: String,
    @SerialName("api_key")
    val apiKey: List<ApiKeyResponseDto>,
)
