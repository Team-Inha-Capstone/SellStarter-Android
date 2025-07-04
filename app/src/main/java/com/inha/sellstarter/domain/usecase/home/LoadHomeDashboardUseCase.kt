package com.inha.sellstarter.domain.usecase.home

import com.inha.sellstarter.domain.model.HomeInfo
import com.inha.sellstarter.domain.repository.HomeRepository
import javax.inject.Inject

class LoadHomeDashboardUseCase
    @Inject
    constructor(
        private val homeRepository: HomeRepository,
    ) {
        suspend operator fun invoke(): Result<HomeInfo> {
            return homeRepository.loadHomeDashboard()
        }
    }
