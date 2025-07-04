package com.inha.sellstarter.domain.repository

import com.inha.sellstarter.data.model.request.mypage.UserApiDeleteRequestDto
import com.inha.sellstarter.data.model.request.mypage.UserApiRequestDto
import com.inha.sellstarter.data.model.request.mypage.UserApiUpdateRequest
import com.inha.sellstarter.domain.model.UserInfo

interface MyPageRepository {
    suspend fun loadUserDetail(): Result<UserInfo>

    suspend fun registerStoreApiKey(userApiRequestDto: UserApiRequestDto): Result<UserInfo>

    suspend fun removeStoreApiKey(userApiDeleteRequestDto: UserApiDeleteRequestDto): Result<UserInfo>

    suspend fun updateStoreApiKey(userApiUpdateRequest: UserApiUpdateRequest): Result<UserInfo>
}
