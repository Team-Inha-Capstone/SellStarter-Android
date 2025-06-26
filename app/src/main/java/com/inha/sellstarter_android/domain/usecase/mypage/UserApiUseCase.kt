package com.inha.sellstarter_android.domain.usecase.mypage

import com.inha.sellstarter_android.data.model.request.mypage.UserApiRequestDto
import com.inha.sellstarter_android.domain.model.UserInfo
import com.inha.sellstarter_android.domain.repository.MyPageRepository
import javax.inject.Inject

class UserApiUseCase  @Inject constructor(
    private val myPageRepository: MyPageRepository,
) {
    suspend fun invoke(userApiRequestDto: UserApiRequestDto): Result<UserInfo> {
        return myPageRepository.registerStoreApiKey(userApiRequestDto)
    }
}