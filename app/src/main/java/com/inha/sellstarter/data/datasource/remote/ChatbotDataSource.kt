package com.inha.sellstarter.data.datasource.remote

import com.inha.sellstarter.data.model.request.chatbot.ChatbotMessageRequestDto
import com.inha.sellstarter.data.model.response.chatbot.ChatbotEndResponseDto
import com.inha.sellstarter.data.model.response.chatbot.ChatbotResponseDto
import com.inha.sellstarter.util.base.BaseResponseDto

interface ChatbotDataSource {
    suspend fun startChatSession(): BaseResponseDto<ChatbotResponseDto>

    suspend fun sendChatMessage(chatbotMessageRequestDto: ChatbotMessageRequestDto): BaseResponseDto<ChatbotResponseDto>

    suspend fun endChatSession(): ChatbotEndResponseDto
}
