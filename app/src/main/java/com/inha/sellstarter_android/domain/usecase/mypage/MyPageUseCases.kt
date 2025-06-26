package com.inha.sellstarter_android.domain.usecase.mypage

data class MyPageUseCases(
    val registerStoreApiKeyUseCase: RegisterStoreApiKeyUseCase,
    val updateStoreApiKeyUseCase: UpdateStoreApiKeyUseCase,
    val removeStoreApiKeyUseCase: RemoveStoreApiKeyUseCase,
    val loadUserDetailUseCase: LoadUserDetailUseCase
)