package com.inha.sellstarter_android.domain.usecase.home

import com.inha.sellstarter_android.domain.model.HomeInfo
import com.inha.sellstarter_android.domain.repository.HomeRepository
import javax.inject.Inject

class HomeInfoUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    suspend fun invoke() : Result<HomeInfo> {
        return homeRepository.loadHomeDashboard()
    }
}