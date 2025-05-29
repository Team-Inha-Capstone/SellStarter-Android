package com.inha.sellstarter_android.data.datasource.remote

import com.inha.sellstarter_android.data.model.response.home.HomeInfoResponseDto
import com.inha.sellstarter_android.util.base.BaseResponseDto

interface HomeDataSource {
    suspend fun getHomeInfo() : BaseResponseDto<HomeInfoResponseDto>
}