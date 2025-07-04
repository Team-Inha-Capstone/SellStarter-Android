package com.inha.sellstarter.data.service

import com.inha.sellstarter.data.model.request.chatbot.ChatbotMessageRequestDto
import com.inha.sellstarter.data.model.response.chatbot.ChatbotEndResponseDto
import com.inha.sellstarter.data.model.response.chatbot.ChatbotResponseDto
import com.inha.sellstarter.data.util.Constants.CHAT
import com.inha.sellstarter.data.util.Constants.CHAT_END
import com.inha.sellstarter.data.util.Constants.CHAT_START
import com.inha.sellstarter.util.base.BaseResponseDto
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface ChatbotService {
    @POST("$CHAT_START/{userId}")
    suspend fun startChatSession(
        @Path("userId") userId: Int = 4,
    ): BaseResponseDto<ChatbotResponseDto>

    @POST("$CHAT/{userId}")
    suspend fun sendChatMessage(
        @Path("userId") userId: Int = 4,
        @Body chatbotMessageRequestDto: ChatbotMessageRequestDto,
    ): BaseResponseDto<ChatbotResponseDto>

    @POST("$CHAT_END/{userId}")
    suspend fun endChatSession(
        @Path("userId") userId: Int = 4,
    ): ChatbotEndResponseDto
}
