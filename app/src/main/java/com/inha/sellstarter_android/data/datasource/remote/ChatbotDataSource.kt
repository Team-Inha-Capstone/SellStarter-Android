package com.inha.sellstarter_android.data.datasource.remote

import com.inha.sellstarter_android.data.model.request.chatbot.ChatbotMessageRequestDto
import com.inha.sellstarter_android.data.model.response.chatbot.ChatbotEndResponseDto
import com.inha.sellstarter_android.data.model.response.chatbot.ChatbotResponseDto
import com.inha.sellstarter_android.util.base.BaseResponseDto

interface ChatbotDataSource {
    suspend fun postChatStart(): BaseResponseDto<ChatbotResponseDto>
    suspend fun postChatMessage(chatbotMessageRequestDto: ChatbotMessageRequestDto): BaseResponseDto<ChatbotResponseDto>
    suspend fun postChatEnd(): ChatbotEndResponseDto
}