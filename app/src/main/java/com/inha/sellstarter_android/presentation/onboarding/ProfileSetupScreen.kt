package com.inha.sellstarter_android.presentation.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.inha.sellstarter_android.domain.model.ShoppingMallType
import com.inha.sellstarter_android.presentation.common.component.OneButton
import com.inha.sellstarter_android.presentation.common.component.OutlinedTextField
import com.inha.sellstarter_android.presentation.common.component.RadioButtonGroup
import com.inha.sellstarter_android.ui.theme.Grey900
import com.inha.sellstarter_android.ui.theme.Purple50

@Composable
fun ProfileSetupScreen(
    modifier: Modifier
) {
    var shopName by remember { mutableStateOf("") }
    var selected by remember { mutableStateOf(ShoppingMallType.GROCERY) }
    var isShopNameValid by remember { mutableStateOf(false) }
    val isButtonEnabled = isShopNameValid && selected != null

    LaunchedEffect(shopName) {
        isShopNameValid = validateShopName(shopName)
    }

    Column(modifier = modifier){
        Spacer(modifier = Modifier.weight(0.5f))

        Text(
            text = "쇼핑몰 이름을 입력해주세요.",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(24.dp)
        )

        OutlinedTextField(
            value = shopName,
            placeholder = "ex) 듀가나디 잡화점",
            isAvailable = isShopNameValid,
            isError = !isShopNameValid && shopName.isNotBlank(),
            availableDescription = "* 올바른 쇼핑몰 이름입니다.",
            errorDescription = "* 올바르지 않은 양식의 쇼핑몰 이름입니다.",
            onValueChange = {},
            innerTextFieldStyle = LocalTextStyle.current.copy(color = Grey900, fontSize = 16.sp),
            singleLine = true,
            modifier = Modifier
                .wrapContentSize()
                .padding(horizontal = 24.dp, vertical = 12.dp)
        )


        RadioButtonGroup(
            options = ShoppingMallType.values(),
            selectedOption = selected,
            onOptionSelected = { selected = it },
            labelMapper = { it.displayName},
            title = "쇼핑몰 업종을 선택해주세요.",
            modifier = Modifier.padding(24.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        OneButton(
            text = "회원가입 완료",
            buttonBackgroundColor = Purple50,
            fontColor = Grey900,
            enabled = isButtonEnabled,
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        )
    }
}

fun validateShopName(input: String): Boolean {
    val regex = "^[가-힣a-zA-Z0-9 ]+$".toRegex()
    return input.isNotBlank() && regex.matches(input)
}

@Preview(showBackground = true)
@Composable
fun PreviewProfileSetupScreen() {
    ProfileSetupScreen(
        modifier = Modifier.fillMaxSize()
    )
}