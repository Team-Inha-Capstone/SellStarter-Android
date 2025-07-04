package com.inha.sellstarter.domain.repository

import com.inha.sellstarter.domain.model.HomeInfo
import com.inha.sellstarter.domain.model.WeeklySales
import com.inha.sellstarter.domain.model.YearlySales

interface HomeRepository {
    suspend fun loadHomeDashboard(): Result<HomeInfo>

    suspend fun loadWeeklySalesInfo(currentDate: String): Result<WeeklySales>

    suspend fun loadYearlySalesInfo(currentDate: String): Result<YearlySales>
}
