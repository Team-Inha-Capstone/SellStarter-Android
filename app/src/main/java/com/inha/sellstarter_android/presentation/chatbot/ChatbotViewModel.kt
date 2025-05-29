package com.inha.sellstarter_android.presentation.chatbot

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inha.sellstarter_android.data.model.request.chatbot.ChatbotMessageRequestDto
import com.inha.sellstarter_android.domain.model.ChatMessage
import com.inha.sellstarter_android.domain.usecase.chatbot.ChatbotUseCases
import com.inha.sellstarter_android.util.base.UiState
import com.inha.sellstarter_android.util.base.safeApiCall
import com.inha.sellstarter_android.util.extension.logHttpError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatbotViewModel @Inject constructor(
    private val chatbotUseCases: ChatbotUseCases
) : ViewModel() {

    private val _chatMessages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val chatMessages: StateFlow<List<ChatMessage>> = _chatMessages

    private val _isBotTyping = MutableStateFlow(true)
    val isBotTyping: StateFlow<Boolean> = _isBotTyping

    fun startChatbot() {
        viewModelScope.launch {
            safeApiCall(
                onStart = {},
                onError = { it.logHttpError("chatbotStart") },
                apiCall = {
                    chatbotUseCases.chatStartUseCase.invoke()
                }
            ).let { result ->
                _isBotTyping.value = false
                handleBotResponse(result)
            }
        }
    }

    fun sendMessage(message: String) {
        appendMessage(ChatMessage(message, isUser = true))
        _isBotTyping.value = true
        viewModelScope.launch {
            safeApiCall(
                onStart = {},
                onError = { it.logHttpError("chatbotSend") },
                apiCall = {
                    chatbotUseCases.chatbotMessageUseCase.invoke(
                        ChatbotMessageRequestDto(message = message)
                    )
                }
            ).let { result ->
                _isBotTyping.value = false
                handleBotResponse(result)
            }
        }
    }

    fun endChatbot() {
        viewModelScope.launch {
            chatbotUseCases.chatEndUseCase.invoke()
            _chatMessages.value = emptyList() // 대화 종료 시 리스트 초기화
        }
    }

    private fun appendMessage(message: ChatMessage) {
        _chatMessages.value += message
    }

    private fun handleBotResponse(result: UiState<ChatMessage>) {
        when (result) {
            is UiState.Success -> {
                appendMessage(result.data)
            }

            is UiState.Failure -> {
                appendMessage(
                    ChatMessage(
                        "답변 실패: ${result.message ?: "네트워크 오류"}",
                        isUser = false
                    )
                )
            }

            else -> Unit
        }
    }
}
