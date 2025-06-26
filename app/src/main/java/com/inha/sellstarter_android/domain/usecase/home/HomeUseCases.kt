package com.inha.sellstarter_android.domain.usecase.home

data class HomeUseCases(
    val loadHomeDashboard: LoadHomeDashboardUseCase,
    val loadWeeklySales: LoadWeeklySalesUseCase,
    val loadAnnualSales: LoadYearlySalesUseCase
)