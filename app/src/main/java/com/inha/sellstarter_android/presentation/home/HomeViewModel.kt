package com.inha.sellstarter_android.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inha.sellstarter_android.domain.model.HomeInfo
import com.inha.sellstarter_android.domain.usecase.home.HomeInfoUseCase
import com.inha.sellstarter_android.util.base.UiState
import com.inha.sellstarter_android.util.base.safeApiCall
import com.inha.sellstarter_android.util.extension.logHttpError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeInfoUseCase: HomeInfoUseCase
) : ViewModel() {

    private val _homeInfoState = MutableStateFlow<UiState<HomeInfo>>(UiState.Loading)
    val homeInfoState: StateFlow<UiState<HomeInfo>> = _homeInfoState

    init {
        getHomeInfo()
    }
    fun getHomeInfo() {
        viewModelScope.launch {
            _homeInfoState.value = safeApiCall(
                onStart = { _homeInfoState.value = UiState.Loading },
                onError = { it.logHttpError("getHomeInfo") },
                apiCall = { homeInfoUseCase.invoke() }
            )
        }
    }
}