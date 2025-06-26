package com.inha.sellstarter_android.domain.usecase.mypage

import com.inha.sellstarter_android.data.model.request.mypage.UserApiUpdateRequest
import com.inha.sellstarter_android.domain.model.UserInfo
import com.inha.sellstarter_android.domain.repository.MyPageRepository
import javax.inject.Inject

class UserApiUpdateUseCase @Inject constructor(
    private val myPageRepository: MyPageRepository,
) {
    suspend fun invoke(userApiUpdateRequestDto: UserApiUpdateRequest): Result<UserInfo> {
        return myPageRepository.updateStoreApiKey(userApiUpdateRequestDto)
    }
}