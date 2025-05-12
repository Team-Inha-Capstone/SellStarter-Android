package com.inha.sellstarter_android.domain.usecase.mypage

data class MyPageUseCases(
    val userApiUseCase: UserApiUseCase,
    val userApiUpdateUseCase: UserApiUpdateUseCase,
    val userApiDeleteUseCase: UserApiDeleteUseCase,
    val userInfoUseCase: MyPageUserInfoUseCase
)