package com.inha.sellstarter_android.data.datasource.remote

import com.inha.sellstarter_android.data.model.response.home.HomeInfoResponseDto
import com.inha.sellstarter_android.data.model.response.home.WeeklySalesResponseDto
import com.inha.sellstarter_android.data.model.response.home.YearlySalesResponseDto
import com.inha.sellstarter_android.util.base.BaseResponseDto

interface HomeDataSource {
    suspend fun getHomeInfo(): BaseResponseDto<HomeInfoResponseDto>
    suspend fun getWeeklySalesInfo(currentDate : String): BaseResponseDto<WeeklySalesResponseDto>
    suspend fun getYearlySalesInfo(currentDate: String): BaseResponseDto<YearlySalesResponseDto>
}