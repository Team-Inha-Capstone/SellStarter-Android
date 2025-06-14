package com.inha.sellstarter_android

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.google.firebase.messaging.FirebaseMessaging
import com.inha.sellstarter_android.data.datasource.local.FcmTokenDataStore
import com.inha.sellstarter_android.presentation.mypage.FontSizeViewModel
import com.inha.sellstarter_android.presentation.navigation.MainBottomNavigation
import com.inha.sellstarter_android.presentation.navigation.MainNavGraph
import com.inha.sellstarter_android.ui.theme.SellStarterAndroidTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkAndRegisterFcmToken()
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
                    MainNavGraph(
                        navController = navController,
                        fontSizeViewModel = fontSizeViewModel,
                        innerPadding = innerPadding,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }
    }

    private fun checkAndRegisterFcmToken() {
        val context = applicationContext
        CoroutineScope(Dispatchers.IO).launch {
            val existingToken = FcmTokenDataStore.getToken(context).firstOrNull()
            if (existingToken.isNullOrBlank()) {
                FirebaseMessaging.getInstance().token
                    .addOnSuccessListener { newToken ->
                        Log.e("hyeon", "FCM 발급 및 저장: $newToken")
                        CoroutineScope(Dispatchers.IO).launch {
                            FcmTokenDataStore.saveToken(context, newToken)
                        }
                    }
                    .addOnFailureListener {
                        Log.e("hyeon", "FCM 토큰 발급 실패: ${it.message}")
                    }
            } else {
                Log.e("hyeon", "이미 저장된 FCM 토큰 존재: $existingToken")
            }
        }
    }
}