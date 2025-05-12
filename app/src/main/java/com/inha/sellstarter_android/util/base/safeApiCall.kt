package com.inha.sellstarter_android.util.base

suspend fun <T> safeApiCall(
    onStart: (() -> Unit)? = null,
    onError: ((Throwable) -> Unit)? = null,
    apiCall: suspend () -> Result<T>
): UiState<T> {
    return try {
        onStart?.invoke()
        apiCall().fold(
            onSuccess = { UiState.Success(it) },
            onFailure = { throwable ->
                onError?.invoke(throwable)
                UiState.Failure(throwable.message)
            }
        )
    } catch (e: Exception) {
        onError?.invoke(e)
        UiState.Failure(e.message)
    }
}