package com.inha.sellstarter.data.datasource.remote

import com.inha.sellstarter.data.model.request.mypage.UserApiDeleteRequestDto
import com.inha.sellstarter.data.model.request.mypage.UserApiRequestDto
import com.inha.sellstarter.data.model.request.mypage.UserApiUpdateRequest
import com.inha.sellstarter.data.model.response.mypage.UserDetailResponseDto
import com.inha.sellstarter.util.base.BaseResponseDto

interface MyPageDataSource {
    suspend fun loadUserDetail(): BaseResponseDto<UserDetailResponseDto>

    suspend fun registerUserApi(userApiRequestDto: UserApiRequestDto): BaseResponseDto<UserDetailResponseDto>

    suspend fun removeUserApi(userApiDeleteRequestDto: UserApiDeleteRequestDto): BaseResponseDto<UserDetailResponseDto>

    suspend fun updateUserApi(userApiUpdateRequest: UserApiUpdateRequest): BaseResponseDto<UserDetailResponseDto>
}
