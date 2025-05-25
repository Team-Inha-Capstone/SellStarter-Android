package com.inha.sellstarter_android.data.mapper

import com.inha.sellstarter_android.data.model.response.chatbot.ChatbotEndResponseDto
import com.inha.sellstarter_android.data.model.response.chatbot.ChatbotResponseDto
import com.inha.sellstarter_android.domain.model.ChatMessage

fun ChatbotResponseDto.toDomain(): ChatMessage {
    return ChatMessage(
        message = this.response,
        isUser = false
    )
}

fun ChatbotEndResponseDto.toDomain() : ChatMessage  {
    return ChatMessage(
        message = this.message,
        isUser = false
    )
}