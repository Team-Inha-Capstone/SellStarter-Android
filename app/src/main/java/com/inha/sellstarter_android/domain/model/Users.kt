package com.inha.sellstarter_android.domain.model

import com.inha.sellstarter_android.domain.model.type.ShoppingMallType

data class Users (
    val id : Int,
    val storeName : String,
    val category : ShoppingMallType
)

data class UserInfo(
    val userName : String,
    val shoppingCategory : String,
    val apiKey : List<ApiKeys>
)

data class ApiKeys(
    val apiId: Int,
    val channelId: Int,
    val channelName: String,
    val channelImage: Int,
    val key: String
)