package com.inha.sellstarter_android.domain.usecase.chatbot

data class ChatbotUseCases(
    val startChatSession: ChatbotStartUseCase,
    val endChatSession: ChatbotEndUseCase,
    val sendChatMessage: ChatbotMessageUseCase
)