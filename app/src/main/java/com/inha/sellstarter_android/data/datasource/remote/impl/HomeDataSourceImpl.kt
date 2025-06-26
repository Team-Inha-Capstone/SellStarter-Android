package com.inha.sellstarter_android.data.datasource.remote.impl

import com.inha.sellstarter_android.data.datasource.remote.HomeDataSource
import com.inha.sellstarter_android.data.model.response.home.HomeInfoResponseDto
import com.inha.sellstarter_android.data.model.response.home.WeeklySalesResponseDto
import com.inha.sellstarter_android.data.model.response.home.YearlySalesResponseDto
import com.inha.sellstarter_android.data.service.HomeService
import com.inha.sellstarter_android.util.base.BaseResponseDto
import javax.inject.Inject

class HomeDataSourceImpl @Inject constructor(
    private val homeService: HomeService
) : HomeDataSource {

    override suspend fun loadHomeDashboard(): BaseResponseDto<HomeInfoResponseDto> {
        return homeService.loadHomeDashboard()
    }

    override suspend fun loadWeeklySalesInfo(
        currentDate: String
    ): BaseResponseDto<WeeklySalesResponseDto> {
        return homeService.loadWeeklySalesInfo(currentDate = currentDate)
    }

    override suspend fun loadYearlySalesInfo(
        currentDate: String
    ): BaseResponseDto<YearlySalesResponseDto> {
        return homeService.loadYearlySalesInfo(currentDate = currentDate)
    }
}