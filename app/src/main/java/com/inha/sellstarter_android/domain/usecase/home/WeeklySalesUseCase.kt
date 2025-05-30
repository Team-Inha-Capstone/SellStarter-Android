package com.inha.sellstarter_android.domain.usecase.home

import com.inha.sellstarter_android.domain.model.WeeklySales
import com.inha.sellstarter_android.domain.repository.HomeRepository
import javax.inject.Inject

class WeeklySalesUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    suspend fun invoke(currentDate: String): Result<WeeklySales> {
        return homeRepository.getWeeklySalesInfo(currentDate = currentDate)
    }
}