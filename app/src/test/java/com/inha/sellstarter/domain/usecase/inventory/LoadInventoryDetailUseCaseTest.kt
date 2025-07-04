package com.inha.sellstarter.domain.usecase.inventory

import com.inha.sellstarter.domain.model.Inventory
import com.inha.sellstarter.domain.repository.InventoryRepository
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

@DisplayName("LoadInventoryDetailUseCase 단위 테스트")
@ExtendWith(MockKExtension::class)
class LoadInventoryDetailUseCaseTest {
    @MockK
    lateinit var mockRepo: InventoryRepository
    private lateinit var useCase: LoadInventoryDetailUseCase

    @BeforeEach
    fun setUp() {
        useCase = LoadInventoryDetailUseCase(mockRepo)
    }

    @Test
    @DisplayName("Repository가 Inventory 반환 시, UseCase도 같은 Inventory를 반환한다")
    fun successCase() =
        runTest {
            // given
            val barcodeId = "ABC123"
            val expected =
                Inventory(
                    id = barcodeId,
                    name = "테스트 상품",
                    quantity = 42,
                    imageUrl = "  ",
                    expiration = "2025-12-31",
                    isSoldOut = false,
                    option = "빨간색",
                    location = "선반 A",
                )
            coEvery { mockRepo.loadInventoryDetail(barcodeId) } returns Result.success(expected)
            // 특정 barcodeId로 호출 했을 때, expected가 실행되도록 repository를 세팅한다.

            // when
            val actualResult = useCase(barcodeId)

            // then
            assertTrue(actualResult.isSuccess)
            assertEquals(expected, actualResult.getOrNull())
            coVerify(exactly = 1) { mockRepo.loadInventoryDetail(barcodeId) }
        }

    @Test
    @DisplayName("Repository가 예외 발생 시, UseCase도 동일 예외를 던진다")
    fun exceptionCase() =
        runTest {
            // given
            val barcodeId = "INVALID"
            val error = Exception("Not found")
            coEvery { mockRepo.loadInventoryDetail(barcodeId) } returns Result.failure(error)

            // when
            val actualResult = useCase(barcodeId)

            // then
            assertTrue(actualResult.isFailure)
            assertSame(error, actualResult.exceptionOrNull())
            coVerify(exactly = 1) { mockRepo.loadInventoryDetail(barcodeId) }
        }
}
