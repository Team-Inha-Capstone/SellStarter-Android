package com.inha.sellstarter_android.domain.repository

import com.inha.sellstarter_android.domain.model.HomeInfo
import com.inha.sellstarter_android.domain.model.WeeklySales
import com.inha.sellstarter_android.domain.model.YearlySales

interface HomeRepository {
    suspend fun loadHomeDashboard() : Result<HomeInfo>
    suspend fun loadWeeklySalesInfo(currentDate : String): Result<WeeklySales>
    suspend fun loadYearlySalesInfo(currentDate: String): Result<YearlySales>
}