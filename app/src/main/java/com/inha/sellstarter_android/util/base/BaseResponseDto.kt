package com.inha.sellstarter_android.util.base

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponseDto<T>(
    val success: Boolean,
    val message: String,
    val data: T
)