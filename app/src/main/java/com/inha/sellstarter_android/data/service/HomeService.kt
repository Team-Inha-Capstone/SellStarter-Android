package com.inha.sellstarter_android.data.service

import com.inha.sellstarter_android.data.model.response.home.HomeInfoResponseDto
import com.inha.sellstarter_android.data.model.response.home.WeeklySalesResponseDto
import com.inha.sellstarter_android.data.model.response.home.YearlySalesResponseDto
import com.inha.sellstarter_android.data.util.Constants.API
import com.inha.sellstarter_android.data.util.Constants.APP
import com.inha.sellstarter_android.data.util.Constants.HOME
import com.inha.sellstarter_android.util.base.BaseResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HomeService {
    @GET("$API/$APP/$HOME/{userId}")
    suspend fun loadHomeDashboard(
        @Path("userId") userId: Int = 4,
    ): BaseResponseDto<HomeInfoResponseDto>

    @GET("$API/$APP/$HOME/{userId}/weekly-sales")
    suspend fun loadWeeklySalesInfo(
        @Path("userId") userId: Int = 4,
        @Query("currentDate") currentDate: String
    ): BaseResponseDto<WeeklySalesResponseDto>

    @GET("$API/$APP/$HOME/{userId}/yearly-sales")
    suspend fun loadYearlySalesInfo(
        @Path("userId") userId: Int = 4,
        @Query("currentDate") currentDate: String
    ): BaseResponseDto<YearlySalesResponseDto>
}