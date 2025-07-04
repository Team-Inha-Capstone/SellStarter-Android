package com.inha.sellstarter.domain.usecase.order

import com.inha.sellstarter.domain.model.BuyerInfo
import com.inha.sellstarter.domain.model.OrderDetailInfo
import com.inha.sellstarter.domain.model.OrderInfo
import com.inha.sellstarter.domain.model.OrderPickingInventory
import com.inha.sellstarter.domain.model.PickingInfo
import com.inha.sellstarter.domain.model.type.OrderStatusType
import com.inha.sellstarter.domain.repository.OrderRepository
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

@DisplayName("LoadOrderConfirmationDetailUseCase 테스트")
@ExtendWith(MockKExtension::class)
class LoadOrderDetailUseCaseTest {
    @MockK
    lateinit var mockRepo: OrderRepository
    private lateinit var useCase: LoadOrderConfirmationDetailUseCase

    @BeforeEach
    fun setUp() {
        useCase = LoadOrderConfirmationDetailUseCase(mockRepo)
    }

    @Test
    @DisplayName("주문 상세 조회 성공 시, OrderDetailInfo를 반환한다")
    fun successCase() =
        runTest {
            // given
            val orderId = "ORDER123"
            val orderInfo =
                OrderInfo(
                    orderId = orderId,
                    channelName = "WEB",
                    orderStatus = OrderStatusType.PIKING_COMPLETED,
                )
            val items =
                listOf(
                    OrderPickingInventory(
                        inventoryName = "Item A",
                        barcodeId = "ABC123",
                        inventoryCount = 2,
                        isPicked = true,
                    ),
                    OrderPickingInventory(
                        inventoryName = "Item B",
                        barcodeId = "DEF456",
                        inventoryCount = 1,
                        isPicked = true,
                    ),
                )
            val pickingInfo =
                PickingInfo(
                    items = items,
                    allPicked = true,
                )
            val buyerInfo =
                BuyerInfo(
                    purchaserName = "Jane Doe",
                    purchaserAddress = "123 Main St",
                    purchaserRequest = "Leave at door",
                )
            val expected =
                OrderDetailInfo(
                    orderInfo = orderInfo,
                    pickingInfo = pickingInfo,
                    buyerInfo = buyerInfo,
                )

            coEvery { mockRepo.loadOrderConfirmationDetail(orderId) } returns Result.success(expected)

            // when
            val result = useCase(orderId)

            // then
            assertTrue(result.isSuccess)
            assertEquals(expected, result.getOrNull())
            coVerify(exactly = 1) { mockRepo.loadOrderConfirmationDetail(orderId) }
        }

    @Test
    @DisplayName("문 상세 조회 실패 시, 예외를 반환한다")
    fun failureCase() =
        runTest {
            // given
            val orderId = "ORDER_INVALID"
            val error = RuntimeException("Detail load failed")
            coEvery { mockRepo.loadOrderConfirmationDetail(orderId) } returns Result.failure(error)

            // when
            val result = useCase(orderId)

            // then
            assertTrue(result.isFailure)
            assertSame(error, result.exceptionOrNull())
            coVerify(exactly = 1) { mockRepo.loadOrderConfirmationDetail(orderId) }
        }
}
