package com.inha.sellstarter.data.model.request.mypage

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserApiUpdateRequest(
    @SerialName("user_id")
    val userId: Int,
    @SerialName("api_id")
    val apiId: Int,
    @SerialName("channel_id")
    val channelId: Int,
    val key: String,
)
