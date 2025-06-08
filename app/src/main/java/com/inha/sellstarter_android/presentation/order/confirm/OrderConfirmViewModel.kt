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
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderConfirmViewModel @Inject constructor(
    private val fetchNewOrders: FetchOrderConfirmListUseCase,
    private val fetchDoneOrders: FetchCompletedPickingListUseCase,
    private val completePickings: CompleteOrderPickingsUseCase
) : ViewModel() {

    companion object {
        private const val PAGE_SIZE = 10
    }

    // 탭 인덱스: 0=신규, 1=피킹완료
    private val _selectedTab = MutableStateFlow(0)
    val selectedTab: StateFlow<Int> = _selectedTab.asStateFlow()

    // 페이징 상태
    private val _newPage = MutableStateFlow(1)
    private val _newTotal = MutableStateFlow(1)
    private val _donePage = MutableStateFlow(1)
    private val _doneTotal = MutableStateFlow(1)
    val newPage: StateFlow<Int> = _newPage.asStateFlow()
    val newTotalPages: StateFlow<Int> = _newTotal.asStateFlow()
    val donePage: StateFlow<Int> = _donePage.asStateFlow()
    val doneTotalPages: StateFlow<Int> = _doneTotal.asStateFlow()

    // 데이터 상태
    private val _newState = MutableStateFlow<UiState<OrderListPage>>(UiState.Loading)
    private val _doneState = MutableStateFlow<UiState<OrderListPage>>(UiState.Loading)
    val newOrdersState: StateFlow<UiState<OrderListPage>> = _newState.asStateFlow()
    val doneOrdersState: StateFlow<UiState<OrderListPage>> = _doneState.asStateFlow()

    // 선택된 주문 ID 집합
    private val _selectedIds = MutableStateFlow<Set<String>>(emptySet())
    val selectedIds: StateFlow<Set<String>> = _selectedIds.asStateFlow()

    init {
        loadNewOrders(1)
        loadDoneOrders(1)
    }

    fun onTabSelected(index: Int) {
        _selectedTab.value = index
    }

    fun loadNewOrders(page: Int = 0) = viewModelScope.launch {
        _newPage.value = page
        _newState.value = UiState.Loading

        val result = safeApiCall(
            onStart = { _newState.value = UiState.Loading },
            onError = { it.logHttpError("loadNewOrders") },
            apiCall = { fetchNewOrders(page, PAGE_SIZE) }
        )
        _newState.value = result
        if (result is UiState.Success) {
            _newTotal.value = result.data.totalPages
        }
    }

    fun loadDoneOrders(page: Int = 0) = viewModelScope.launch {
        _donePage.value = page
        _doneState.value = UiState.Loading

        val result = safeApiCall(
            onStart = { _doneState.value = UiState.Loading },
            onError = { it.logHttpError("loadDoneOrders") },
            apiCall = { fetchDoneOrders(page, PAGE_SIZE) }
        )
        _doneState.value = result
        if (result is UiState.Success) {
            _doneTotal.value = result.data.totalPages
        }
    }

    fun onItemSelect(orderId: String) {
        val set = _selectedIds.value.toMutableSet().apply {
            if (!remove(orderId)) add(orderId)
        }
        _selectedIds.value = set
    }

    fun onSelectAll(summaries: OrderListPage) {
        _selectedIds.value = summaries.orders.map { it.orderId }.toSet()
    }

//
//    fun onClickCompleteSelected() = viewModelScope.launch {
//        val ids = _selectedIds.value.toList()
//        runCatching { completePickings(ids) }
//            .onSuccess {
//                _selectedIds.value = emptySet()
//                loadNewOrders(_newPage.value)
//                loadDoneOrders(_donePage.value)
//            }
//            .onFailure {
//                // 에러 처리 로직
//            }
//    }

    /**
     * 선택된 여러 주문을 일괄로 피킹 완료 처리.
     *
     * 1) 먼저 모든 orderIds에 대해 isPickingAvailableUseCase를 호출하여
     *    하나라도 false가 나오면 바로 UiState.Failure("NotAllPicked")로 설정.
     * 2) 모두 true일 때만, for-loop로 completeOrderPickingsUseCase 호출하여
     *    성공 개수를 카운트. 그 후 UiState.Success(count)로 상태 업데이트.
     */
//    fun completeSelectedOrders(orderIds: List<String>) {
//        viewModelScope.launch {
//            _batchCompleteState.value = UiState.Loading
//
//            // 1) 피킹 가능 여부 검사
//            for (id in orderIds) {
//                val available = isPickingAvailableUseCase(id).getOrDefault(false)
//                if (!available) {
//                    // 하나라도 false면 에러 상태 반환
//                    _batchCompleteState.value = UiState.Failure("NotAllPicked")
//                    return@launch
//                }
//            }
//
//            // 2) 모두 가능하면, 실제 완료 처리
//            var successCount = 0
//            for (id in orderIds) {
//                runCatching {
//                    completeOrderPickingsUseCase(id)
//                }.onSuccess {
//                    successCount++
//                }
//            }
//
//            // 3) 성공 개수 상태로 반환
//            _batchCompleteState.value = UiState.Success(successCount)
//
//            // 4) 목록 갱신
//            loadNewOrders()
//            loadCompletedPickings()
//        }
//    }
//
//    fun resetBatchCompleteState() {
//        _batchCompleteState.value = UiState.Loading
//    }
}