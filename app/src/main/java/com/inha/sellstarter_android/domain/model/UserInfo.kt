package com.inha.sellstarter_android.domain.model

import com.inha.sellstarter_android.presentation.mypage.component.storemanage.ApiKey

data class UserInfo(
    val userName : String,
    val shoppingCategory : String,
    val apiKey : List<ApiKeys>
)
