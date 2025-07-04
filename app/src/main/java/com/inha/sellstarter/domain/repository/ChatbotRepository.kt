package com.inha.sellstarter.domain.repository

import com.inha.sellstarter.data.model.request.chatbot.ChatbotMessageRequestDto
import com.inha.sellstarter.domain.model.ChatMessage

interface ChatbotRepository {
    suspend fun startChatSession(): Result<ChatMessage>

    suspend fun sendChatMessage(chatbotMessageRequestDto: ChatbotMessageRequestDto): Result<ChatMessage>

    suspend fun endChatSession(): Result<ChatMessage>
}
