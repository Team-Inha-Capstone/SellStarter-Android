package com.inha.sellstarter_android.di

import com.inha.sellstarter_android.domain.repository.ChatbotRepository
import com.inha.sellstarter_android.domain.repository.DataAnalysisRepository
import com.inha.sellstarter_android.domain.repository.HomeRepository
import com.inha.sellstarter_android.domain.repository.InventoryRepository
import com.inha.sellstarter_android.domain.repository.MyPageRepository
import com.inha.sellstarter_android.domain.repository.OrderRepository
import com.inha.sellstarter_android.domain.usecase.chatbot.ChatbotEndUseCase
import com.inha.sellstarter_android.domain.usecase.chatbot.ChatbotMessageUseCase
import com.inha.sellstarter_android.domain.usecase.chatbot.ChatbotStartUseCase
import com.inha.sellstarter_android.domain.usecase.chatbot.ChatbotUseCases
import com.inha.sellstarter_android.domain.usecase.dataanalysis.DataAnalysisUseCases
import com.inha.sellstarter_android.domain.usecase.dataanalysis.DataReportUseCase
import com.inha.sellstarter_android.domain.usecase.dataanalysis.InventoryFlowGraphUseCase
import com.inha.sellstarter_android.domain.usecase.home.HomeInfoUseCase
import com.inha.sellstarter_android.domain.usecase.home.HomeUseCases
import com.inha.sellstarter_android.domain.usecase.home.WeeklySalesUseCase
import com.inha.sellstarter_android.domain.usecase.home.YearlySalesUseCase
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
import com.inha.sellstarter_android.domain.usecase.order.CancelOrderUseCase
import com.inha.sellstarter_android.domain.usecase.order.CompleteOrderPickingsUseCase
import com.inha.sellstarter_android.domain.usecase.order.CompleteSinglePickingUseCase
import com.inha.sellstarter_android.domain.usecase.order.ConfirmOrderShipmentUseCase
import com.inha.sellstarter_android.domain.usecase.order.FetchCompletedPickingListUseCase
import com.inha.sellstarter_android.domain.usecase.order.FetchOrderConfirmListUseCase
import com.inha.sellstarter_android.domain.usecase.order.FetchOrderConfirmationDetailUseCase
import com.inha.sellstarter_android.domain.usecase.order.IsPickingAvailableUseCase
import com.inha.sellstarter_android.domain.usecase.order.OrderUseCases
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
            startChatSession = ChatbotStartUseCase(chatbotRepository),
            sendChatMessage = ChatbotMessageUseCase(chatbotRepository),
            endChatSession = ChatbotEndUseCase(chatbotRepository)
        )
    }

    @Provides
    @Singleton
    fun provideInventoryUseCase(
        inventoryRepository: InventoryRepository
    ): InventoryUseCases {
        return InventoryUseCases(
            inventoryListUseCase = InventoryListUseCase(inventoryRepository),
            inventoryDetailUseCase = InventoryDetailUseCase(inventoryRepository),
            inventorySearchUseCase = InventorySearchUseCase(inventoryRepository),
            inventoryEditCountUseCase = InventoryEditCountUseCase(inventoryRepository),
            inventoryRegisterUseCase = InventoryRegisterUseCase(inventoryRepository),
        )
    }

    @Provides
    @Singleton
    fun provideOrderUseCase(
        orderRepository: OrderRepository
    ): OrderUseCases {
        return OrderUseCases(
            fetchOrderConfirmListUseCase = FetchOrderConfirmListUseCase(orderRepository),
            fetchOrderConfirmationDetailUseCase = FetchOrderConfirmationDetailUseCase(
                orderRepository
            ),
            isPickingAvailableUseCase = IsPickingAvailableUseCase(orderRepository),
            completeOrderPickingsUseCase = CompleteOrderPickingsUseCase(orderRepository),
            completeSinglePickingUseCase = CompleteSinglePickingUseCase(orderRepository),
            fetchCompletedPickingListUseCase = FetchCompletedPickingListUseCase(orderRepository),
            confirmOrderShipmentUseCase = ConfirmOrderShipmentUseCase(orderRepository),
            cancelOrderUseCase = CancelOrderUseCase(orderRepository)
        )
    }

    @Provides
    @Singleton
    fun provideDataAnalysisUseCase(
        dataAnalysisRepository: DataAnalysisRepository
    ): DataAnalysisUseCases {
        return DataAnalysisUseCases(
            loadInventoryFlowGraph = InventoryFlowGraphUseCase(dataAnalysisRepository),
            loadDataReport = DataReportUseCase(dataAnalysisRepository)
        )
    }

    @Provides
    @Singleton
    fun provideHomeUseCase(
        homeRepository: HomeRepository
    ): HomeUseCases {
        return HomeUseCases(
            homeInfoUseCase = HomeInfoUseCase(homeRepository),
            weeklySalesUseCase = WeeklySalesUseCase(homeRepository),
            yearlySalesUseCase = YearlySalesUseCase(homeRepository)
        )
    }

}