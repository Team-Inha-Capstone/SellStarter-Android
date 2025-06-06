package com.inha.sellstarter_android.di

import com.inha.sellstarter_android.data.service.ChatbotService
import com.inha.sellstarter_android.data.service.DataAnalysisService
import com.inha.sellstarter_android.data.service.HomeService
import com.inha.sellstarter_android.data.service.InventoryService
import com.inha.sellstarter_android.data.service.MyPageService
import com.inha.sellstarter_android.data.service.OrderService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ServiceModule {

    @Singleton
    @Provides
    fun provideMyPageService(
        @NetworkModule.MainRetrofit retrofit: Retrofit
    ): MyPageService =
        retrofit.create(MyPageService::class.java)


    @Singleton
    @Provides
    fun provideChatbotService(
        @NetworkModule.PythonChatbotRetrofit retrofit: Retrofit
    ): ChatbotService =
        retrofit.create(ChatbotService::class.java)

    @Singleton
    @Provides
    fun provideDataAnalysisService(
        @NetworkModule.PythonDataRetrofit retrofit: Retrofit
    ): DataAnalysisService =
        retrofit.create(DataAnalysisService::class.java)

    @Singleton
    @Provides
    fun provideInventoryService(
        @NetworkModule.MainRetrofit retrofit: Retrofit
    ): InventoryService =
        retrofit.create(InventoryService::class.java)

    @Singleton
    @Provides
    fun provideOrderService(
        @NetworkModule.MainRetrofit retrofit: Retrofit
    ): OrderService =
        retrofit.create(OrderService::class.java)

    @Singleton
    @Provides
    fun provideHomeService(
        @NetworkModule.MainRetrofit retrofit: Retrofit
    ) : HomeService =
        retrofit.create(HomeService::class.java)


}