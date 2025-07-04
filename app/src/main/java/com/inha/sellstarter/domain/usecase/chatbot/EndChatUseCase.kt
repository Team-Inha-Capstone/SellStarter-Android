package com.inha.sellstarter.domain.usecase.chatbot

import com.inha.sellstarter.domain.model.ChatMessage
import com.inha.sellstarter.domain.repository.ChatbotRepository
import javax.inject.Inject

class EndChatUseCase
    @Inject
    constructor(
        private val chatbotRepository: ChatbotRepository,
    ) {
        suspend operator fun invoke(): Result<ChatMessage> {
            return chatbotRepository.endChatSession()
        }
    }
