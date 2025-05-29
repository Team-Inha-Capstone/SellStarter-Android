package com.inha.sellstarter_android.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.inha.sellstarter_android.presentation.chatbot.ChatbotRoute
import com.inha.sellstarter_android.presentation.home.HomeRoute
import com.inha.sellstarter_android.presentation.inventory.InventoryViewModel
import com.inha.sellstarter_android.presentation.inventory.detail.InventoryDetailRoute
import com.inha.sellstarter_android.presentation.inventory.list.InventoryGridRoute
import com.inha.sellstarter_android.presentation.inventory.register.barcode.BarcodeConfirmDialog
import com.inha.sellstarter_android.presentation.inventory.register.InventoryRegisterRoute
import com.inha.sellstarter_android.presentation.mypage.FontSizeViewModel
import com.inha.sellstarter_android.presentation.mypage.MyPageScreen
import com.inha.sellstarter_android.presentation.onboarding.OnboardingScreen
import com.inha.sellstarter_android.presentation.onboarding.ProfileSetupScreen

@Composable
fun MainNavigator(
    navController: NavHostController,
    fontSizeViewModel: FontSizeViewModel,
    innerPadding: PaddingValues,
    modifier: Modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding),
) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {

        composable("onboarding") {
            OnboardingScreen(
                onClickStart = {
                    navController.navigate("profile/setup")
                },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
            )
        }

        composable("profile/setup") {
            ProfileSetupScreen(
                onClickNext = {
                    navController.navigate("home")
                },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
            )
        }

        composable("home") {
            HomeRoute(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                onNavigateToInventoryRegister = {
                    navController.navigate("inventory/register")
                },
                onNavigateToChatbot = {
                    navController.navigate("chatbot")
                }
            )
        }

        composable("chatbot") {
            ChatbotRoute(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            )
        }

        composable("inventory/register") {
            InventoryRegisterRoute(
                onRegisterSuccess = { navController.navigate("inventory/confirm") },
                modifier = Modifier.fillMaxSize()
            )
        }

        composable("inventory/confirm") {
            val parentEntry = remember { navController.getBackStackEntry("inventory/register") }
            val viewModel = hiltViewModel<InventoryViewModel>(parentEntry)

            BarcodeConfirmDialog(
                viewModel = viewModel,
                onDismiss = { navController.popBackStack() },
                onConfirm = {
                    navController.navigate("inventory") {
                        popUpTo("inventory/register") { inclusive = true }
                    }
                }
            )
        }

        composable("inventory") {
            InventoryGridRoute(
                modifier = modifier,
                onNavigateToDetail = { barcodeId ->
                    navController.navigate("inventory/detail/$barcodeId")
                }
            )
        }

        composable("inventory/detail/{barcodeId}") { backStackEntry ->
            val barcodeId = backStackEntry.arguments?.getString("barcodeId") ?: ""
            InventoryDetailRoute(
                barcodeId = barcodeId,
                onBack = { navController.popBackStack() },
                onClickPicking = {},
                modifier = modifier
            )
        }

        composable("mypage") {
            MyPageScreen(
                modifier = modifier,
                onFontScaleChanged = { scale ->
                    fontSizeViewModel.updateFontScale(scale)
                }
            )
        }
    }
}