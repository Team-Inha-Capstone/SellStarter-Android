package com.inha.sellstarter.util.base

sealed class UiState<out T> {
    object Loading : UiState<Nothing>()

    data class Success<T>(
        val data: T,
    ) : UiState<T>()

    data class Failure<T>(
        val message: String?,
        val code: Int? = null,
        val data: T? = null,
    ) : UiState<T>()
}
