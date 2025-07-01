package com.inha.sellstarter_android.domain.usecase.order

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

@DisplayName("CancelOrderUseCase 테스트")
@ExtendWith(MockKExtension::class)
class CancelOrderUseCaseTest {

    @MockK
    lateinit var mockRepo: OrderRepository
    private lateinit var useCase: CancelOrderUseCase

    @BeforeEach
    fun setUp() {
        useCase = CancelOrderUseCase(mockRepo)
    }

    @Test
    @DisplayName("주문 취소 성공 시, Result.success(Unit)를 반환한다")
    fun successCase() = runTest {
        // given
        val orderId = "ORDER123"
        coEvery { mockRepo.cancelOrder(orderId) } returns Result.success(Unit)

        // when
        val result = useCase(orderId)

        // then
        assertTrue(result.isSuccess)
        assertEquals(Unit, result.getOrNull())
        coVerify(exactly = 1) { mockRepo.cancelOrder(orderId) }
    }

    @Test
    @DisplayName("주문 취소 실패 시, Result.failure 예외를 반환한다")
    fun failureCase() = runTest {
        // given
        val orderId = "ORDER_INVALID"
        val error = RuntimeException("Cancel failed")
        coEvery { mockRepo.cancelOrder(orderId) } returns Result.failure(error)

        // when
        val result = useCase(orderId)

        // then
        assertTrue(result.isFailure)
        assertSame(error, result.exceptionOrNull())
        coVerify(exactly = 1) { mockRepo.cancelOrder(orderId) }
    }
}