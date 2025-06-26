package com.inha.sellstarter_android.domain.usecase.chatbot

import com.inha.sellstarter_android.data.model.request.chatbot.ChatbotMessageRequestDto
import com.inha.sellstarter_android.domain.repository.ChatbotRepository
import com.inha.sellstarter_android.domain.model.ChatMessage
import javax.inject.Inject

class ChatbotMessageUseCase @Inject constructor(
    private val chatbotRepository: ChatbotRepository
) {
    suspend operator fun invoke(chatbotMessageRequestDto: ChatbotMessageRequestDto): Result<ChatMessage> {
        return chatbotRepository.sendChatMessage(chatbotMessageRequestDto)
    }
}