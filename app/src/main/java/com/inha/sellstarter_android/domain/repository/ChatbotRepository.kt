package com.inha.sellstarter_android.domain.repository

import com.inha.sellstarter_android.data.model.request.chatbot.ChatbotMessageRequestDto
import com.inha.sellstarter_android.domain.model.ChatMessage

interface ChatbotRepository {
    suspend fun startChatSession(): Result<ChatMessage>
    suspend fun sendChatMessage(chatbotMessageRequestDto: ChatbotMessageRequestDto): Result<ChatMessage>
    suspend fun endChatSession(): Result<ChatMessage>
}