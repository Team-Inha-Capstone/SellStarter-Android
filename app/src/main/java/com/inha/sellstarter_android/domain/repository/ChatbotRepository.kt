package com.inha.sellstarter_android.domain.repository

import com.inha.sellstarter_android.data.model.request.chatbot.ChatbotMessageRequestDto
import com.inha.sellstarter_android.data.model.request.chatbot.ChatbotRequestDto
import com.inha.sellstarter_android.domain.model.ChatMessage

interface ChatbotRepository {
    suspend fun postChatStart(chatbotRequestDto: ChatbotRequestDto): Result<ChatMessage>
    suspend fun postChatMessage(chatbotMessageRequestDto: ChatbotMessageRequestDto): Result<ChatMessage>
    suspend fun postChatEnd(chatbotRequestDto: ChatbotRequestDto): Result<ChatMessage>
}