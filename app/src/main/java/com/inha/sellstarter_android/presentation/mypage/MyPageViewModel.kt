package com.inha.sellstarter_android.presentation.mypage

import android.net.http.HttpException
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inha.sellstarter_android.data.model.request.mypage.UserApiDeleteRequestDto
import com.inha.sellstarter_android.data.model.request.mypage.UserApiRequestDto
import com.inha.sellstarter_android.data.model.request.mypage.UserApiUpdateRequest
import com.inha.sellstarter_android.domain.model.UserInfo
import com.inha.sellstarter_android.domain.usecase.mypage.MyPageUseCases
import com.inha.sellstarter_android.util.base.UiState
import com.inha.sellstarter_android.util.base.safeApiCall
import com.inha.sellstarter_android.util.extension.logHttpError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val myPageUseCases: MyPageUseCases
) : ViewModel() {

    // 1. 유저 정보
    private val _userInfoState = MutableStateFlow<UiState<UserInfo>>(UiState.Loading)
    val userInfoState: StateFlow<UiState<UserInfo>> = _userInfoState

    // 2. API 등록
    private val _createApiKeyState = MutableStateFlow<UiState<UserInfo>>(UiState.Loading)
    val createApiKeyState: StateFlow<UiState<UserInfo>> = _createApiKeyState

    // 3. API 수정
    private val _updateApiKeyState = MutableStateFlow<UiState<UserInfo>>(UiState.Loading)
    val updateApiKeyState: StateFlow<UiState<UserInfo>> = _updateApiKeyState

    // 4. API 삭제
    private val _deleteApiKeyState = MutableStateFlow<UiState<UserInfo>>(UiState.Loading)
    val deleteApiKeyState: StateFlow<UiState<UserInfo>> = _deleteApiKeyState

    fun getUserInfo() {
        viewModelScope.launch {
            _userInfoState.value = safeApiCall(
                onStart = { _userInfoState.value = UiState.Loading },
                onError = { it.logHttpError("getUserInfo") },
                apiCall = { myPageUseCases.userInfoUseCase.invoke() }
            )
        }
    }

    fun createApiKey(request: UserApiRequestDto) {
        viewModelScope.launch {
            _createApiKeyState.value = safeApiCall(
                onStart = { _createApiKeyState.value = UiState.Loading },
                onError = { it.logHttpError("createApiKey") },
                apiCall = {
                    myPageUseCases.userApiUseCase.invoke(request).also { getUserInfo() }
                }
            )
        }
    }

    fun updateApiKey(request: UserApiUpdateRequest) {
        viewModelScope.launch {
            _updateApiKeyState.value = safeApiCall(
                onStart = { _updateApiKeyState.value = UiState.Loading },
                onError = { it.logHttpError("updateApiKey") },
                apiCall = {
                    myPageUseCases.userApiUpdateUseCase.invoke(request).also { getUserInfo() }
                }
            )
        }
    }

    fun deleteApiKey(request: UserApiDeleteRequestDto) {
        viewModelScope.launch {
            _deleteApiKeyState.value = safeApiCall(
                onStart = { _deleteApiKeyState.value = UiState.Loading },
                onError = { it.logHttpError("deleteApiKey") },
                apiCall = {
                    myPageUseCases.userApiDeleteUseCase.invoke(request).also { getUserInfo() }
                }
            )
        }
    }
}