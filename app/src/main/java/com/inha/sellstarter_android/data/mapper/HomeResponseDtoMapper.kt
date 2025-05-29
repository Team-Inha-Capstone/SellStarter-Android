package com.inha.sellstarter_android.data.mapper

import com.inha.sellstarter_android.data.model.response.home.HomeInfoResponseDto
import com.inha.sellstarter_android.domain.model.HomeInfo

fun HomeInfoResponseDto.toDomain(): HomeInfo {
    return HomeInfo(
        shopName = this.shopName,
        newOrder = this.newOrder,
        pickingCompleted = this.pickingCompleted,
        shippingCompleted = this.shippingCompleted,
        cancelRequest = this.cancelRequest,
        returnRequest = this.returnRequest,
        analysisSubscribed = this.analysisSubscribed
    )
}