package com.inha.sellstarter.data.model.request.mypage

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserApiDeleteRequestDto(
    @SerialName("user_id")
    val userId: Int,
    @SerialName("api_id")
    val apiId: Int,
)
