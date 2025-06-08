package com.inha.sellstarter_android.presentation.mypage.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.inha.sellstarter_android.presentation.common.screen.ErrorScreen
import com.inha.sellstarter_android.presentation.common.screen.LoadingLottieScreen
import com.inha.sellstarter_android.presentation.common.screen.LoadingScreen
import com.inha.sellstarter_android.presentation.mypage.FontSizeViewModel
import com.inha.sellstarter_android.presentation.mypage.MyPageScreen
import com.inha.sellstarter_android.presentation.mypage.MyPageViewModel
import com.inha.sellstarter_android.presentation.mypage.component.appfont.FontSizeType
import com.inha.sellstarter_android.util.base.UiState

@Composable
fun MyPageRoute(
    modifier: Modifier,
    fontSizeViewModel: FontSizeViewModel = hiltViewModel(),
    viewModel: MyPageViewModel = hiltViewModel()
) {
    val fontScale by fontSizeViewModel.fontScale.collectAsState()
    val currentFontSizeType = FontSizeType.fromScale(fontScale)

    val userInfoState by viewModel.userInfoState.collectAsState()

    when (userInfoState) {
        is UiState.Loading -> {
            LoadingScreen(
                loadingText = "사용자 정보를 가져오고 있습니다.",
                modifier = Modifier.fillMaxSize()
            )
        }

        is UiState.Failure -> {
            ErrorScreen(errorText = "사용자 정보를 가져오는데\n오류가 발생하였습니다.")
        }

        is UiState.Success -> {
            val userInfo = (userInfoState as UiState.Success).data
            MyPageScreen(
                modifier = modifier,
                userInfo = userInfo,
                fontScaleType = currentFontSizeType,
                onFontScaleChanged = fontSizeViewModel::updateFontScale,
                onCreateApiKey = viewModel::createApiKey,
                onUpdateApiKey = viewModel::updateApiKey,
                onDeleteApiKey = viewModel::deleteApiKey
            )
        }
    }
}