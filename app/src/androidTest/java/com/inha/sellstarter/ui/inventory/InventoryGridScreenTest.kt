
@file:Suppress("ktlint:standard:function-naming")

package com.inha.sellstarter.ui.inventory

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import androidx.compose.ui.test.performTextInput
import com.inha.sellstarter.domain.model.InventoryListPage
import com.inha.sellstarter.domain.model.InventorySummary
import com.inha.sellstarter.ui.CommonTestData
import com.inha.sellstarter.util.base.UiState
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class InventoryGridScreenTest {
    /**
     * 재고 리스트 뷰 검증 항목
     * 1. 성공, 로딩, 에러, 빈 상태 검증
     * 2. 재고 리스트 아이템 클릭 검증
     * 3. 그리드 리스트 무한스크롤 onLoadMore 검증
     * 4. 바코드 번호 검색 입력 검증
     * 5. 필터칩 클릭 검증
     * 6. 긴 텍스트 입력 display 보여짐 여부 검증
     **/

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 성공상태에서_초기화면_렌더링_시_제목_설명_검색바_필터칩_그리드가_모두_표시된다() {
        InventoryGridTestData.setInventoryGridScreen(composeTestRule)
        // 루트, 타이틀, 텍스트
        composeTestRule.onNodeWithTag(InventoryGridTestData.TAG_ROOT)
            .assertIsDisplayed()
        composeTestRule.onNodeWithTag(CommonTestData.TAG_TITLE)
            .assertIsDisplayed()
            .assertTextContains("스토어 재고 확인")
        composeTestRule.onNodeWithText("스토어 내 재고를 한눈에 파악하세요.")
            .assertIsDisplayed()

        // 검색바, 필터칩, 그리드
        composeTestRule.onNodeWithTag(InventoryGridTestData.TAG_SEARCH_BAR)
            .assertIsDisplayed()
        composeTestRule.onNodeWithTag(InventoryGridTestData.TAG_FILTER_CHIPS)
            .assertIsDisplayed()
        composeTestRule.onNodeWithTag(InventoryGridTestData.TAG_GRID)
            .assertIsDisplayed()

        // 재고 아이템
        InventoryGridTestData.dummyList.forEach { item ->
            composeTestRule.onNodeWithTag(InventoryGridTestData.itemTag(item.id))
                .assertIsDisplayed()
        }
    }

    @Test
    fun 로딩_상태에서_로딩화면이_표시된다() {
        InventoryGridTestData.setInventoryGridScreen(
            composeTestRule,
            uiState = UiState.Loading,
        )

        composeTestRule
            .onNodeWithTag(CommonTestData.TAG_LOADING_LOTTIE)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("재고 정보를 가져오고 있습니다.")
            .assertIsDisplayed()

        listOf(
            InventoryGridTestData.TAG_GRID,
            CommonTestData.TAG_EMPTY,
            CommonTestData.TAG_ERROR,
        ).forEach { tag ->
            composeTestRule.onNodeWithTag(tag).assertDoesNotExist()
        }
    }

    @Test
    fun 재고_목록이_빈_상태일_때_빈_화면이_표시된다() {
        InventoryGridTestData.setInventoryGridScreen(
            composeTestRule,
            uiState =
                UiState.Success(
                    InventoryListPage(
                        emptyList(),
                        0,
                        0,
                        0,
                        0,
                    ),
                ),
        )

        composeTestRule.onNodeWithTag(CommonTestData.TAG_EMPTY)
            .assertIsDisplayed()
        composeTestRule.onNodeWithText("재고가 존재하지 않습니다.")
            .assertIsDisplayed()

        listOf(
            InventoryGridTestData.TAG_GRID,
            CommonTestData.TAG_LOADING,
            CommonTestData.TAG_ERROR,
        ).forEach { tag ->
            composeTestRule.onNodeWithTag(tag).assertDoesNotExist()
        }
    }

    @Test
    fun 실패_상태일_때_에러_화면이_표시된다() {
        InventoryGridTestData.setInventoryGridScreen(
            composeTestRule,
            uiState = UiState.Failure("데이터 로딩 중 예외 발생"),
        )

        composeTestRule.onNodeWithTag(CommonTestData.TAG_ERROR)
            .assertIsDisplayed()
        composeTestRule.onNodeWithText("재고 정보를 가져오는 데 실패했습니다.")
            .assertIsDisplayed()

        listOf(
            InventoryGridTestData.TAG_GRID,
            CommonTestData.TAG_EMPTY,
            CommonTestData.TAG_LOADING,
        ).forEach { tag ->
            composeTestRule.onNodeWithTag(tag).assertDoesNotExist()
        }
    }

    @Test
    fun 아이템_클릭_시_onItemClick_콜백에_해당_아이디가_전달된다() {
        var clickedId: String? = null
        InventoryGridTestData.setInventoryGridScreen(
            composeTestRule,
            onItemClick = { clickedId = it },
        )

        composeTestRule.onNodeWithTag(InventoryGridTestData.itemTag("2"))
            .performClick()
        assertEquals("2", clickedId)
    }

    @Test
    fun 그리드를_끝까지_스크롤하면_onLoadMore_콜백이_호출된다() {
        val many =
            List(30) { idx ->
                InventorySummary("$idx", "Item #$idx", idx, false, "opt", null)
            }
        var loadMoreCalled = false
        InventoryGridTestData.setInventoryGridScreen(
            composeTestRule,
            uiState =
                UiState.Success(
                    InventoryListPage(many, 0, many.size, many.size, 2),
                ),
            hasNextPage = true,
            onLoadMore = { loadMoreCalled = true },
        )

        composeTestRule.onNodeWithTag(InventoryGridTestData.TAG_GRID)
            .performScrollToIndex(28)
        assertTrue(loadMoreCalled)
    }

    @Test
    fun 페이지_로딩_상태일_때_로딩_인디케이터가_표시된다() {
        InventoryGridTestData.setInventoryGridScreen(
            composeTestRule,
            isLoadingMore = true,
            hasNextPage = true,
        )

        // Grid 를 스크롤해서 마지막 인덱스(더미List.size) 위치로 이동
        composeTestRule.onNodeWithTag(InventoryGridTestData.TAG_GRID)
            .performScrollToIndex(InventoryGridTestData.dummyList.size)

        // 화면에 보이는 Indicator 검증
        composeTestRule.onNodeWithTag(InventoryGridTestData.TAG_LOAD_MORE_INDICATOR)
            .assertIsDisplayed()
    }

    @Test
    fun 검색창에_바코드번호를_입력하고_엔터_액션_시_onSearch_콜백이_호출된다() {
        var typed = ""
        var searched = false

        InventoryGridTestData.setInventoryGridScreen(
            composeTestRule,
            onSearchTextChanged = { typed = it },
            onSearch = { searched = true },
        )

        composeTestRule
            .onNodeWithTag(InventoryGridTestData.TAG_SEARCH_INPUT)
            .performTextInput("ABC123\n")

        assertEquals("ABC123", typed)
        assertTrue(searched)
    }

    @Test
    fun 품절_필터_칩_클릭_시_onChipSelected_콜백이_호출된다() {
        var selected = -1
        InventoryGridTestData.setInventoryGridScreen(
            composeTestRule,
            onChipSelected = { selected = it },
        )

        composeTestRule.onNodeWithTag("FilterChip_1")
            .assertIsDisplayed()
            .performClick()

        assertEquals(1, selected)
    }

    @Test
    fun 검색창_입력_최대_50자_허용을_확인한다() {
        var typed = ""
        InventoryGridTestData.setInventoryGridScreen(
            composeTestRule,
            onSearchTextChanged = { typed = it },
        )

        val longText = "A".repeat(100)

        composeTestRule
            .onNodeWithTag(InventoryGridTestData.TAG_SEARCH_INPUT)
            .performTextInput(longText)

        val expected = "A".repeat(50)
        assertEquals(expected, typed)
    }
}
