package com.inha.sellstarter.data.model.response.chatbot

import kotlinx.serialization.Serializable

@Serializable
data class ChatbotResponseDto(
    val response: String,
)

@Serializable
data class ChatbotEndResponseDto(
    val success: Boolean,
    val message: String,
)
