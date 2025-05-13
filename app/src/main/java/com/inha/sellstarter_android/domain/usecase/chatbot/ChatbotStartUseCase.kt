package com.inha.sellstarter_android.domain.usecase.chatbot

import com.inha.sellstarter_android.data.model.request.chatbot.ChatbotRequestDto
import com.inha.sellstarter_android.domain.repository.ChatbotRepository
import com.inha.sellstarter_android.domain.model.ChatMessage
import javax.inject.Inject

class ChatbotStartUseCase @Inject constructor(
    private val chatbotRepository: ChatbotRepository
) {
    suspend fun invoke(chatbotRequestDto: ChatbotRequestDto): Result<ChatMessage> {
        return chatbotRepository.postChatStart(chatbotRequestDto)
    }
}