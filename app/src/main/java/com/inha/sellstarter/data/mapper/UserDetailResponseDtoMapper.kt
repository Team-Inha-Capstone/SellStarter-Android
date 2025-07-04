package com.inha.sellstarter.data.mapper

import com.inha.sellstarter.R
import com.inha.sellstarter.data.model.response.mypage.ApiKeyResponseDto
import com.inha.sellstarter.data.model.response.mypage.UserDetailResponseDto
import com.inha.sellstarter.domain.model.ApiKeys
import com.inha.sellstarter.domain.model.UserInfo

fun UserDetailResponseDto.toDomain(): UserInfo {
    return UserInfo(
        userName = this.userName,
        shoppingCategory = this.shoppingCategory,
        apiKey = this.apiKey.map { it.toDomain() },
    )
}

fun ApiKeyResponseDto.toDomain(): ApiKeys {
    val channelImage =
        when (this.channelId) {
            1 -> R.drawable.ic_naver
            else -> R.drawable.ic_shopify
        }
    return ApiKeys(
        apiId = this.apiId,
        channelId = this.channelId,
        channelName = this.channel,
        channelImage = channelImage,
        key = this.key,
    )
}
