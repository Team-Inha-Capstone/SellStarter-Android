package com.inha.sellstarter.domain.usecase.inventory

import com.inha.sellstarter.data.model.request.inventory.InventoryCreateRequestDto
import com.inha.sellstarter.domain.model.Inventory
import com.inha.sellstarter.domain.repository.InventoryRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.test.runTest
import okhttp3.MultipartBody
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith

@DisplayName("RegisterInventoryItemUseCase 테스트")
@ExtendWith(MockKExtension::class)
class RegisterInventoryUseCaseTest {
    @MockK
    lateinit var mockRepo: InventoryRepository
    private lateinit var useCase: RegisterInventoryItemUseCase

    @BeforeEach
    fun setup() {
        useCase = RegisterInventoryItemUseCase(mockRepo)
    }

    @Test
    @DisplayName("올바른 요청 시 Inventory가 등록되어 반환된다")
    fun successRegisterInventory() =
        runTest {
            // given
            val imagePart: MultipartBody.Part? = null
            val request =
                InventoryCreateRequestDto(
                    inventoryName = "새상품",
                    inventoryCount = 10,
                    inventoryOption = "블루",
                    inventoryLocation = "선반B",
                    expiration = "2025-12-31",
                    barcodeId = "XYZ123",
                )
            val expected =
                Inventory(
                    id = "XYZ123",
                    name = "새상품",
                    quantity = 10,
                    imageUrl = "111",
                    expiration = "2025-12-31",
                    isSoldOut = false,
                    option = "블루",
                    location = "선반B",
                )
            coEvery {
                mockRepo.registerInventoryItem(
                    inventoryCreateRequest = request,
                    image = imagePart,
                )
            } returns Result.success(expected)

            // when
            val actual = useCase(imagePart, request)

            // then
            assertTrue(actual.isSuccess)
            assertEquals(expected, actual.getOrNull())
            coVerify(exactly = 1) {
                mockRepo.registerInventoryItem(request, imagePart)
            }
        }

    @Test
    @DisplayName("Repository가 예외 발생 시, UseCase도 동일 예외를 던진다")
    fun exceptionCase() =
        runTest {
            // given
            val imagePart: MultipartBody.Part? = null
            val request =
                InventoryCreateRequestDto(
                    inventoryName = "테스트",
                    inventoryCount = 1,
                    inventoryOption = "그린",
                    inventoryLocation = "선반C",
                    expiration = "2025-10-10",
                    barcodeId = "DEF456",
                )
            val exception = IllegalStateException("네트워크 오류")
            coEvery {
                mockRepo.registerInventoryItem(request, imagePart)
            } throws exception

            // when
            val thrown =
                assertThrows<IllegalStateException> {
                    useCase(imagePart, request) // 이 호출에서 예외 발생
                }

            // then
            assertEquals("네트워크 오류", thrown.message)

            coVerify(exactly = 1) {
                mockRepo.registerInventoryItem(request, imagePart)
            }
        }
}
