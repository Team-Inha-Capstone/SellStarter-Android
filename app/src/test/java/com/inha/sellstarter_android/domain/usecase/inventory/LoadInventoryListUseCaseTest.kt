package com.inha.sellstarter_android.domain.usecase.inventory

import com.inha.sellstarter_android.domain.model.InventoryListPage
import com.inha.sellstarter_android.domain.model.InventorySummary
import com.inha.sellstarter_android.domain.repository.InventoryRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith

@DisplayName("LoadInventoryListUseCase 단위 테스트")
@ExtendWith(MockKExtension::class)
class LoadInventoryListUseCaseTest {

    @MockK
    lateinit var mockRepo: InventoryRepository
    private lateinit var useCase: LoadInventoryListUseCase

    @BeforeEach
    fun setUp() {
        useCase = LoadInventoryListUseCase(mockRepo)
    }

    @Test
    @DisplayName("검색어 = null, status = true일 때, 품절 아이템만 반환한다.")
    fun successSoldOutCase() = runTest {
        // given
        val summaries = listOf(
            InventorySummary(
                id = "A",
                name = "Item A",
                quantity = 5,
                isSoldOut = false,
                option = "옵션A",
                imageUrl = "11"
            ),
            InventorySummary(
                id = "B",
                name = "Item B",
                quantity = 3,
                isSoldOut = true,
                option = "옵션B",
                imageUrl = "22"
            )
        )

        val expected = InventoryListPage(
            inventories = summaries.filter { it.isSoldOut },
            page = 0,
            size = 10,
            totalElements = 1,
            totalPages = 1
        )

        coEvery {
            mockRepo.loadInventoryList(search = null, status = true, page = 0, size = 10)
        } returns Result.success(expected)

        // when
        val actualResult = useCase(search = null, status = true, page = 0, size = 10)

        // then
        assertTrue(actualResult.isSuccess)
        assertEquals(expected, actualResult.getOrNull())
        coVerify(exactly = 1) {
            mockRepo.loadInventoryList(
                search = null,
                status = true,
                page = 0,
                size = 10
            )
        }
        // suspend 함수가 1번만 호출되었는지 검증
    }

    @Test
    @DisplayName("검색어 = ABC123 일 때, 바코드 번호 ABC123만 반환한다.")
    fun successSearchCase() = runTest {
        // given
        val summaries = listOf(
            InventorySummary(
                id = "ABC123",
                name = "Item A",
                quantity = 5,
                isSoldOut = false,
                option = "옵션A",
                imageUrl = "11"
            ),
            InventorySummary(
                id = "B",
                name = "Item B",
                quantity = 3,
                isSoldOut = true,
                option = "옵션B",
                imageUrl = "22"
            )
        )

        val expected = InventoryListPage(
            inventories = summaries.filter { it.id == "ABC123" },
            page = 0,
            size = 10,
            totalElements = 1,
            totalPages = 1
        )

        coEvery {
            mockRepo.loadInventoryList(search = "ABC123", status = false, page = 0, size = 10)
        } returns Result.success(expected)

        // when
        val actualResult = useCase(search = "ABC123", status = false, page = 0, size = 10)

        // then
        assertTrue(actualResult.isSuccess)
        assertEquals(expected, actualResult.getOrNull())
        coVerify(exactly = 1) {
            mockRepo.loadInventoryList(
                search = "ABC123",
                status = false,
                page = 0,
                size = 10
            )
        }
        // suspend 함수가 1번만 호출되었는지 검증
    }

    @Test
    @DisplayName("등록된 재고 리스트가 없을 때 재고 조회시, 빈 리스트가 반환된다.")
    fun successEmptyCase() = runTest {
        // given
        val expected = InventoryListPage(
            inventories = emptyList(),
            page = 0,
            size = 10,
            totalElements = 0,
            totalPages = 0
        )

        coEvery {
            mockRepo.loadInventoryList(search = null, status = false, page = 0, size = 10)
        } returns Result.success(expected)

        // when
        val actualResult = useCase(search = null, status = false, page = 0, size = 10)

        // then
        assertTrue(actualResult.isSuccess)
        assertEquals(expected, actualResult.getOrNull())
        coVerify(exactly = 1) {
            mockRepo.loadInventoryList(search = null, status = false, page = 0, size = 10)
        }
        // suspend 함수가 1번만 호출되었는지 검증
    }

    @Test
    @DisplayName("검색어 = 빈 문자열이고, status = false일 때, 전체 목록을 반환한다.")
    fun successSearchEmptyCase() = runTest {
        // given
        val summaries = listOf(
            InventorySummary(
                id = "A",
                name = "Item A",
                quantity = 5,
                isSoldOut = false,
                option = "옵션A",
                imageUrl = "11"
            ),
            InventorySummary(
                id = "B",
                name = "Item B",
                quantity = 3,
                isSoldOut = true,
                option = "옵션B",
                imageUrl = "22"
            )
        )
        val expected = InventoryListPage(
            inventories = summaries,
            page = 0,
            size = 10,
            totalElements = summaries.size,
            totalPages = 1
        )

        coEvery {
            mockRepo.loadInventoryList(
                search = "",
                status = false,
                page = 0,
                size = 10
            )
        } returns Result.success(expected)

        // when
        val actualResult = useCase(
            search = "",
            status = false,
            page = 0,
            size = 10
        )

        // then
        assertTrue(actualResult.isSuccess)
        assertEquals(expected, actualResult.getOrNull())
        coVerify(exactly = 1) {
            mockRepo.loadInventoryList("", false, 0, 10)
        }
    }

    @Test
    @DisplayName("Repository가 예외 발생 시 UseCase도 같은 예외를 던진다.")
    fun exceptionCase() = runTest {

        // given
        val error = IllegalStateException("test 예외")
        coEvery {
            mockRepo.loadInventoryList(
                search = null,
                status = false,
                page = 0,
                size = 10
            )
        } returns Result.failure(error)

        // when
        val actualResult = useCase(
            search = null,
            status = false,
            page = 0,
            size = 10
        )

        // then
        assertTrue(actualResult.isFailure)
        assertSame(error, actualResult.exceptionOrNull())
        coVerify(exactly = 1) {
            mockRepo.loadInventoryList(
                search = null,
                status = false,
                page = 0,
                size = 10
            )
        }
    }
}