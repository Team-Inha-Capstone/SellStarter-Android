package com.inha.sellstarter.data.service

import com.inha.sellstarter.data.model.request.mypage.UserApiDeleteRequestDto
import com.inha.sellstarter.data.model.request.mypage.UserApiRequestDto
import com.inha.sellstarter.data.model.request.mypage.UserApiUpdateRequest
import com.inha.sellstarter.data.model.response.mypage.UserDetailResponseDto
import com.inha.sellstarter.data.util.Constants.API
import com.inha.sellstarter.data.util.Constants.APP
import com.inha.sellstarter.data.util.Constants.CREATE
import com.inha.sellstarter.data.util.Constants.DELETE
import com.inha.sellstarter.data.util.Constants.UPDATE
import com.inha.sellstarter.data.util.Constants.USER
import com.inha.sellstarter.util.base.BaseResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MyPageService {
    @GET("$API/$APP/$USER/{userId}")
    suspend fun loadUserDetail(
        @Path("userId") userId: Int,
    ): BaseResponseDto<UserDetailResponseDto>

    @POST("$API/$APP/$USER/{userId}/$DELETE")
    suspend fun removeStoreApiKey(
        @Path("userId") userId: Int,
        @Body userApiDeleteRequestDto: UserApiDeleteRequestDto,
    ): BaseResponseDto<UserDetailResponseDto>

    @POST("$API/$APP/$USER/{userId}/$UPDATE")
    suspend fun updateStoreApiKey(
        @Path("userId") userId: Int,
        @Body userApiUpdateRequest: UserApiUpdateRequest,
    ): BaseResponseDto<UserDetailResponseDto>

    @POST("$API/$APP/$USER/{userId}/$CREATE")
    suspend fun registerStoreApiKey(
        @Path("userId") userId: Int,
        @Body userApiRequestDto: UserApiRequestDto,
    ): BaseResponseDto<UserDetailResponseDto>
}
