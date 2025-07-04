package com.inha.sellstarter.data.model.response.analysis

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnalysisReportResponseDto(
    @SerialName("url")
    val url: String,
)
