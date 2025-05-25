package com.inha.sellstarter_android.di

import com.inha.sellstarter_android.data.datasource.remote.ChatbotDataSource
import com.inha.sellstarter_android.data.datasource.remote.MyPageDataSource
import com.inha.sellstarter_android.data.datasource.remote.impl.ChatbotDataSourceImpl
import com.inha.sellstarter_android.data.datasource.remote.impl.MyPageDataSourceImpl
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
}