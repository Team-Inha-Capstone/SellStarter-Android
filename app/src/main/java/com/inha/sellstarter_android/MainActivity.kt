package com.inha.sellstarter_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.inha.sellstarter_android.presentation.mypage.FontSizeViewModel
import com.inha.sellstarter_android.presentation.navigation.MainBottomNavigation
import com.inha.sellstarter_android.presentation.navigation.MainNavigator
import com.inha.sellstarter_android.presentation.navigation.type.BottomNavType
import com.inha.sellstarter_android.ui.theme.SellStarterAndroidTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val fontSizeViewModel: FontSizeViewModel = hiltViewModel()
            val fontScale by fontSizeViewModel.fontScale.collectAsState()
            SellStarterAndroidTheme(fontScale = fontScale) {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        MainBottomNavigation(navController)
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    MainNavigator(
                        navController = navController,
                        fontSizeViewModel = fontSizeViewModel,
                        innerPadding = innerPadding
                    )
                }
            }
        }
    }

    companion object {
        val items: List<BottomNavType> =
            listOf(
                BottomNavType.Home,
                BottomNavType.Inventory,
                BottomNavType.Order,
                BottomNavType.MyPage,
            )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

}