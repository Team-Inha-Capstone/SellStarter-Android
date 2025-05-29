package com.inha.sellstarter_android.presentation.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.inha.sellstarter_android.domain.model.ShoppingMallType
import com.inha.sellstarter_android.domain.model.Users
import com.inha.sellstarter_android.presentation.common.screen.ErrorScreen
import com.inha.sellstarter_android.presentation.common.screen.LoadingScreen
import com.inha.sellstarter_android.presentation.common.screen.TitleScreen
import com.inha.sellstarter_android.presentation.mypage.component.appfont.AppFontSizeContent
import com.inha.sellstarter_android.presentation.mypage.component.MyPageProfileContent
import com.inha.sellstarter_android.presentation.mypage.component.appfont.AppFontSizeDialog
import com.inha.sellstarter_android.presentation.mypage.component.appfont.FontSizeType
import com.inha.sellstarter_android.presentation.mypage.component.help.HelpContent
import com.inha.sellstarter_android.presentation.mypage.component.help.helpItems
import com.inha.sellstarter_android.presentation.mypage.component.storemanage.MyPageStoreAPIContent
import com.inha.sellstarter_android.ui.theme.Grey100
import com.inha.sellstarter_android.util.base.UiState

@Composable
fun MyPageScreen(
    onFontScaleChanged: (Float) -> Unit,
    modifier: Modifier,
    viewModel: MyPageViewModel = hiltViewModel()
) {
    val fontSizeViewModel: FontSizeViewModel = hiltViewModel()
    val fontScale by fontSizeViewModel.fontScale.collectAsState()
    val currentFontSizeType = FontSizeType.fromScale(fontScale)
    var isFontDialogVisible by remember { mutableStateOf(false) }

    val userInfoState by viewModel.userInfoState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getUserInfo()
    }
    when (userInfoState) {
        is UiState.Loading -> {
            LoadingScreen(
                loadingText = "정보를 가져오고 있습니다.",
                modifier = Modifier.fillMaxSize()
            )
        }

        is UiState.Failure -> {
            ErrorScreen(errorText = "사용자 정보를 가져오는데\n오류가 발생하였습니다.")
        }

        is UiState.Success -> {
            val userInfo = (userInfoState as UiState.Success).data
            Column(
                modifier = modifier
            ) {

                TitleScreen(title = "마이페이지")

                MyPageProfileContent(
                    users = userInfo, // 필요시 Mapper 만들어야 함
                    modifier = Modifier
                        .fillMaxWidth()
                )

                MyPageStoreAPIContent(
                    users = userInfo,
                    viewModel = viewModel,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth()
                )

                AppFontSizeContent(
                    onClickEdit = { isFontDialogVisible = true },
                    fontScale = currentFontSizeType,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                HelpContent(
                    items = helpItems,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth()
                )
            }

            if (isFontDialogVisible) {
                AppFontSizeDialog(
                    currentLimit = currentFontSizeType,
                    onLimitSelected = { onFontScaleChanged(it) },
                    onConfirm = { isFontDialogVisible = false },
                    onDismiss = { isFontDialogVisible = false }
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewMyPage() {
}

