package com.inha.sellstarter_android.data.datasource.remote.impl

import com.inha.sellstarter_android.data.datasource.remote.MyPageDataSource
import com.inha.sellstarter_android.data.model.request.mypage.UserApiDeleteRequestDto
import com.inha.sellstarter_android.data.model.request.mypage.UserApiRequestDto
import com.inha.sellstarter_android.data.model.request.mypage.UserApiUpdateRequest
import com.inha.sellstarter_android.data.model.response.mypage.UserDetailResponseDto
import com.inha.sellstarter_android.data.service.MyPageService
import com.inha.sellstarter_android.domain.model.UserInfo
import com.inha.sellstarter_android.util.base.BaseResponseDto
import javax.inject.Inject

// 실제 서버와 통신하는 datasource 구현
class MyPageDataSourceImpl @Inject constructor(
    private val myPageService: MyPageService,
) : MyPageDataSource {
    override suspend fun getUserDetail(): BaseResponseDto<UserDetailResponseDto> {
        return myPageService.getUserDetail(userId = 4)
    }

    override suspend fun postUserApi(userApiRequestDto: UserApiRequestDto): BaseResponseDto<UserDetailResponseDto> {
        return myPageService.createUserApi(userId = 4, userApiRequestDto)
    }

    override suspend fun deleteUserApi(userApiDeleteRequestDto: UserApiDeleteRequestDto): BaseResponseDto<UserDetailResponseDto> {
        return myPageService.deleteUserApi(userId = 4, userApiDeleteRequestDto)
    }

    override suspend fun updateUserApi(userApiUpdateRequest: UserApiUpdateRequest): BaseResponseDto<UserDetailResponseDto> {
        return myPageService.updateUserApi(userId = 4, userApiUpdateRequest)
    }
}