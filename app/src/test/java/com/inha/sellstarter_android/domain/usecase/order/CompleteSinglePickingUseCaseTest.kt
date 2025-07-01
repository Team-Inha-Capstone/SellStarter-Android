package com.inha.sellstarter_android.domain.usecase.order

import com.inha.sellstarter_android.data.model.request.order.OrderInventoryPickingRequestDto
import com.inha.sellstarter_android.domain.repository.OrderRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertSame
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.extension.ExtendWith

@DisplayName("CompleteSinglePickingUseCase 테스트")
@ExtendWith(MockKExtension::class)
class CompleteSinglePickingUseCaseTest {

    @MockK
    lateinit var mockRepo: OrderRepository
    private lateinit var useCase: CompleteSinglePickingUseCase

    @BeforeEach
    fun setUp() {
        useCase = CompleteSinglePickingUseCase(mockRepo)
    }

    @Test
    @DisplayName("단일 피킹 완료 성공 시, Result.success(Unit)를 반환한다")
    fun successCase() = runTest {
        // given
        val orderId = "ORDER123"
        val request = OrderInventoryPickingRequestDto(barcodeId = "ABC123")
        coEvery { mockRepo.completeSinglePicking(orderId, request) } returns Result.success(Unit)

        // when
        val result = useCase(orderId, request)

        // then
        assertTrue(result.isSuccess)
        assertEquals(Unit, result.getOrNull())
        coVerify(exactly = 1) { mockRepo.completeSinglePicking(orderId, request) }
    }

    @Test
    @DisplayName("단일 피킹 완료 실패 시, Result.failure 예외를 반환한다")
    fun failureCase() = runTest {
        // given
        val orderId = "ORDER_INVALID"
        val request = OrderInventoryPickingRequestDto(barcodeId = "XYZ999")
        val error = RuntimeException("Complete single picking failed")
        coEvery { mockRepo.completeSinglePicking(orderId, request) } returns Result.failure(error)

        // when
        val result = useCase(orderId, request)

        // then
        assertTrue(result.isFailure)
        assertSame(error, result.exceptionOrNull())
        coVerify(exactly = 1) { mockRepo.completeSinglePicking(orderId, request) }
    }
}