package com.inha.sellstarter_android.domain.usecase.home

import com.inha.sellstarter_android.domain.model.WeeklySales
import com.inha.sellstarter_android.domain.repository.HomeRepository
import javax.inject.Inject

class LoadWeeklySalesUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke(currentDate: String): Result<WeeklySales> {
        return homeRepository.loadWeeklySalesInfo(currentDate = currentDate)
    }
}