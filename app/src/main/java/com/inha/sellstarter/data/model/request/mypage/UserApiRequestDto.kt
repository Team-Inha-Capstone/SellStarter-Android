package com.inha.sellstarter.data.model.request.mypage

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserApiRequestDto(
    @SerialName("user_id")
    val userId: Int,
    @SerialName("channel_id")
    val channelId: Int,
    @SerialName("key")
    val key: String,
)
