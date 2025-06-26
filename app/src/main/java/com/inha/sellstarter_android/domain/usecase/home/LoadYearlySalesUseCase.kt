package com.inha.sellstarter_android.domain.usecase.home

import com.inha.sellstarter_android.domain.model.YearlySales
import com.inha.sellstarter_android.domain.repository.HomeRepository
import javax.inject.Inject

class LoadYearlySalesUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke(currentDate: String): Result<YearlySales> {
        return homeRepository.loadYearlySalesInfo(currentDate = currentDate)
    }
}