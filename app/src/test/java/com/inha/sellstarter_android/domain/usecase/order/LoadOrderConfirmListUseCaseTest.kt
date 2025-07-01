package com.inha.sellstarter_android.domain.usecase.order

import com.inha.sellstarter_android.domain.model.OrderListPage
import com.inha.sellstarter_android.domain.model.OrderSummary
import com.inha.sellstarter_android.domain.model.type.ChannelPlatform
import com.inha.sellstarter_android.domain.repository.OrderRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertSame
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@DisplayName("LoadOrderConfirmListUseCase 테스트")
@ExtendWith(MockKExtension::class)
class LoadOrderConfirmListUseCaseTest {

    @MockK
    lateinit var mockRepo: OrderRepository
    private lateinit var useCase: LoadOrderConfirmListUseCase

    @BeforeEach
    fun setUp() {
        useCase = LoadOrderConfirmListUseCase(mockRepo)
    }

    @Test
    @DisplayName("주문확인 리스트 조회 성공 시, OrderListPage를 반환한다")
    fun successCase() = runTest {
        // given
        val page = 0
        val size = 10
        val summaries = listOf(
            OrderSummary(orderId = "ORDER123", orderDate = "2025-06-30", channel = ChannelPlatform.SHOPIFY, inventoryItem = "ItemX"),
            OrderSummary(orderId = "ORDER456", orderDate = "2025-06-29", channel = ChannelPlatform.NAVER, inventoryItem = "ItemY")
        )
        val expected = OrderListPage(
            orders = summaries,
            page = page,
            size = size,
            totalElements = summaries.size,
            totalPages = 1
        )
        coEvery { mockRepo.loadOrderConfirmList(page = page, size = size) } returns Result.success(expected)

        // when
        val result = useCase(page, size)

        // then
        assertTrue(result.isSuccess)
        assertEquals(expected, result.getOrNull())
        coVerify(exactly = 1) { mockRepo.loadOrderConfirmList(page = page, size = size) }
    }

    @Test
    @DisplayName("주문확인 리스트 조회 실패 시, 예외를 반환한다")
    fun failureCase() = runTest {
        // given
        val page = 1
        val size = 5
        val error = RuntimeException("Load confirm-list failed")
        coEvery { mockRepo.loadOrderConfirmList(page = page, size = size) } returns Result.failure(error)

        // when
        val result = useCase(page, size)

        // then
        assertTrue(result.isFailure)
        assertSame(error, result.exceptionOrNull())
        coVerify(exactly = 1) { mockRepo.loadOrderConfirmList(page = page, size = size) }
    }
}
