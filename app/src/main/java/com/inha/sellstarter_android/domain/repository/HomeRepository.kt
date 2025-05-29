package com.inha.sellstarter_android.domain.repository

import com.inha.sellstarter_android.domain.model.HomeInfo

interface HomeRepository {
    suspend fun getHomeInfo() : Result<HomeInfo>
}