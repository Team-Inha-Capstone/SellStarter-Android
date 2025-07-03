package com.inha.sellstarter_android.ui.inventory

import android.net.Uri
import org.junit.Assert.*
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.inha.sellstarter_android.ui.CommonTestData
import org.junit.Rule
import org.junit.Test

class InventoryRegisterScreenTest {

    /**
     * 재고 등록 뷰 검증 항목
     * 1. 초기 화면 렌더링 시 제목·설명·폼이 표시된다
     * 2. 초기 입력 시 등록 버튼이 비활성화된다
     * 3. 필수사항(이름, 수량, 위치) 입력 후 버튼이 활성화된다
     * 4. 이미지 선택 영역 클릭 시 onImageClick 콜백이 호출된다
     * 5. 수량 필드는 무조건 숫자만 입력 가능하다
     **/

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 초기_화면_렌더링_시_재고_등록_폼이_표시된다() {
        InventoryRegisterTestData.setInventoryRegisterScreen(composeTestRule)

        composeTestRule.onNodeWithTag(CommonTestData.TAG_TITLE)
            .assertIsDisplayed()
            .assertTextEquals("재고 등록하기")
        composeTestRule.onNodeWithTag(CommonTestData.TAG_DESCRIPTION)
            .assertIsDisplayed()
            .assertTextEquals("스토어 내 재고를 등록하세요.")

        // 등록 입력 폼 확인
        with(InventoryRegisterTestData) {
            composeTestRule.onNodeWithTag(TAG_IMAGE_PICKER).assertExists()
            composeTestRule.onNodeWithTag(TAG_INVENTORY_NAME_INPUT).assertExists()
            composeTestRule.onNodeWithTag(TAG_INVENTORY_COUNT_INPUT).assertExists()
            composeTestRule.onNodeWithTag(TAG_INVENTORY_LOCATION_INPUT).assertExists()
            composeTestRule.onNodeWithTag(TAG_INVENTORY_OPTION_INPUT).assertExists()
            composeTestRule.onNodeWithTag(TAG_INVENTORY_EXPIRATION_INPUT).assertExists()
        }

        // 등록 버튼 확인
        composeTestRule
            .onNodeWithTag(InventoryRegisterTestData.TAG_REGISTER_BUTTON)
            .assertIsDisplayed()
    }

    @Test
    fun 초기_입력_시_버튼_비활성화_되어있다() {
        InventoryRegisterTestData.setInventoryRegisterScreen(composeTestRule)

        composeTestRule.onNodeWithTag(InventoryRegisterTestData.TAG_REGISTER_BUTTON)
            .assertIsNotEnabled()
    }

    @Test
    fun 필수사항_입력_후_버튼_활성화_된다() {
        InventoryRegisterTestData.setInventoryRegisterScreen(composeTestRule)

        with(composeTestRule) {
            onNodeWithTag(InventoryRegisterTestData.TAG_INVENTORY_NAME_INPUT)
                .performTextInput("상품A")
            onNodeWithTag(InventoryRegisterTestData.TAG_INVENTORY_COUNT_INPUT)
                .performTextInput("5")
            onNodeWithTag(InventoryRegisterTestData.TAG_INVENTORY_LOCATION_INPUT)
                .performTextInput("창고1")

            onNodeWithTag(InventoryRegisterTestData.TAG_REGISTER_BUTTON)
                .assertIsEnabled()
        }
    }

    @Test
    fun 이미지_선택_클릭_시_onImageClick_호출된다() {
        var clicked = false

        InventoryRegisterTestData.setInventoryForm(
            rule = composeTestRule,
            onImageClick = { clicked = true }
        )

        composeTestRule
            .onNodeWithTag(InventoryRegisterTestData.TAG_IMAGE_PICKER)
            .performClick()

        assertTrue(clicked)
    }

    @Test
    fun 수량은_무조건_숫자만_입력가능하다() {

        InventoryRegisterTestData.setInventoryRegisterScreen(composeTestRule)

        // 1. ABC!@#123def456 입력
        composeTestRule
            .onNodeWithTag(InventoryRegisterTestData.TAG_INVENTORY_COUNT_INPUT)
            .performTextInput("ABC!@#123def456")

        // 2. 다시 가져와서 검사
        composeTestRule
            .onNodeWithTag(InventoryRegisterTestData.TAG_INVENTORY_COUNT_INPUT)
            .assertTextEquals("123456")
    }
}
