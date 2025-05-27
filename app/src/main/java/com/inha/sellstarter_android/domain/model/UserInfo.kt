package com.inha.sellstarter_android.domain.model


data class UserInfo(
    val userName : String,
    val shoppingCategory : String,
    val apiKey : List<ApiKeys>
)
