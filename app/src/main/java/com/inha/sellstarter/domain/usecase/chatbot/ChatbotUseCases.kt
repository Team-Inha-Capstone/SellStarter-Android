package com.inha.sellstarter.domain.usecase.chatbot

data class ChatbotUseCases(
    val startChatSession: StartChatUseCase,
    val endChatSession: EndChatUseCase,
    val sendChatMessage: ChatbotMessageUseCase,
)
