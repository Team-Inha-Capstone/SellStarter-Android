package com.inha.sellstarter_android.presentation.mypage.component.storemanage

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inha.sellstarter_android.domain.model.ShoppingMallPlatform
import com.inha.sellstarter_android.domain.model.ShoppingMallType
import com.inha.sellstarter_android.domain.model.Users
import com.inha.sellstarter_android.presentation.common.component.DefaultTextField
import com.inha.sellstarter_android.presentation.common.component.OneButton
import com.inha.sellstarter_android.presentation.common.component.TitleAndText
import com.inha.sellstarter_android.ui.theme.Grey0
import com.inha.sellstarter_android.ui.theme.Grey100
import com.inha.sellstarter_android.ui.theme.Grey900

@Composable
fun MyPageStoreAPIContent(
    users: Users,
    onClickAddKey: () -> Unit,
    onClickModifyKey: () -> Unit,
    modifier: Modifier
) {

    var apiKeyList by remember {
        mutableStateOf(
            listOf(
                ApiKey(
                    "123", ShoppingMallPlatform.NAVER.displayName, ShoppingMallPlatform.NAVER.displayImage
                ),
            )
        )
    }

    var isAdding by remember { mutableStateOf(false) }
    var newKeyText by remember { mutableStateOf("") }

    Column(modifier = modifier) {
        TitleAndText(
            titleText = "스토어 관리",
            contentText = "스토어 API Key 등록",
            isAvailableEdit = true,
            onClickEdit = { isAdding = !isAdding },
            modifier = Modifier
        )

        LazyColumn(
            contentPadding = PaddingValues(vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(apiKeyList) { apiKey ->
                ApiKeyItem(
                    apiKey = apiKey,
                    onEditClick = {

                    },
                    onDeleteClick = {

                    }
                )
            }

            if (isAdding) {
                item {
                    // 상태들
                    var expanded by remember { mutableStateOf(false) }
                    var selectedPlatform by remember { mutableStateOf(ShoppingMallPlatform.NAVER) }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, Grey100, RoundedCornerShape(6.dp))
                            .padding(12.dp)
                    ) {
                        Text("새 API Key 등록", style = MaterialTheme.typography.bodySmall)
                        Spacer(modifier = Modifier.height(8.dp))

                        // ✅ 플랫폼 선택 드롭다운
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(1.dp, Grey100, RoundedCornerShape(6.dp))
                                .clickable { expanded = true }
                                .padding(12.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Image(
                                    painter = painterResource(selectedPlatform.displayImage),
                                    contentDescription = selectedPlatform.name,
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = selectedPlatform.displayName,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Grey900
                                )
                                Spacer(modifier = Modifier.weight(1f))
                                Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                            }

                            DropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false }
                            ) {
                                ShoppingMallPlatform.values().forEach { platform ->
                                    DropdownMenuItem(
                                        text = {
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Image(
                                                    painter = painterResource(platform.displayImage),
                                                    contentDescription = null,
                                                    modifier = Modifier.size(20.dp)
                                                )
                                                Spacer(modifier = Modifier.width(8.dp))
                                                Text(text = platform.displayName)
                                            }
                                        },
                                        onClick = {
                                            selectedPlatform = platform
                                            expanded = false
                                        }
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        // ✅ API Key 입력 필드
                        DefaultTextField(
                            value = newKeyText,
                            onValueChange = { newKeyText = it },
                            innerTextFieldStyle = MaterialTheme.typography.bodyMedium.copy(color = Grey900),
                            singleLine = true,
                            borderColor = Grey100,
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        // ✅ 저장 버튼
                        OneButton(
                            text = "저장",
                            fontColor = Grey0,
                            fontStyle = MaterialTheme.typography.bodyLarge,
                            onClick = {
                                val newApiKey = ApiKey(
                                    key = newKeyText,
                                    platform = selectedPlatform.displayName,
                                    platformImage = selectedPlatform.displayImage
                                )

                                // 👇 API 통신 로직 자리 (예: viewModel.addApiKey(...))
                                apiKeyList = apiKeyList + newApiKey
                                newKeyText = ""
                                isAdding = false
                            },
                            width = 80,
                            height = 40,
                            radius = 20,
                            enabled = newKeyText.isNotBlank(),
                            modifier = Modifier.align(Alignment.End)
                        )
                    }
                }
            }
        }

    }


}

@Preview(showBackground = true)
@Composable
fun PreviewMyPageStoreAPISection() {
    MyPageStoreAPIContent(
        users = Users(1, "듀가나디 잡화점", ShoppingMallType.HOUSEHOLD_GOODS),
        onClickAddKey = { },
        onClickModifyKey = { },
        modifier = Modifier
    )
}