package com.inha.sellstarter.domain.usecase.dataanalysis

import com.inha.sellstarter.domain.repository.DataAnalysisRepository
import javax.inject.Inject

class LoadDataReportUseCase
    @Inject
    constructor(
        private val dataAnalysisRepository: DataAnalysisRepository,
    ) {
        suspend operator fun invoke(): Result<String> {
            return dataAnalysisRepository.loadAnalysisReport()
        }
    }
