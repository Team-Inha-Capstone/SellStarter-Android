package com.inha.sellstarter.util.base

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponseDto<T>(
    val success: Boolean,
    val message: String,
    val data: T,
)
