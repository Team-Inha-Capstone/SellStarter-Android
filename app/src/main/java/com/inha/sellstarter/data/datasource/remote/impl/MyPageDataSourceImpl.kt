package com.inha.sellstarter.data.datasource.remote.impl

import com.inha.sellstarter.data.datasource.remote.MyPageDataSource
import com.inha.sellstarter.data.model.request.mypage.UserApiDeleteRequestDto
import com.inha.sellstarter.data.model.request.mypage.UserApiRequestDto
import com.inha.sellstarter.data.model.request.mypage.UserApiUpdateRequest
import com.inha.sellstarter.data.model.response.mypage.UserDetailResponseDto
import com.inha.sellstarter.data.service.MyPageService
import com.inha.sellstarter.util.base.BaseResponseDto
import javax.inject.Inject

// 실제 서버와 통신하는 datasource 구현
class MyPageDataSourceImpl
    @Inject
    constructor(
        private val myPageService: MyPageService,
    ) : MyPageDataSource {
        override suspend fun loadUserDetail(): BaseResponseDto<UserDetailResponseDto> {
            return myPageService.loadUserDetail(userId = 4)
        }

        override suspend fun registerUserApi(userApiRequestDto: UserApiRequestDto): BaseResponseDto<UserDetailResponseDto> {
            return myPageService.registerStoreApiKey(userId = 4, userApiRequestDto)
        }

        override suspend fun removeUserApi(userApiDeleteRequestDto: UserApiDeleteRequestDto): BaseResponseDto<UserDetailResponseDto> {
            return myPageService.removeStoreApiKey(userId = 4, userApiDeleteRequestDto)
        }

        override suspend fun updateUserApi(userApiUpdateRequest: UserApiUpdateRequest): BaseResponseDto<UserDetailResponseDto> {
            return myPageService.updateStoreApiKey(userId = 4, userApiUpdateRequest)
        }
    }
