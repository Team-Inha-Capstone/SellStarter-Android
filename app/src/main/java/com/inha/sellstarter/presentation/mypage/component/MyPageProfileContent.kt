package com.inha.sellstarter.presentation.mypage.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inha.sellstarter.R
import com.inha.sellstarter.domain.model.UserInfo
import com.inha.sellstarter.presentation.common.component.TitleAndText
import com.inha.sellstarter.ui.theme.Grey100

@Composable
fun MyPageProfileContent(
    users: UserInfo,
    modifier: Modifier,
) {
    Box {
        Row(
            modifier = modifier.padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(R.drawable.ic_profile),
                contentDescription = "profileImage",
                modifier =
                    Modifier
                        .size(90.dp)
                        .padding(12.dp),
            )

            TitleAndText(
                titleText = "스토어 ${users.userName}님",
                contentText = "업종 : ${users.shoppingCategory}",
                modifier = Modifier,
            )
        }

        IconButton(
            onClick = { },
            modifier =
                Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 24.dp),
        ) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "설정",
                tint = Grey100,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMyPageProfileSection() {
}
