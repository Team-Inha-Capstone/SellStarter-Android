package com.inha.sellstarter_android.domain.usecase.inventory

import com.inha.sellstarter_android.data.model.request.inventory.InventoryCountRequestDto
import com.inha.sellstarter_android.domain.model.Inventory
import com.inha.sellstarter_android.domain.repository.InventoryRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith

@DisplayName("UpdateInventoryCountUseCase 단위 테스트")
@ExtendWith(MockKExtension::class)
class UpdateInventoryCountUseCaseTest {

    @MockK
    lateinit var mockRepo: InventoryRepository
    private lateinit var useCase: UpdateInventoryCountUseCase

    @BeforeEach
    fun setUp(){
        useCase = UpdateInventoryCountUseCase(mockRepo)
    }

    @Test
    @DisplayName("양수 quantity 입력 시 재고가 해당 수로 변경된다")
    fun successUpdateQuantity() = runTest {
        //given
        val barcodeId = "ABC123"
        val newQuantity = 10
        val original = Inventory(
            id = barcodeId,
            name = "테스트상품",
            quantity = 3,
            imageUrl = "123",
            expiration = "2025-12-31",
            isSoldOut = false,
            option = "옵션A",
            location = "선반1"
        )

        val request = InventoryCountRequestDto(
            currentCount = original.quantity,
            inventoryCount = newQuantity
        )

        val updated = original.copy(quantity = newQuantity)
        coEvery {
            mockRepo.updateInventoryCount(barcodeId, request)
        } returns Result.success(updated)


        //when
        val actualResult = useCase(barcodeId, request)

        //then
        assertTrue(actualResult.isSuccess)
        assertEquals(updated, actualResult.getOrNull())
        coVerify(exactly = 1) { mockRepo.updateInventoryCount(barcodeId, request) }
    }
}