package com.inha.sellstarter_android.domain.usecase.mypage

import com.inha.sellstarter_android.domain.model.UserInfo
import com.inha.sellstarter_android.domain.repository.MyPageRepository
import javax.inject.Inject

// 하나의 비즈니스 목적을 위한 단위 동작
class LoadUserDetailUseCase @Inject constructor(
    private val myPageRepository: MyPageRepository,
) {
    suspend operator fun invoke(): Result<UserInfo> {
        return myPageRepository.loadUserDetail()
    }
}
