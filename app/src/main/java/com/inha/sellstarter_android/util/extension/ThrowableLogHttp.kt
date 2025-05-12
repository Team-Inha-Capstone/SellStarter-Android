package com.inha.sellstarter_android.util.extension

import android.util.Log
import retrofit2.HttpException

fun Throwable.logHttpError(tag: String = "Hyeon") {
    when (this) {
        is HttpException -> {
            val errorBody = this.response()?.errorBody()?.string()
            Log.e(tag, "HttpException: ${this.code()} ${message()} \n$errorBody")
        }
        else -> Log.e(tag, "Throwable: ${this.message}")
    }
}