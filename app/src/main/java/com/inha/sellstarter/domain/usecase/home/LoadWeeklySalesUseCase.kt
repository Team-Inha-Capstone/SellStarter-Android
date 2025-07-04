package com.inha.sellstarter.domain.usecase.home

import com.inha.sellstarter.domain.model.WeeklySales
import com.inha.sellstarter.domain.repository.HomeRepository
import javax.inject.Inject

class LoadWeeklySalesUseCase
    @Inject
    constructor(
        private val homeRepository: HomeRepository,
    ) {
        suspend operator fun invoke(currentDate: String): Result<WeeklySales> {
            return homeRepository.loadWeeklySalesInfo(currentDate = currentDate)
        }
    }
