package com.inha.sellstarter_android.di

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
        //repository: UserRepository
    ) {

    }
//    : MyPageUseCases {
//        return MyPageUseCases(
//            updateNickname = UpdateNicknameUseCase(repository),
//            getUserProfile = GetUserProfileUseCase(repository),
//            logout = LogoutUseCase(repository)
//        )
//    }
}