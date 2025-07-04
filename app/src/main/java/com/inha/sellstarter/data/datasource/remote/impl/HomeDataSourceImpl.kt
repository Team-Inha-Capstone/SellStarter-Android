package com.inha.sellstarter.data.datasource.remote.impl

import com.inha.sellstarter.data.datasource.remote.HomeDataSource
import com.inha.sellstarter.data.model.response.home.HomeInfoResponseDto
import com.inha.sellstarter.data.model.response.home.WeeklySalesResponseDto
import com.inha.sellstarter.data.model.response.home.YearlySalesResponseDto
import com.inha.sellstarter.data.service.HomeService
import com.inha.sellstarter.util.base.BaseResponseDto
import javax.inject.Inject

class HomeDataSourceImpl
    @Inject
    constructor(
        private val homeService: HomeService,
    ) : HomeDataSource {
        override suspend fun loadHomeDashboard(): BaseResponseDto<HomeInfoResponseDto> {
            return homeService.loadHomeDashboard()
        }

        override suspend fun loadWeeklySalesInfo(currentDate: String): BaseResponseDto<WeeklySalesResponseDto> {
            return homeService.loadWeeklySalesInfo(currentDate = currentDate)
        }

        override suspend fun loadYearlySalesInfo(currentDate: String): BaseResponseDto<YearlySalesResponseDto> {
            return homeService.loadYearlySalesInfo(currentDate = currentDate)
        }
    }
