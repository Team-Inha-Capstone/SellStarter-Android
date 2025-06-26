package com.inha.sellstarter_android.data.datasource.remote.impl

import android.util.Log
import com.inha.sellstarter_android.data.datasource.remote.ChatbotDataSource
import com.inha.sellstarter_android.data.model.request.chatbot.ChatbotMessageRequestDto
import com.inha.sellstarter_android.data.model.response.chatbot.ChatbotEndResponseDto
import com.inha.sellstarter_android.data.model.response.chatbot.ChatbotResponseDto
import com.inha.sellstarter_android.data.service.ChatbotService
import com.inha.sellstarter_android.util.base.BaseResponseDto
import javax.inject.Inject

class ChatbotDataSourceImpl @Inject constructor(
    private val chatbotService: ChatbotService
) : ChatbotDataSource {
    override suspend fun postChatStart(): BaseResponseDto<ChatbotResponseDto> {
        Log.e("hyeon", chatbotService.startChatSession().toString());
        return chatbotService.startChatSession()
    }

    override suspend fun postChatMessage(chatbotMessageRequestDto: ChatbotMessageRequestDto): BaseResponseDto<ChatbotResponseDto> {
        return chatbotService.sendChatMessage(chatbotMessageRequestDto = chatbotMessageRequestDto)
    }

    override suspend fun postChatEnd(): ChatbotEndResponseDto {
        return chatbotService.endChatSession()
    }
}