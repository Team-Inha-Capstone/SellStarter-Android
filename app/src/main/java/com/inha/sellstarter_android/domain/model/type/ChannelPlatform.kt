package com.inha.sellstarter_android.domain.model.type

import androidx.annotation.DrawableRes
import com.inha.sellstarter_android.R

enum class ChannelPlatform(
    val channelId: Int,
    val displayName: String,
    @DrawableRes
    val displayImage: Int
) {

    NAVER(1, "네이버 스마트 스토어", R.drawable.ic_naver),
    SHOPIFY(2, "쇼피파이", R.drawable.ic_shopify);

    companion object {
        fun fromChannelId(channelId: Int): ChannelPlatform {
            return values().firstOrNull { it.channelId == channelId } ?: NAVER
        }
    }
}