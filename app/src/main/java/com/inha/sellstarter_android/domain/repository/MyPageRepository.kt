package com.inha.sellstarter_android.domain.repository

import com.inha.sellstarter_android.data.model.request.mypage.UserApiDeleteRequestDto
import com.inha.sellstarter_android.data.model.request.mypage.UserApiRequestDto
import com.inha.sellstarter_android.data.model.request.mypage.UserApiUpdateRequest
import com.inha.sellstarter_android.domain.model.UserInfo

interface MyPageRepository {
    suspend fun getUserDetail(): Result<UserInfo>
    suspend fun deleteUserApi(userApiDeleteRequestDto: UserApiDeleteRequestDto): Result<UserInfo>
    suspend fun updateUserApi(userApiUpdateRequest: UserApiUpdateRequest): Result<UserInfo>
    suspend fun createUserApi(userApiRequestDto: UserApiRequestDto): Result<UserInfo>
}