package com.inha.sellstarter_android.domain.usecase.chatbot

data class ChatbotUseCases(
    val chatStartUseCase: ChatbotStartUseCase,
    val chatEndUseCase: ChatbotEndUseCase,
    val chatbotMessageUseCase: ChatbotMessageUseCase
)