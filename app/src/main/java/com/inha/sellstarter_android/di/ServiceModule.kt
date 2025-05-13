package com.inha.sellstarter_android.di

import com.inha.sellstarter_android.data.service.ChatbotService
import com.inha.sellstarter_android.data.service.MyPageService
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
        @NetworkModule.PythonRetrofit retrofit: Retrofit
    ): ChatbotService =
        retrofit.create(ChatbotService::class.java)
}