package com.inha.sellstarter_android.di

import com.inha.sellstarter_android.domain.repository.MyPageRepository
import com.inha.sellstarter_android.domain.usecase.mypage.MyPageUseCases
import com.inha.sellstarter_android.domain.usecase.mypage.MyPageUserInfoUseCase
import com.inha.sellstarter_android.domain.usecase.mypage.UserApiDeleteUseCase
import com.inha.sellstarter_android.domain.usecase.mypage.UserApiUpdateUseCase
import com.inha.sellstarter_android.domain.usecase.mypage.UserApiUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideMyPageUseCases(
        myPageRepository: MyPageRepository
    ): MyPageUseCases {
        return MyPageUseCases(
            userApiUseCase = UserApiUseCase(myPageRepository),
            userApiUpdateUseCase = UserApiUpdateUseCase(myPageRepository),
            userApiDeleteUseCase = UserApiDeleteUseCase(myPageRepository),
            userInfoUseCase = MyPageUserInfoUseCase(myPageRepository)
        )
    }

}