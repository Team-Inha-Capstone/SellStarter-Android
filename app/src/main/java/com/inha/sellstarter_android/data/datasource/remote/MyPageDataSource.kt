package com.inha.sellstarter_android.data.datasource.remote

import com.inha.sellstarter_android.data.model.request.mypage.UserApiDeleteRequestDto
import com.inha.sellstarter_android.data.model.request.mypage.UserApiRequestDto
import com.inha.sellstarter_android.data.model.request.mypage.UserApiUpdateRequest
import com.inha.sellstarter_android.data.model.response.mypage.UserDetailResponseDto
import com.inha.sellstarter_android.util.base.BaseResponseDto

interface MyPageDataSource {
    suspend fun getUserDetail(): BaseResponseDto<UserDetailResponseDto>
    suspend fun postUserApi(userApiRequestDto: UserApiRequestDto): BaseResponseDto<UserDetailResponseDto>
    suspend fun deleteUserApi(userApiDeleteRequestDto: UserApiDeleteRequestDto): BaseResponseDto<UserDetailResponseDto>
    suspend fun updateUserApi(userApiUpdateRequest: UserApiUpdateRequest): BaseResponseDto<UserDetailResponseDto>
}