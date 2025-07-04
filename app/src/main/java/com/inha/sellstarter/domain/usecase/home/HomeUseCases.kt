package com.inha.sellstarter.domain.usecase.home

data class HomeUseCases(
    val loadHomeDashboard: LoadHomeDashboardUseCase,
    val loadWeeklySales: LoadWeeklySalesUseCase,
    val loadAnnualSales: LoadYearlySalesUseCase,
)
