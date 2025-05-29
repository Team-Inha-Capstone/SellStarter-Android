package com.inha.sellstarter_android.di

import com.inha.sellstarter_android.data.repository.ChatbotRepositoryImpl
import com.inha.sellstarter_android.data.repository.HomeRepositoryImpl
import com.inha.sellstarter_android.data.repository.InventoryRepositoryImpl
import com.inha.sellstarter_android.data.repository.MyPageRepositoryImpl
import com.inha.sellstarter_android.domain.repository.ChatbotRepository
import com.inha.sellstarter_android.domain.repository.HomeRepository
import com.inha.sellstarter_android.domain.repository.InventoryRepository
import com.inha.sellstarter_android.domain.repository.MyPageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindMyPageRepository(myPageRepositoryImpl: MyPageRepositoryImpl): MyPageRepository

    @Binds
    @Singleton
    abstract fun bindChatbotRepository(chatbotRepositoryImpl: ChatbotRepositoryImpl): ChatbotRepository

    @Binds
    @Singleton
    abstract fun bindInventoryRepository (inventoryRepositoryImpl: InventoryRepositoryImpl) : InventoryRepository

    @Binds
    @Singleton
    abstract fun bindHomeRepository(homeRepositoryImpl: HomeRepositoryImpl) : HomeRepository

}