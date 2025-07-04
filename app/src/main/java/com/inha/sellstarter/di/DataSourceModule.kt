package com.inha.sellstarter.di

import com.inha.sellstarter.data.datasource.remote.ChatbotDataSource
import com.inha.sellstarter.data.datasource.remote.DataAnalysisDataSource
import com.inha.sellstarter.data.datasource.remote.HomeDataSource
import com.inha.sellstarter.data.datasource.remote.InventoryDataSource
import com.inha.sellstarter.data.datasource.remote.MyPageDataSource
import com.inha.sellstarter.data.datasource.remote.OrderDataSource
import com.inha.sellstarter.data.datasource.remote.impl.ChatbotDataSourceImpl
import com.inha.sellstarter.data.datasource.remote.impl.DataAnalysisDataSourceImpl
import com.inha.sellstarter.data.datasource.remote.impl.HomeDataSourceImpl
import com.inha.sellstarter.data.datasource.remote.impl.InventoryDataSourceImpl
import com.inha.sellstarter.data.datasource.remote.impl.MyPageDataSourceImpl
import com.inha.sellstarter.data.datasource.remote.impl.OrderDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindMyPageRemoteDataSource(myPageRemoteDataSourceImpl: MyPageDataSourceImpl): MyPageDataSource

    @Binds
    @Singleton
    abstract fun bindChatbotRemoteDataSource(chatbotRemoteDataSourceImpl: ChatbotDataSourceImpl): ChatbotDataSource

    @Binds
    @Singleton
    abstract fun bindInventoryRemoteDataSource(inventoryRemoteDataSourceImpl: InventoryDataSourceImpl): InventoryDataSource

    @Binds
    @Singleton
    abstract fun bindOrderRemoteDataSource(orderRemoteDataSourceImpl: OrderDataSourceImpl): OrderDataSource

    @Binds
    @Singleton
    abstract fun bindHomeRemoteDataSource(homeDataSourceImpl: HomeDataSourceImpl): HomeDataSource

    @Binds
    @Singleton
    abstract fun bindDataRemoteDataSource(dataAnalysisDataSourceImpl: DataAnalysisDataSourceImpl): DataAnalysisDataSource
}
