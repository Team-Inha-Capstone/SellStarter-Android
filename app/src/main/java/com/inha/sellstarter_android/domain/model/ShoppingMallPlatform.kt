package com.inha.sellstarter_android.domain.model

import com.inha.sellstarter_android.R

enum class ShoppingMallPlatform(val displayName: String, val displayImage: Int) {
    NAVER("네이버 스마트 스토어", R.drawable.ic_naver),
    SHOPIFY("쇼피파이", R.drawable.ic_shopify)
}