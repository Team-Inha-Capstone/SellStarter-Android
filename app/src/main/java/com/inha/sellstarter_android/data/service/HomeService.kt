package com.inha.sellstarter_android.data.service

import com.inha.sellstarter_android.data.model.response.home.HomeInfoResponseDto
import com.inha.sellstarter_android.data.util.Constants.API
import com.inha.sellstarter_android.data.util.Constants.APP
import com.inha.sellstarter_android.data.util.Constants.HOME
import com.inha.sellstarter_android.util.base.BaseResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeService {
    @GET("$API/$APP/$HOME/{userId}")
    suspend fun getHomeInfo(
        @Path("userId") userId : Int = 4,
    ) : BaseResponseDto<HomeInfoResponseDto>
}