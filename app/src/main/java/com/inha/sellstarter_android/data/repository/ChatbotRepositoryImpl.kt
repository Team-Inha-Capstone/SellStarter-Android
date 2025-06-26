package com.inha.sellstarter_android.data.repository

import com.inha.sellstarter_android.data.datasource.remote.ChatbotDataSource
import com.inha.sellstarter_android.data.mapper.toDomain
import com.inha.sellstarter_android.data.model.request.chatbot.ChatbotMessageRequestDto
import com.inha.sellstarter_android.domain.repository.ChatbotRepository
import com.inha.sellstarter_android.domain.model.ChatMessage
import javax.inject.Inject

class ChatbotRepositoryImpl @Inject constructor(
    private val chatbotDataSource: ChatbotDataSource
) : ChatbotRepository {
    override suspend fun startChatSession(): Result<ChatMessage> {
        return runCatching {
            chatbotDataSource.startChatSession().data.toDomain()
        }
    }

    override suspend fun sendChatMessage(chatbotMessageRequestDto: ChatbotMessageRequestDto): Result<ChatMessage> {
        return runCatching {
            chatbotDataSource.sendChatMessage(chatbotMessageRequestDto).data.toDomain()
        }
    }

    override suspend fun endChatSession(): Result<ChatMessage> {
        return runCatching {
            chatbotDataSource.endChatSession().toDomain()
        }
    }
}