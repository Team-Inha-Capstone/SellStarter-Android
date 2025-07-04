package com.inha.sellstarter.data.datasource.remote

import com.inha.sellstarter.data.model.response.home.HomeInfoResponseDto
import com.inha.sellstarter.data.model.response.home.WeeklySalesResponseDto
import com.inha.sellstarter.data.model.response.home.YearlySalesResponseDto
import com.inha.sellstarter.util.base.BaseResponseDto

interface HomeDataSource {
    suspend fun loadHomeDashboard(): BaseResponseDto<HomeInfoResponseDto>

    suspend fun loadWeeklySalesInfo(currentDate: String): BaseResponseDto<WeeklySalesResponseDto>

    suspend fun loadYearlySalesInfo(currentDate: String): BaseResponseDto<YearlySalesResponseDto>
}
