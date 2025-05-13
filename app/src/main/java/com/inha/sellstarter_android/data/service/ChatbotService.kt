package com.inha.sellstarter_android.data.service

import com.inha.sellstarter_android.data.model.request.chatbot.ChatbotMessageRequestDto
import com.inha.sellstarter_android.data.model.request.chatbot.ChatbotRequestDto
import com.inha.sellstarter_android.data.model.response.chatbot.ChatbotResponseDto
import com.inha.sellstarter_android.data.util.Constants.CHAT
import com.inha.sellstarter_android.data.util.Constants.CHAT_END
import com.inha.sellstarter_android.data.util.Constants.CHAT_START
import com.inha.sellstarter_android.util.base.BaseResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface ChatbotService {
    @POST("$CHAT_START")
    suspend fun postChatStart(
        @Body chatbotRequestDto: ChatbotRequestDto
    ): BaseResponseDto<ChatbotResponseDto>

    @POST("$CHAT")
    suspend fun postChatMessage(
        @Body chatbotMessageRequestDto: ChatbotMessageRequestDto
    ): BaseResponseDto<ChatbotResponseDto>

    @POST("$CHAT_END")
    suspend fun postChatEnd(
        @Body chatbotRequestDto: ChatbotRequestDto
    ): BaseResponseDto<ChatbotResponseDto>

}