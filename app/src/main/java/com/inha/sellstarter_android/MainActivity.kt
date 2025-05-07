package com.inha.sellstarter_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.inha.sellstarter_android.domain.ShoppingMallType
import com.inha.sellstarter_android.domain.Users
import com.inha.sellstarter_android.presentation.mypage.FontSizeViewModel
import com.inha.sellstarter_android.presentation.mypage.MyPageScreen
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
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyPageScreen(
                        users = Users(1, "듀가나디 잡화점", ShoppingMallType.HOUSEHOLD_GOODS),
                        onFontScaleChanged = {
                            fontSizeViewModel.updateFontScale(it)
                        },
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

}