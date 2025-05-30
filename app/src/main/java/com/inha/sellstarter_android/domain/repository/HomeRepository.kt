package com.inha.sellstarter_android.domain.repository

import com.inha.sellstarter_android.domain.model.HomeInfo
import com.inha.sellstarter_android.domain.model.WeeklySales
import com.inha.sellstarter_android.domain.model.YearlySales

interface HomeRepository {
    suspend fun getHomeInfo() : Result<HomeInfo>
    suspend fun getWeeklySalesInfo(currentDate : String): Result<WeeklySales>
    suspend fun getYearlySalesInfo(currentDate: String): Result<YearlySales>
}