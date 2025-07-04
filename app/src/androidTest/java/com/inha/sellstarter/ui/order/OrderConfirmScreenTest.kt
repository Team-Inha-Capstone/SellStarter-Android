package com.inha.sellstarter.ui.order

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.inha.sellstarter.domain.model.OrderListPage
import com.inha.sellstarter.ui.order.data.OrderConfirmTestData
import com.inha.sellstarter.util.base.UiState
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OrderConfirmScreenTest {

    /**
     * 주문 확인 (리스트) 뷰 검증 항목
     * 1. 신규 주문 탭이 기본 선택되어 있는지 검증
     * 2. 피킹 완료 탭 클릭 시 onTabSelected 콜백 호출 검증
     * 3. 주문 목록이 정상적으로 표시되는지 검증
     * 4. 주문 아이템 클릭 시 onOrderItemClick 콜백 호출 검증
     * 5. 주문목록이 없을 때 빈 화면이 표시되는지 검증
     * 6. Loading 상태일 때 로딩 UI가 표시되는지 검증
     * 7. Failure 상태일 때 에러 UI가 표시되는지 검증
     * 8. 각 페이지에 맞는 페이지 나오는지 검증
     */

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 신규_주문_탭이_기본_선택되어_있음이_표시된다() {
        OrderConfirmTestData.setOrderConfirmScreen(composeTestRule)

        composeTestRule
            .onNodeWithTag(OrderConfirmTestData.TAG_NEW_TAB)
            .assertIsSelected()

        composeTestRule
            .onNodeWithTag(OrderConfirmTestData.TAG_DONE_TAB)
            .assertIsNotSelected()
    }

    @Test
    fun 피킹_완료_탭_클릭시_onTabSelected_호출된다() {
        var clickedIndex = -1
        OrderConfirmTestData.setOrderConfirmScreen(
            composeTestRule,
            onTabSelected = { clickedIndex = it }
        )

        composeTestRule
            .onNodeWithTag(OrderConfirmTestData.TAG_DONE_TAB)
            .performClick()

        assertEquals(1, clickedIndex)
    }

    @Test
    fun 주문_목록이_정상적으로_표시된다() {
        OrderConfirmTestData.setOrderConfirmScreen(composeTestRule)

        OrderConfirmTestData.dummyOrders.forEach { order ->
            composeTestRule
                .onNodeWithTag(OrderConfirmTestData.itemTag(order.orderId))
                .assertExists()
                .assertHasClickAction()
        }
    }

    @Test
    fun 주문_아이템_클릭시_onOrderItemClick_호출된다() {
        var clickedId: String? = null
        OrderConfirmTestData.setOrderConfirmScreen(
            composeTestRule,
            onOrderItemClick = { id, _ -> clickedId = id }
        )
        val targetId = OrderConfirmTestData.dummyOrders.first().orderId

        composeTestRule
            .onNodeWithTag(OrderConfirmTestData.itemTag(targetId))
            .performClick()

        assertEquals(targetId, clickedId)
    }

    @Test
    fun 주문목록_없을_때_빈화면이_표시된다() {
        val emptyPage = OrderListPage(
            orders        = emptyList(),
            page          = 1,
            size          = 0,
            totalElements = 0,
            totalPages    = 1
        )

        OrderConfirmTestData.setOrderConfirmScreen(
            composeTestRule,
            newOrdersState          = UiState.Success(emptyPage),
            completedPickingsState  = UiState.Success(emptyPage)
        )

        composeTestRule
            .onNodeWithText("처리할 주문이 존재하지 않습니다.")
            .assertExists()
    }

    @Test
    fun 페이지네이션_숫자_버튼_클릭시_onLoadNew_호출된다() {
        // given
        var selectedPage = -1
        OrderConfirmTestData.setOrderConfirmScreen(
            composeTestRule,
            newPage = 1,
            newTotalPages = 3,
            onLoadNew = { selectedPage = it }
        )

        // when: 0번째 페이지 버튼 클릭
        composeTestRule
            .onNodeWithTag(OrderConfirmTestData.pageTag(0))
            .performClick()
        // then
        assertEquals(0, selectedPage)

        // when: 1번째 페이지 버튼 클릭
        composeTestRule
            .onNodeWithTag(OrderConfirmTestData.pageTag(1))
            .performClick()
        // then
        assertEquals(1, selectedPage)
    }
}