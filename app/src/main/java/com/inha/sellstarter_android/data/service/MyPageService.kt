package com.inha.sellstarter_android.data.service

import com.inha.sellstarter_android.data.model.request.mypage.UserApiDeleteRequestDto
import com.inha.sellstarter_android.data.model.request.mypage.UserApiRequestDto
import com.inha.sellstarter_android.data.model.request.mypage.UserApiUpdateRequest
import com.inha.sellstarter_android.data.model.response.mypage.UserDetailResponseDto
import com.inha.sellstarter_android.util.base.BaseResponseDto
import retrofit2.http.GET
import retrofit2.http.POST
import com.inha.sellstarter_android.data.util.Constants.API
import com.inha.sellstarter_android.data.util.Constants.APP
import com.inha.sellstarter_android.data.util.Constants.CREATE
import com.inha.sellstarter_android.data.util.Constants.DELETE
import com.inha.sellstarter_android.data.util.Constants.UPDATE
import com.inha.sellstarter_android.data.util.Constants.USER
import retrofit2.http.Body
import retrofit2.http.Path

interface MyPageService {
    @GET("$API/$APP/$USER/{userId}")
    suspend fun getUserDetail(
        @Path("userId") userId: Int
    ): BaseResponseDto<UserDetailResponseDto>

    @POST("$API/$APP/$USER/{userId}/$DELETE")
    suspend fun deleteUserApi(
        @Path("userId") userId: Int,
        @Body userApiDeleteRequestDto: UserApiDeleteRequestDto
    ): BaseResponseDto<UserDetailResponseDto>

    @POST("$API/$APP/$USER/{userId}/$UPDATE")
    suspend fun updateUserApi(
        @Path("userId") userId: Int,
        @Body userApiUpdateRequest: UserApiUpdateRequest
    ): BaseResponseDto<UserDetailResponseDto>

    @POST("$API/$APP/$USER/{userId}/$CREATE")
    suspend fun createUserApi(
        @Path("userId") userId: Int,
        @Body userApiRequestDto: UserApiRequestDto
    ): BaseResponseDto<UserDetailResponseDto>
}