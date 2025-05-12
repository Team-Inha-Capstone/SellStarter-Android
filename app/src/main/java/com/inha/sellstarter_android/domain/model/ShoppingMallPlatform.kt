package com.inha.sellstarter_android.domain.model

import com.inha.sellstarter_android.R

enum class ShoppingMallPlatform(
    val channelId: Int,          // 서버 통신용 ID
    val displayName: String,     // UI 표시용 이름
    val displayImage: Int        // drawable 리소스
) {
    NAVER(1, "네이버 스마트 스토어", R.drawable.ic_naver),
    SHOPIFY(2, "쇼피파이", R.drawable.ic_shopify);

    companion object {
        fun fromChannelId(channelId: Int): ShoppingMallPlatform {
            return values().firstOrNull { it.channelId == channelId } ?: NAVER
        }
    }
}