package com.inha.sellstarter_android.di

import com.inha.sellstarter_android.domain.repository.ChatbotRepository
import com.inha.sellstarter_android.domain.repository.DataAnalysisRepository
import com.inha.sellstarter_android.domain.repository.HomeRepository
import com.inha.sellstarter_android.domain.repository.InventoryRepository
import com.inha.sellstarter_android.domain.repository.MyPageRepository
import com.inha.sellstarter_android.domain.repository.OrderRepository
import com.inha.sellstarter_android.domain.usecase.chatbot.EndChatUseCase
import com.inha.sellstarter_android.domain.usecase.chatbot.ChatbotMessageUseCase
import com.inha.sellstarter_android.domain.usecase.chatbot.StartChatUseCase
import com.inha.sellstarter_android.domain.usecase.chatbot.ChatbotUseCases
import com.inha.sellstarter_android.domain.usecase.dataanalysis.DataAnalysisUseCases
import com.inha.sellstarter_android.domain.usecase.dataanalysis.LoadDataReportUseCase
import com.inha.sellstarter_android.domain.usecase.dataanalysis.LoadInventoryFlowGraphUseCase
import com.inha.sellstarter_android.domain.usecase.home.LoadHomeDashboardUseCase
import com.inha.sellstarter_android.domain.usecase.home.HomeUseCases
import com.inha.sellstarter_android.domain.usecase.home.LoadWeeklySalesUseCase
import com.inha.sellstarter_android.domain.usecase.home.LoadYearlySalesUseCase
import com.inha.sellstarter_android.domain.usecase.inventory.LoadInventoryDetailUseCase
import com.inha.sellstarter_android.domain.usecase.inventory.UpdateInventoryCountUseCase
import com.inha.sellstarter_android.domain.usecase.inventory.LoadInventoryListUseCase
import com.inha.sellstarter_android.domain.usecase.inventory.RegisterInventoryItemUseCase
import com.inha.sellstarter_android.domain.usecase.inventory.SearchInventoriesUseCase
import com.inha.sellstarter_android.domain.usecase.inventory.InventoryUseCases
import com.inha.sellstarter_android.domain.usecase.mypage.MyPageUseCases
import com.inha.sellstarter_android.domain.usecase.mypage.LoadUserDetailUseCase
import com.inha.sellstarter_android.domain.usecase.mypage.RegisterStoreApiKeyUseCase
import com.inha.sellstarter_android.domain.usecase.mypage.RemoveStoreApiKeyUseCase
import com.inha.sellstarter_android.domain.usecase.mypage.UpdateStoreApiKeyUseCase
import com.inha.sellstarter_android.domain.usecase.order.CancelOrderUseCase
import com.inha.sellstarter_android.domain.usecase.order.CompleteOrderPickingsUseCase
import com.inha.sellstarter_android.domain.usecase.order.CompleteSinglePickingUseCase
import com.inha.sellstarter_android.domain.usecase.order.ShipOrderUseCase
import com.inha.sellstarter_android.domain.usecase.order.LoadCompletedPickingListUseCase
import com.inha.sellstarter_android.domain.usecase.order.LoadOrderConfirmListUseCase
import com.inha.sellstarter_android.domain.usecase.order.LoadOrderConfirmationDetailUseCase
import com.inha.sellstarter_android.domain.usecase.order.CheckPickingAvailableUseCase
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
            registerStoreApiKeyUseCase = RegisterStoreApiKeyUseCase(myPageRepository),
            updateStoreApiKeyUseCase = UpdateStoreApiKeyUseCase(myPageRepository),
            removeStoreApiKeyUseCase = RemoveStoreApiKeyUseCase(myPageRepository),
            loadUserDetailUseCase = LoadUserDetailUseCase(myPageRepository)
        )
    }

    @Provides
    @Singleton
    fun provideChatbotUseCases(
        chatbotRepository: ChatbotRepository
    ): ChatbotUseCases {
        return ChatbotUseCases(
            startChatSession = StartChatUseCase(chatbotRepository),
            sendChatMessage = ChatbotMessageUseCase(chatbotRepository),
            endChatSession = EndChatUseCase(chatbotRepository)
        )
    }

    @Provides
    @Singleton
    fun provideInventoryUseCase(
        inventoryRepository: InventoryRepository
    ): InventoryUseCases {
        return InventoryUseCases(
            loadInventoryListUseCase = LoadInventoryListUseCase(inventoryRepository),
            loadInventoryDetailUseCase = LoadInventoryDetailUseCase(inventoryRepository),
            searchInventoriesUseCase = SearchInventoriesUseCase(inventoryRepository),
            updateInventoryCountUseCase = UpdateInventoryCountUseCase(inventoryRepository),
            registerInventoryUseCase = RegisterInventoryItemUseCase(inventoryRepository),
        )
    }

    @Provides
    @Singleton
    fun provideOrderUseCase(
        orderRepository: OrderRepository
    ): OrderUseCases {
        return OrderUseCases(
            loadOrderConfirmListUseCase = LoadOrderConfirmListUseCase(orderRepository),
            loadOrderConfirmationDetailUseCase = LoadOrderConfirmationDetailUseCase(
                orderRepository
            ),
            checkPickingAvailableUseCase = CheckPickingAvailableUseCase(orderRepository),
            completeOrderPickingsUseCase = CompleteOrderPickingsUseCase(orderRepository),
            completeSinglePickingUseCase = CompleteSinglePickingUseCase(orderRepository),
            loadCompletedPickingListUseCase = LoadCompletedPickingListUseCase(orderRepository),
            shipOrderUseCase = ShipOrderUseCase(orderRepository),
            cancelOrderUseCase = CancelOrderUseCase(orderRepository)
        )
    }

    @Provides
    @Singleton
    fun provideDataAnalysisUseCase(
        dataAnalysisRepository: DataAnalysisRepository
    ): DataAnalysisUseCases {
        return DataAnalysisUseCases(
            loadInventoryFlowGraph = LoadInventoryFlowGraphUseCase(dataAnalysisRepository),
            loadDataReport = LoadDataReportUseCase(dataAnalysisRepository)
        )
    }

    @Provides
    @Singleton
    fun provideHomeUseCase(
        homeRepository: HomeRepository
    ): HomeUseCases {
        return HomeUseCases(
            loadHomeDashboard =  LoadHomeDashboardUseCase(homeRepository),
            loadWeeklySales = LoadWeeklySalesUseCase(homeRepository),
            loadAnnualSales = LoadYearlySalesUseCase(homeRepository)
        )
    }

}