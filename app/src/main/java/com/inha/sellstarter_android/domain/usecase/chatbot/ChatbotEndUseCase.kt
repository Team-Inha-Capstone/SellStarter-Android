package com.inha.sellstarter_android.domain.usecase.chatbot

import com.inha.sellstarter_android.domain.repository.ChatbotRepository
import com.inha.sellstarter_android.domain.model.ChatMessage
import javax.inject.Inject

class ChatbotEndUseCase @Inject constructor(
    private val chatbotRepository: ChatbotRepository
) {
    suspend fun invoke(): Result<ChatMessage> {
        return chatbotRepository.endChatSession()
    }
}