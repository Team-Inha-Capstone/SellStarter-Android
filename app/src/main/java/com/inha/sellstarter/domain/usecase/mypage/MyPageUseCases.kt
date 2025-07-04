package com.inha.sellstarter.domain.usecase.mypage

data class MyPageUseCases(
    val registerStoreApiKeyUseCase: RegisterStoreApiKeyUseCase,
    val updateStoreApiKeyUseCase: UpdateStoreApiKeyUseCase,
    val removeStoreApiKeyUseCase: RemoveStoreApiKeyUseCase,
    val loadUserDetailUseCase: LoadUserDetailUseCase,
)
