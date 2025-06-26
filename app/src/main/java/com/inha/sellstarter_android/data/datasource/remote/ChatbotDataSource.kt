package com.inha.sellstarter_android.data.datasource.remote

import com.inha.sellstarter_android.data.model.request.chatbot.ChatbotMessageRequestDto
import com.inha.sellstarter_android.data.model.response.chatbot.ChatbotEndResponseDto
import com.inha.sellstarter_android.data.model.response.chatbot.ChatbotResponseDto
import com.inha.sellstarter_android.util.base.BaseResponseDto

interface ChatbotDataSource {
    suspend fun startChatSession(): BaseResponseDto<ChatbotResponseDto>
    suspend fun sendChatMessage(chatbotMessageRequestDto: ChatbotMessageRequestDto): BaseResponseDto<ChatbotResponseDto>
    suspend fun endChatSession(): ChatbotEndResponseDto
}