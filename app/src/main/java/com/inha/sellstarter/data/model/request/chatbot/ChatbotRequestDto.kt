package com.inha.sellstarter.data.model.request.chatbot

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChatbotMessageRequestDto(
    @SerialName("message")
    val message: String,
)
