package com.inha.sellstarter.ui.order.data

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.unit.dp
import com.inha.sellstarter.domain.model.OrderListPage
import com.inha.sellstarter.domain.model.OrderSummary
import com.inha.sellstarter.domain.model.type.ChannelPlatform
import com.inha.sellstarter.presentation.order.confirm.OrderConfirmScreen
import com.inha.sellstarter.ui.theme.Grey0
import com.inha.sellstarter.util.base.UiState


object OrderConfirmTestData {
    const val TAG_ROOT = "OrderConfirmRoot"
    const val TAG_TAB_ROW = "OrderTabRow"
    const val TAG_NEW_TAB = "NewOrderTab"
    const val TAG_DONE_TAB = "DoneOrderTab"
    const val TAG_PAGER = "OrderPager"
    const val TAG_PAGE_CONTENT = "OrderPageContent"

    fun itemTag(orderId: String) = "OrderItem_$orderId"

    const val TAG_PAGINATION_BAR = "PaginationBar"
    const val TAG_PREV_PAGE      = "PrevPageButton"
    const val TAG_NEXT_PAGE      = "NextPageButton"
    fun pageTag(page: Int) = "Page_$page"

    private val testChannel = ChannelPlatform.NAVER

    val dummyOrders = listOf(
        OrderSummary(
            orderId = "1001",
            inventoryItem = "테스트상품A",
            orderDate = "2025-07-01",
            channel = testChannel
        ),
        OrderSummary(
            orderId = "1002",
            inventoryItem = "테스트상품B",
            orderDate = "2025-07-02",
            channel = testChannel
        ),
        OrderSummary(
            orderId = "1003",
            inventoryItem = "테스트상품C",
            orderDate = "2025-07-03",
            channel = testChannel
        )
    )


    // 더미 페이지 (페이징 필드 포함)
    val dummyPage = OrderListPage(
        orders        = dummyOrders,
        page          = 1,
        size          = dummyOrders.size,
        totalElements = dummyOrders.size,
        totalPages    = 1
    )

    fun setOrderConfirmScreen(
        rule: ComposeContentTestRule,
        selectedTabIndex: Int = 0,
        newOrdersState: UiState<OrderListPage> = UiState.Success(dummyPage),
        completedPickingsState: UiState<OrderListPage> = UiState.Success(dummyPage),
        newPage: Int = dummyPage.page,
        newTotalPages: Int = dummyPage.totalPages,
        donePage: Int = dummyPage.page,
        doneTotalPages: Int = dummyPage.totalPages,
        selectedIds: Set<String> = emptySet(),
        onTabSelected: (Int) -> Unit = {},
        onLoadNew: (Int) -> Unit = {},
        onLoadDone: (Int) -> Unit = {},
        onItemSelect: (String) -> Unit = {},
        onSelectAll: (List<OrderSummary>) -> Unit = {},
        onOrderItemClick: (String, Boolean) -> Unit = {_,_ ->},
    ) {
        rule.setContent {
            OrderConfirmScreen(
                selectedTabIndex = selectedTabIndex,
                pagerState = rememberPagerState(
                    pageCount = { 2 },
                    initialPage = selectedTabIndex,
                ),
                newOrdersState = newOrdersState,
                completedPickingsState = completedPickingsState,
                newPage = newPage,
                newTotalPages = newTotalPages,
                donePage = donePage,
                doneTotalPages = doneTotalPages,
                selectedIds = selectedIds,
                onTabSelected = onTabSelected,
                onLoadNew = onLoadNew,
                onLoadDone = onLoadDone,
                onItemSelect = onItemSelect,
                onSelectAll = onSelectAll,
                onOrderItemClick = onOrderItemClick,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Grey0)
                    .padding(16.dp)
                    .testTag(TAG_ROOT)
            )
        }
    }
}