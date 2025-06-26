package com.inha.sellstarter_android.domain.usecase.mypage

import com.inha.sellstarter_android.data.model.request.mypage.UserApiDeleteRequestDto
import com.inha.sellstarter_android.domain.model.UserInfo
import com.inha.sellstarter_android.domain.repository.MyPageRepository
import javax.inject.Inject

class UserApiDeleteUseCase @Inject constructor(
    private val myPageRepository: MyPageRepository,
) {
    suspend fun invoke(userApiDeleteRequestDto: UserApiDeleteRequestDto): Result<UserInfo> {
        return myPageRepository.removeStoreApiKey(userApiDeleteRequestDto)
    }
}