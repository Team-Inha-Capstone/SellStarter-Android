package com.inha.sellstarter_android.presentation.order.confirm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inha.sellstarter_android.domain.model.OrderListPage
import com.inha.sellstarter_android.domain.usecase.order.CompleteOrderPickingsUseCase
import com.inha.sellstarter_android.domain.usecase.order.FetchCompletedPickingListUseCase
import com.inha.sellstarter_android.domain.usecase.order.FetchOrderConfirmListUseCase
import com.inha.sellstarter_android.domain.usecase.order.IsPickingAvailableUseCase
import com.inha.sellstarter_android.util.base.UiState
import com.inha.sellstarter_android.util.base.safeApiCall
import com.inha.sellstarter_android.util.extension.logHttpError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderConfirmViewModel @Inject constructor(
    private val fetchOrderConfirmListUseCase: FetchOrderConfirmListUseCase,
    private val fetchCompletedPickingListUseCase: FetchCompletedPickingListUseCase,
    private val isPickingAvailableUseCase: IsPickingAvailableUseCase,
    private val completeOrderPickingsUseCase: CompleteOrderPickingsUseCase
) : ViewModel() {

    // 1. 신규 주문 목록 조회 상태
    private val _newOrderListState = MutableStateFlow<UiState<OrderListPage>>(UiState.Loading)
    val newOrderListState: StateFlow<UiState<OrderListPage>> = _newOrderListState

    // 2. 피킹 완료 목록 조회 상태
    private val _completedPickingListState =
        MutableStateFlow<UiState<OrderListPage>>(UiState.Loading)
    val completedPickingListState: StateFlow<UiState<OrderListPage>> = _completedPickingListState

    // 3. 개별 주문의 피킹 가능 여부 확인 상태
    private val _pickingAvailableState = MutableStateFlow<UiState<Boolean>>(UiState.Loading)
    val pickingAvailableState: StateFlow<UiState<Boolean>> = _pickingAvailableState

    // 4. (추가) 여러 주문을 선택하여 일괄 피킹 완료할 때 사용될 상태
    private val _batchCompleteState = MutableStateFlow<UiState<Int>>(UiState.Loading)
    val batchCompleteState: StateFlow<UiState<Int>> = _batchCompleteState

    fun loadNewOrders(page: Int = 0, size: Int = 10) {
        viewModelScope.launch {
            _newOrderListState.value = safeApiCall(
                onStart = { _newOrderListState.value = UiState.Loading },
                onError = { it.logHttpError("loadNewOrders") },
                apiCall = { fetchOrderConfirmListUseCase(page, size) }
            )
            Log.e("hyeon", _newOrderListState.value.toString())
        }
    }


    fun loadCompletedPickings(page: Int = 0, size: Int = 10) {
        viewModelScope.launch {
            _completedPickingListState.value = safeApiCall(
                onStart = { _completedPickingListState.value = UiState.Loading },
                onError = { it.logHttpError("loadCompletedPickings") },
                apiCall = { fetchCompletedPickingListUseCase(page, size) }
            )
            Log.e("hyeon", _completedPickingListState.value.toString())
        }
    }

    fun checkOrderPickingAvailable(orderId: String) {
        viewModelScope.launch {
            _pickingAvailableState.value = safeApiCall(
                onStart = { _pickingAvailableState.value = UiState.Loading },
                onError = { it.logHttpError("checkPickingAvailable") },
                apiCall = { isPickingAvailableUseCase(orderId) }
            )
            Log.e("hyeon", _pickingAvailableState.value.toString())
        }
    }

    fun completeOrderPickings(orderId: String) {
        viewModelScope.launch {
            runCatching {
                completeOrderPickingsUseCase(orderId)
            }.onSuccess {
                // 완료 후 목록 갱신
                loadNewOrders()
                loadCompletedPickings()
            }
        }
    }

    /**
     * 선택된 여러 주문을 일괄로 피킹 완료 처리.
     *
     * 1) 먼저 모든 orderIds에 대해 isPickingAvailableUseCase를 호출하여
     *    하나라도 false가 나오면 바로 UiState.Failure("NotAllPicked")로 설정.
     * 2) 모두 true일 때만, for-loop로 completeOrderPickingsUseCase 호출하여
     *    성공 개수를 카운트. 그 후 UiState.Success(count)로 상태 업데이트.
     */
    fun completeSelectedOrders(orderIds: List<String>) {
        viewModelScope.launch {
            _batchCompleteState.value = UiState.Loading

            // 1) 피킹 가능 여부 검사
            for (id in orderIds) {
                val available = isPickingAvailableUseCase(id).getOrDefault(false)
                if (!available) {
                    // 하나라도 false면 에러 상태 반환
                    _batchCompleteState.value = UiState.Failure("NotAllPicked")
                    return@launch
                }
            }

            // 2) 모두 가능하면, 실제 완료 처리
            var successCount = 0
            for (id in orderIds) {
                runCatching {
                    completeOrderPickingsUseCase(id)
                }.onSuccess {
                    successCount++
                }
            }

            // 3) 성공 개수 상태로 반환
            _batchCompleteState.value = UiState.Success(successCount)

            // 4) 목록 갱신
            loadNewOrders()
            loadCompletedPickings()
        }
    }

    fun resetBatchCompleteState() {
        _batchCompleteState.value = UiState.Loading
    }
}