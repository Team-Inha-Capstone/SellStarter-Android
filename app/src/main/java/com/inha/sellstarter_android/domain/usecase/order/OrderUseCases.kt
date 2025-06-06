package com.inha.sellstarter_android.domain.usecase.order

data class OrderUseCases(
    val fetchOrderConfirmListUseCase: FetchOrderConfirmListUseCase,
    val fetchOrderConfirmationDetailUseCase: FetchOrderConfirmationDetailUseCase,
    val isPickingAvailableUseCase: IsPickingAvailableUseCase,
    val completeOrderPickingsUseCase: CompleteOrderPickingsUseCase,
    val completeSinglePickingUseCase: CompleteSinglePickingUseCase,
    val fetchCompletedPickingListUseCase: FetchCompletedPickingListUseCase,
    val confirmOrderShipmentUseCase: ConfirmOrderShipmentUseCase,
    val cancelOrderUseCase: CancelOrderUseCase
)