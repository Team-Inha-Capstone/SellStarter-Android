package com.inha.sellstarter_android.domain.usecase.order

data class OrderUseCases(
    val loadOrderConfirmListUseCase: LoadOrderConfirmListUseCase,
    val loadOrderConfirmationDetailUseCase: LoadOrderConfirmationDetailUseCase,
    val checkPickingAvailableUseCase: CheckPickingAvailableUseCase,
    val completeOrderPickingsUseCase: CompleteOrderPickingsUseCase,
    val completeSinglePickingUseCase: CompleteSinglePickingUseCase,
    val loadCompletedPickingListUseCase: LoadCompletedPickingListUseCase,
    val shipOrderUseCase: ShipOrderUseCase,
    val cancelOrderUseCase: CancelOrderUseCase
)