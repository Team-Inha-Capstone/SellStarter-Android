package com.inha.sellstarter_android.di

import com.inha.sellstarter_android.domain.repository.ChatbotRepository
import com.inha.sellstarter_android.domain.repository.InventoryRepository
import com.inha.sellstarter_android.domain.repository.MyPageRepository
import com.inha.sellstarter_android.domain.usecase.chatbot.ChatbotEndUseCase
import com.inha.sellstarter_android.domain.usecase.chatbot.ChatbotMessageUseCase
import com.inha.sellstarter_android.domain.usecase.chatbot.ChatbotStartUseCase
import com.inha.sellstarter_android.domain.usecase.chatbot.ChatbotUseCases
import com.inha.sellstarter_android.domain.usecase.inventory.InventoryDetailUseCase
import com.inha.sellstarter_android.domain.usecase.inventory.InventoryEditCountUseCase
import com.inha.sellstarter_android.domain.usecase.inventory.InventoryListUseCase
import com.inha.sellstarter_android.domain.usecase.inventory.InventoryRegisterUseCase
import com.inha.sellstarter_android.domain.usecase.inventory.InventorySearchUseCase
import com.inha.sellstarter_android.domain.usecase.inventory.InventoryUseCases
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

    @Provides
    @Singleton
    fun provideChatbotUseCases(
        chatbotRepository: ChatbotRepository
    ): ChatbotUseCases {
        return ChatbotUseCases(
            chatStartUseCase = ChatbotStartUseCase(chatbotRepository),
            chatbotMessageUseCase = ChatbotMessageUseCase(chatbotRepository),
            chatEndUseCase = ChatbotEndUseCase(chatbotRepository)
        )
    }

    @Provides
    @Singleton
    fun provideInventoryUseCase(
        inventoryRepository: InventoryRepository
    ) : InventoryUseCases {
        return InventoryUseCases(
            inventoryListUseCase = InventoryListUseCase(inventoryRepository),
            inventoryDetailUseCase = InventoryDetailUseCase(inventoryRepository),
            inventorySearchUseCase = InventorySearchUseCase(inventoryRepository),
            inventoryEditCountUseCase = InventoryEditCountUseCase(inventoryRepository),
            inventoryRegisterUseCase = InventoryRegisterUseCase(inventoryRepository)
        )
    }

}