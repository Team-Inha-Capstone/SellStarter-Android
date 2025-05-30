package com.inha.sellstarter_android.data.repository

import com.inha.sellstarter_android.data.datasource.remote.HomeDataSource
import com.inha.sellstarter_android.data.mapper.toDomain
import com.inha.sellstarter_android.domain.model.HomeInfo
import com.inha.sellstarter_android.domain.model.WeeklySales
import com.inha.sellstarter_android.domain.model.YearlySales
import com.inha.sellstarter_android.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeDataSource: HomeDataSource
) : HomeRepository {
    override suspend fun getHomeInfo(): Result<HomeInfo> {
        return runCatching {
            homeDataSource.getHomeInfo().data.toDomain()
        }
    }

    override suspend fun getWeeklySalesInfo(currentDate: String): Result<WeeklySales> {
        return runCatching {
            homeDataSource.getWeeklySalesInfo(currentDate).data.toDomain()
        }
    }

    override suspend fun getYearlySalesInfo(currentDate: String): Result<YearlySales> {
        return runCatching {
            homeDataSource.getYearlySalesInfo(currentDate).data.toDomain()
        }
    }
}