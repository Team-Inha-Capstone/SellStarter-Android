package com.inha.sellstarter_android.data.model.request.chatbot

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ChatbotMessageRequestDto(
    @SerialName("user_id")
    val userId: Int,
    @SerialName("message")
    val message: String
)


@Serializable
data class ChatbotRequestDto(
    @SerialName("user_id")
    val userId: Int
)


