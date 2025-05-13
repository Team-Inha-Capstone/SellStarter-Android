package com.inha.sellstarter_android.data.datasource.remote.impl

import com.inha.sellstarter_android.data.datasource.remote.ChatbotDataSource
import com.inha.sellstarter_android.data.model.request.chatbot.ChatbotMessageRequestDto
import com.inha.sellstarter_android.data.model.request.chatbot.ChatbotRequestDto
import com.inha.sellstarter_android.data.model.response.chatbot.ChatbotResponseDto
import com.inha.sellstarter_android.data.service.ChatbotService
import com.inha.sellstarter_android.util.base.BaseResponseDto
import javax.inject.Inject

class ChatbotDataSourceImpl @Inject constructor(
    private val chatbotService: ChatbotService
) : ChatbotDataSource {
    override suspend fun postChatStart(chatbotRequestDto: ChatbotRequestDto): BaseResponseDto<ChatbotResponseDto> {
        return chatbotService.postChatStart(chatbotRequestDto)
    }

    override suspend fun postChatMessage(chatbotMessageRequestDto: ChatbotMessageRequestDto): BaseResponseDto<ChatbotResponseDto> {
        return chatbotService.postChatMessage(chatbotMessageRequestDto)
    }

    override suspend fun postChatEnd(chatbotRequestDto: ChatbotRequestDto): BaseResponseDto<ChatbotResponseDto> {
        return chatbotService.postChatEnd(chatbotRequestDto)
    }
}