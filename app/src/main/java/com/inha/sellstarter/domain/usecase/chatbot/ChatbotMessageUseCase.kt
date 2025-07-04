package com.inha.sellstarter.domain.usecase.chatbot

import com.inha.sellstarter.data.model.request.chatbot.ChatbotMessageRequestDto
import com.inha.sellstarter.domain.model.ChatMessage
import com.inha.sellstarter.domain.repository.ChatbotRepository
import javax.inject.Inject

class ChatbotMessageUseCase
    @Inject
    constructor(
        private val chatbotRepository: ChatbotRepository,
    ) {
        suspend operator fun invoke(chatbotMessageRequestDto: ChatbotMessageRequestDto): Result<ChatMessage> {
            return chatbotRepository.sendChatMessage(chatbotMessageRequestDto)
        }
    }
