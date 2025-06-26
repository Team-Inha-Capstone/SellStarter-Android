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
    override suspend fun loadHomeDashboard(): Result<HomeInfo> {
        return runCatching {
            homeDataSource.loadHomeDashboard().data.toDomain()
        }
    }

    override suspend fun loadWeeklySalesInfo(currentDate: String): Result<WeeklySales> {
        return runCatching {
            homeDataSource.loadWeeklySalesInfo(currentDate).data.toDomain()
        }
    }

    override suspend fun loadYearlySalesInfo(currentDate: String): Result<YearlySales> {
        return runCatching {
            homeDataSource.loadYearlySalesInfo(currentDate).data.toDomain()
        }
    }
}