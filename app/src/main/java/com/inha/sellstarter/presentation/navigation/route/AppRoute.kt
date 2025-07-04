package com.inha.sellstarter.presentation.navigation.route

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class AppRoute(val route: String) {
    // Onboarding
    data object Onboarding : AppRoute("onboarding")

    data object ProfileSetup : AppRoute("profile/setup")

    // Home
    data object Home : AppRoute("home")

    // Inventory
    data object Inventory : AppRoute("inventory")

    data object InventoryRegister : AppRoute("inventory/register")

    data object InventoryConfirm : AppRoute("inventory/confirm")

    data object InventoryScanError : AppRoute("inventory/scan/error")

    data class InventoryDetail(val barcodeId: String) :
        AppRoute("inventory/detail/{$BARCODE_ID}") {
        companion object {
            const val BARCODE_ID = "barcodeId"

            fun createRoute(barcodeId: String) = "inventory/detail/$barcodeId"

            fun navArguments() =
                listOf(
                    navArgument(BARCODE_ID) { type = NavType.StringType },
                )
        }
    }

    data class InventoryScan(val orderId: String, val barcodeId: String) :
        AppRoute("inventory/scan/{$ORDER_ID}/{$BARCODE_ID}") {
        companion object {
            const val ORDER_ID = "orderId"
            const val BARCODE_ID = "barcodeId"

            fun createRoute(
                orderId: String,
                barcodeId: String,
            ) = "inventory/scan/$orderId/$barcodeId"

            fun navArguments() =
                listOf(
                    navArgument(ORDER_ID) { type = NavType.StringType },
                    navArgument(BARCODE_ID) { type = NavType.StringType },
                )
        }
    }

    data class InventoryScanDialog(val orderId: String, val barcodeId: String) :
        AppRoute("inventory/scan/{$ORDER_ID}/{$BARCODE_ID}/dialog") {
        companion object {
            const val ORDER_ID = "orderId"
            const val BARCODE_ID = "barcodeId"

            fun createRoute(
                orderId: String,
                barcodeId: String,
            ) = "inventory/scan/$orderId/$barcodeId/dialog"

            fun navArguments() =
                listOf(
                    navArgument(ORDER_ID) { type = NavType.StringType },
                    navArgument(BARCODE_ID) { type = NavType.StringType },
                )
        }
    }

    // Order
    data object Order : AppRoute("order")

    data class OrderDetail(val orderId: String, val isFromCompleted: Boolean) :
        AppRoute("order/detail/{$ORDER_ID}/{$IS_FROM_COMPLETED}") {
        companion object {
            const val ORDER_ID = "orderId"
            const val IS_FROM_COMPLETED = "isFromCompleted"

            fun createRoute(
                orderId: String,
                isFromCompleted: Boolean,
            ) = "order/detail/$orderId/$isFromCompleted"

            fun navArguments() =
                listOf(
                    navArgument(ORDER_ID) { type = NavType.StringType },
                    navArgument(IS_FROM_COMPLETED) { type = NavType.BoolType },
                )
        }
    }

    // MyPage
    data object MyPage : AppRoute("mypage")

    // Data Analysis
    data object Chatbot : AppRoute("chatbot")

    data object DataAnalysisReport : AppRoute("analysis/report")
}
