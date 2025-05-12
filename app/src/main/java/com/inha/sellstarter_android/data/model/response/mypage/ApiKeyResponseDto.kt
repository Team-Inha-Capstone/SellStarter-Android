package com.inha.sellstarter_android.data.model.response.mypage

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiKeyResponseDto(
    @SerialName("api_id")
    val apiId : Int,
    @SerialName("channel_id")
    val channelId : Int,
    val channel : String,
    val key : String
)