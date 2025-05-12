package com.inha.sellstarter_android.domain.model

import kotlinx.serialization.SerialName

data class ApiKeys(
    val apiId: Int,
    val channelId: Int,
    val channelName: String,
    val channelImage: Int,
    val key: String
)