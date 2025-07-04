package com.inha.sellstarter.presentation.mypage.component.help

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inha.sellstarter.presentation.common.component.TitleAndText
import com.inha.sellstarter.ui.theme.Grey0
import com.inha.sellstarter.ui.theme.Grey900

@Composable
fun HelpContent(
    items: List<HelpItem>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
            modifier
                .background(Grey0)
                .padding(vertical = 12.dp, horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        TitleAndText(
            titleText = "❓도움말",
            contentText = "필요시 가이드 문서를 참고하세요.",
            modifier = Modifier.fillMaxWidth(),
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.fillMaxWidth(),
        ) {
            items.forEach { item ->
                HelpItemRow(item)
            }
        }
    }
}

@Composable
fun HelpItemRow(item: HelpItem) {
    val context = LocalContext.current
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .clickable {
                    val uri = Uri.parse(item.url)
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    context.startActivity(intent)
                },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = item.title,
            style =
                MaterialTheme.typography.labelLarge.copy(
                    textDecoration = TextDecoration.Underline,
                ),
            color = Grey900,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHelpSection() {
    HelpContent(
        modifier = Modifier,
        items = helpItems,
    )
}
