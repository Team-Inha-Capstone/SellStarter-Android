package com.inha.sellstarter_android.data.datasource.remote.impl

import com.inha.sellstarter_android.data.datasource.remote.HomeDataSource
import com.inha.sellstarter_android.data.model.response.home.HomeInfoResponseDto
import com.inha.sellstarter_android.data.service.HomeService
import com.inha.sellstarter_android.util.base.BaseResponseDto
import javax.inject.Inject

class HomeDataSourceImpl @Inject constructor(
    private val homeService: HomeService
) : HomeDataSource {

    override suspend fun getHomeInfo(): BaseResponseDto<HomeInfoResponseDto> {
       return homeService.getHomeInfo()
    }
}