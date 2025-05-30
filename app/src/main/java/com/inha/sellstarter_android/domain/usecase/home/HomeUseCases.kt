package com.inha.sellstarter_android.domain.usecase.home

data class HomeUseCases(
    val homeInfoUseCase: HomeInfoUseCase,
    val weeklySalesUseCase: WeeklySalesUseCase,
    val yearlySalesUseCase: YearlySalesUseCase
)