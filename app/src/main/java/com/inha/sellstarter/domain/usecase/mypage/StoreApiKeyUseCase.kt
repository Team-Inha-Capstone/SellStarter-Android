package com.inha.sellstarter.domain.usecase.mypage

import com.inha.sellstarter.data.model.request.mypage.UserApiDeleteRequestDto
import com.inha.sellstarter.data.model.request.mypage.UserApiRequestDto
import com.inha.sellstarter.data.model.request.mypage.UserApiUpdateRequest
import com.inha.sellstarter.domain.model.UserInfo
import com.inha.sellstarter.domain.repository.MyPageRepository
import javax.inject.Inject

class RegisterStoreApiKeyUseCase
    @Inject
    constructor(
        private val myPageRepository: MyPageRepository,
    ) {
        suspend operator fun invoke(userApiRequestDto: UserApiRequestDto): Result<UserInfo> {
            return myPageRepository.registerStoreApiKey(userApiRequestDto)
        }
    }

class RemoveStoreApiKeyUseCase
    @Inject
    constructor(
        private val myPageRepository: MyPageRepository,
    ) {
        suspend operator fun invoke(userApiDeleteRequestDto: UserApiDeleteRequestDto): Result<UserInfo> {
            return myPageRepository.removeStoreApiKey(userApiDeleteRequestDto)
        }
    }

class UpdateStoreApiKeyUseCase
    @Inject
    constructor(
        private val myPageRepository: MyPageRepository,
    ) {
        suspend operator fun invoke(userApiUpdateRequestDto: UserApiUpdateRequest): Result<UserInfo> {
            return myPageRepository.updateStoreApiKey(userApiUpdateRequestDto)
        }
    }
