package com.inha.sellstarter.presentation.order.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inha.sellstarter.data.model.request.order.OrderInventoryPickingRequestDto
import com.inha.sellstarter.domain.model.OrderDetailInfo
import com.inha.sellstarter.domain.usecase.order.CancelOrderUseCase
import com.inha.sellstarter.domain.usecase.order.CompleteOrderPickingsUseCase
import com.inha.sellstarter.domain.usecase.order.CompleteSinglePickingUseCase
import com.inha.sellstarter.domain.usecase.order.LoadOrderConfirmationDetailUseCase
import com.inha.sellstarter.domain.usecase.order.ShipOrderUseCase
import com.inha.sellstarter.util.base.UiState
import com.inha.sellstarter.util.base.safeApiCall
import com.inha.sellstarter.util.extension.logHttpError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderDetailViewModel
    @Inject
    constructor(
        private val orderConfirmationDetailUseCase: LoadOrderConfirmationDetailUseCase,
        private val cancelOrderUseCase: CancelOrderUseCase,
        private val singlePickingUseCase: CompleteSinglePickingUseCase,
        private val shipOrderUseCase: ShipOrderUseCase,
        private val completeOrderPickingsUseCase: CompleteOrderPickingsUseCase,
    ) : ViewModel() {
        // 1. 주문 상세 조회 상태
        private val _orderDetailState = MutableStateFlow<UiState<OrderDetailInfo>>(UiState.Loading)
        val orderDetailState: StateFlow<UiState<OrderDetailInfo>> = _orderDetailState

        // 2. 주문 취소 상태
        private val _cancelState = MutableStateFlow<UiState<Unit>>(UiState.Loading)
        val cancelState: StateFlow<UiState<Unit>> = _cancelState

        // 3. 단일 상품 피킹 상태
        private val _singlePickingState = MutableStateFlow<UiState<Unit>>(UiState.Loading)
        val singlePickingState: StateFlow<UiState<Unit>> = _singlePickingState

        // 4. 출고 완료 상태
        private val _confirmShipmentState = MutableStateFlow<UiState<Unit>>(UiState.Loading)
        val confirmShipmentState: StateFlow<UiState<Unit>> = _confirmShipmentState

        // 5. 전체 상품 피킹 완료 상태
        private val _completePickingsState = MutableStateFlow<UiState<Unit>>(UiState.Loading)
        val completePickingsState: StateFlow<UiState<Unit>> = _completePickingsState

        fun loadOrderDetail(orderId: String) {
            viewModelScope.launch {
                _orderDetailState.value =
                    safeApiCall(
                        onStart = { _orderDetailState.value = UiState.Loading },
                        onError = { it.logHttpError("loadOrderDetail") },
                        apiCall = { orderConfirmationDetailUseCase(orderId) },
                    )
                Log.e("OrderDetailVM", "_orderDetailState = ${_orderDetailState.value}")
            }
        }

        fun completeOrderPickings(orderId: String) {
            viewModelScope.launch {
                _completePickingsState.value =
                    safeApiCall(
                        onStart = { _completePickingsState.value = UiState.Loading },
                        onError = { it.logHttpError("completeOrderPickings") },
                        apiCall = { completeOrderPickingsUseCase(orderId) },
                    )
                Log.e("OrderDetailVM", "_completePickingsState = ${_completePickingsState.value}")
            }
        }

        fun completeSinglePicking(
            orderId: String,
            request: OrderInventoryPickingRequestDto,
        ) {
            viewModelScope.launch {
                _singlePickingState.value =
                    safeApiCall(
                        onStart = { _singlePickingState.value = UiState.Loading },
                        onError = { it.logHttpError("completeSinglePicking") },
                        apiCall = { singlePickingUseCase(orderId, request) },
                    )
                Log.e("OrderDetailVM", "_singlePickingState = ${_singlePickingState.value}")
            }
        }

        fun confirmOrderShipment(orderId: String) {
            viewModelScope.launch {
                _confirmShipmentState.value =
                    safeApiCall(
                        onStart = { _confirmShipmentState.value = UiState.Loading },
                        onError = { it.logHttpError("confirmOrderShipment") },
                        apiCall = { shipOrderUseCase(orderId) },
                    )
                Log.e("OrderDetailVM", "_confirmShipmentState = ${_confirmShipmentState.value}")
            }
        }

        fun cancelOrder(orderId: String) {
            viewModelScope.launch {
                _cancelState.value =
                    safeApiCall(
                        onStart = { _cancelState.value = UiState.Loading },
                        onError = { it.logHttpError("cancelOrder") },
                        apiCall = { cancelOrderUseCase(orderId) },
                    )
                Log.e("OrderDetailVM", "_cancelState = ${_cancelState.value}")
            }
        }
    }
