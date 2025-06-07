package com.inha.sellstarter_android.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inha.sellstarter_android.domain.model.HomeInfo
import com.inha.sellstarter_android.domain.model.WeeklySales
import com.inha.sellstarter_android.domain.model.YearlySales
import com.inha.sellstarter_android.domain.usecase.home.HomeUseCases
import com.inha.sellstarter_android.util.base.UiState
import com.inha.sellstarter_android.util.base.safeApiCall
import com.inha.sellstarter_android.util.extension.logHttpError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCases: HomeUseCases
) : ViewModel() {

    private val currentDate: String = LocalDate.now().toString()

    private val _homeInfoState = MutableStateFlow<UiState<HomeInfo>>(UiState.Loading)
    val homeInfoState: StateFlow<UiState<HomeInfo>> = _homeInfoState

    private val _weeklySalesState = MutableStateFlow<UiState<WeeklySales>>(UiState.Loading)
    val weeklySalesState: StateFlow<UiState<WeeklySales>> = _weeklySalesState

    private val _yearlySalesState = MutableStateFlow<UiState<YearlySales>>(UiState.Loading)
    val yearlySalesState: StateFlow<UiState<YearlySales>> = _yearlySalesState

    init {
        getHomeInfo()
        getWeeklySales(currentDate = currentDate)
        getYearlySales(currentDate = currentDate)
    }

    fun getHomeInfo() {
        viewModelScope.launch {
            _homeInfoState.value = safeApiCall(
                onStart = { _homeInfoState.value = UiState.Loading },
                onError = { it.logHttpError("getHomeInfo") },
                apiCall = { homeUseCases.homeInfoUseCase.invoke() }
            )
        }
    }

    private fun getWeeklySales(
        currentDate: String
    ) {
        viewModelScope.launch {
            _weeklySalesState.value = safeApiCall(
                onStart = { _weeklySalesState.value = UiState.Loading },
                onError = { it.logHttpError("getWeeklySales") },
                apiCall = {
                    homeUseCases.weeklySalesUseCase.invoke(
                        currentDate = currentDate
                    )
                }
            )
        }
    }

    private fun getYearlySales(
        currentDate: String
    ) {
        viewModelScope.launch {
            _yearlySalesState.value = safeApiCall(
                onStart = { _yearlySalesState.value = UiState.Loading },
                onError = { it.logHttpError("getYearlySales") },
                apiCall = {
                    homeUseCases.yearlySalesUseCase.invoke(
                        currentDate = currentDate
                    )
                }
            )
        }
    }
}
