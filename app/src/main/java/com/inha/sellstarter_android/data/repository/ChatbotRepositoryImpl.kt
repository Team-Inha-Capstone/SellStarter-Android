package com.inha.sellstarter_android.data.repository

import android.util.Log
import com.inha.sellstarter_android.data.datasource.remote.ChatbotDataSource
import com.inha.sellstarter_android.data.mapper.toDomain
import com.inha.sellstarter_android.data.model.request.chatbot.ChatbotMessageRequestDto
import com.inha.sellstarter_android.domain.repository.ChatbotRepository
import com.inha.sellstarter_android.domain.model.ChatMessage
import javax.inject.Inject

class ChatbotRepositoryImpl @Inject constructor(
    private val chatbotDataSource: ChatbotDataSource
) : ChatbotRepository {
    override suspend fun postChatStart(): Result<ChatMessage> {
        return runCatching {
            chatbotDataSource.postChatStart().data.toDomain()
        }
    }

    override suspend fun postChatMessage(chatbotMessageRequestDto: ChatbotMessageRequestDto): Result<ChatMessage> {
        return runCatching {
            chatbotDataSource.postChatMessage(chatbotMessageRequestDto).data.toDomain()
        }
    }

    override suspend fun postChatEnd(): Result<ChatMessage> {
        return runCatching {
            chatbotDataSource.postChatEnd().toDomain()
        }
    }
}