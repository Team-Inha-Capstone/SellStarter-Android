package com.inha.sellstarter_android.presentation.inventory.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DatePicker
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.inha.sellstarter_android.presentation.common.component.OneButton
import com.inha.sellstarter_android.presentation.common.component.TitleAndPurplelinedTextField
import com.inha.sellstarter_android.presentation.common.component.TitleAndText
import com.inha.sellstarter_android.presentation.common.component.TitleScreen
import com.inha.sellstarter_android.ui.theme.Grey0
import com.inha.sellstarter_android.ui.theme.Purple200

@Composable
fun InventoryRegisterScreen(
    onClickRegister: () -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        TitleScreen(
            title = "재고 등록하기"
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp)
        ) {

            AsyncImage(
                model = "",
                contentDescription = "inventoryRegisterImage",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )

            TitleAndPurplelinedTextField(
                value = "",
                onValueChange = { },
                titleText = "상품명",
                modifier = Modifier
            )

            TitleAndPurplelinedTextField(
                value = "",
                onValueChange = { },
                titleText = "재고수량",
                modifier = Modifier
            )

            TitleAndPurplelinedTextField(
                value = "",
                onValueChange = { },
                titleText = "재고위치",
                modifier = Modifier
            )

            InventoryExpirationDateSection(
                modifier = Modifier
            )


        }

        OneButton(
            text = "재고등록",
            buttonBackgroundColor = Purple200,
            fontColor = Grey0,
            enabled = true,
            onClick = onClickRegister,
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        )

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewInventoryRegisterScreen() {

    InventoryRegisterScreen(
        onClickRegister = { },
        modifier = Modifier.fillMaxSize()
    )

}