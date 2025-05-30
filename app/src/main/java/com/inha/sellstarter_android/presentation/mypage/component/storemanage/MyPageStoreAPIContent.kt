package com.inha.sellstarter_android.presentation.mypage.component.storemanage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.hilt.navigation.compose.hiltViewModel
import com.inha.sellstarter_android.data.model.request.mypage.UserApiDeleteRequestDto
import com.inha.sellstarter_android.data.model.request.mypage.UserApiRequestDto
import com.inha.sellstarter_android.data.model.request.mypage.UserApiUpdateRequest
import com.inha.sellstarter_android.domain.model.ShoppingMallPlatform
import com.inha.sellstarter_android.domain.model.ShoppingMallType
import com.inha.sellstarter_android.domain.model.UserInfo
import com.inha.sellstarter_android.domain.model.Users
import com.inha.sellstarter_android.presentation.common.component.DefaultTextField
import com.inha.sellstarter_android.presentation.common.component.OneButton
import com.inha.sellstarter_android.presentation.common.component.TitleAndText
import com.inha.sellstarter_android.presentation.mypage.MyPageViewModel
import com.inha.sellstarter_android.ui.theme.Grey0
import com.inha.sellstarter_android.ui.theme.Grey100
import com.inha.sellstarter_android.ui.theme.Grey900

@Composable
fun MyPageStoreAPIContent(
    users: UserInfo,
    modifier: Modifier = Modifier,
    viewModel: MyPageViewModel = hiltViewModel()
) {
    var isAdding by remember { mutableStateOf(false) }
    var newKeyText by remember { mutableStateOf("") }
    val editingKeyMap = remember { mutableStateMapOf<Int, String>() }

    Column(
        modifier = modifier
            .background(Grey0)
            .padding(vertical = 12.dp, horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        TitleAndText(
            titleText = "스토어 관리",
            contentText = "스토어 API Key 등록",
            isAvailableEdit = true,
            onClickEdit = { isAdding = !isAdding },
            modifier = Modifier.fillMaxWidth()
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            users.apiKey.forEach { apiKey ->
                val isEditing = editingKeyMap.containsKey(apiKey.apiId)
                val editingKey = editingKeyMap[apiKey.apiId] ?: apiKey.key

                if (isEditing) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, Grey100, RoundedCornerShape(6.dp))
                            .padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text("API Key 수정", style = MaterialTheme.typography.bodySmall)
                        DefaultTextField(
                            value = editingKey,
                            onValueChange = { editingKeyMap[apiKey.apiId] = it },
                            innerTextFieldStyle = MaterialTheme.typography.bodyMedium.copy(color = Grey900),
                            singleLine = true,
                            borderColor = Grey100,
                            modifier = Modifier.fillMaxWidth()
                        )
                        OneButton(
                            text = "수정 완료",
                            onClick = {
                                viewModel.updateApiKey(
                                    UserApiUpdateRequest(
                                        userId = 4,
                                        apiId = apiKey.apiId,
                                        channelId = apiKey.channelId,
                                        key = editingKey
                                    )
                                )
                                editingKeyMap.remove(apiKey.apiId)
                            },
                            width = 90,
                            height = 40,
                            fontStyle = MaterialTheme.typography.headlineSmall,
                            enabled = editingKey.isNotBlank(),
                            modifier = Modifier
                                .align(Alignment.End)
                        )
                    }
                } else {
                    ApiKeyItem(
                        apiKey = apiKey,
                        onEditClick = { editingKeyMap[apiKey.apiId] = apiKey.key },
                        onDeleteClick = {
                            viewModel.deleteApiKey(
                                request = UserApiDeleteRequestDto(
                                    userId = 4,
                                    apiId = apiKey.apiId
                                )
                            )
                        }
                    )
                }
            }
        }

        // 3) 새 키 추가 폼
        if (isAdding) {
            var expanded by remember { mutableStateOf(false) }
            var selectedPlatform by remember { mutableStateOf(ShoppingMallPlatform.NAVER) }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Grey100, RoundedCornerShape(6.dp))
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text("새 API Key 등록", style = MaterialTheme.typography.bodySmall)

                // 플랫폼 드롭다운
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
                                    Row(verticalAlignment = Alignment.CenterVertically) {
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

                DefaultTextField(
                    value = newKeyText,
                    onValueChange = { newKeyText = it },
                    innerTextFieldStyle = MaterialTheme.typography.bodyMedium.copy(color = Grey900),
                    singleLine = true,
                    borderColor = Grey100,
                    modifier = Modifier.fillMaxWidth()
                )

                OneButton(
                    text = "저장",
                    onClick = {
                        viewModel.createApiKey(
                            UserApiRequestDto(
                                userId = 4,
                                channelId = selectedPlatform.channelId,
                                key = newKeyText
                            )
                        )
                        newKeyText = ""
                        isAdding = false
                    },
                    fontStyle = MaterialTheme.typography.headlineSmall,
                    enabled = newKeyText.isNotBlank(),
                    modifier = Modifier
                        .align(Alignment.End)
                        .widthIn(min = 70.dp, max = 120.dp)
                        .height(40.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMyPageStoreAPISection() {
}