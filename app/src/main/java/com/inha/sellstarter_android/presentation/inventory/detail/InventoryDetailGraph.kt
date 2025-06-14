package com.inha.sellstarter_android.presentation.inventory.detail

import android.graphics.Paint.Align
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.inha.sellstarter_android.R
import com.inha.sellstarter_android.presentation.common.screen.EmptyScreen

@Composable
fun InventoryDetailGraph(
    titleText: String,
    graphUrl: String,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
    ) {
        Text(
            text = titleText,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(top = 24.dp, bottom = 12.dp)
        )

        if (graphUrl == "") {
            EmptyScreen(
                emptyText = "판매 정보가 없습니다.",
                emptyIcon = R.drawable.ic_empty_graph,
                modifier = Modifier
                    .height(300.dp)
                    .align(Alignment.CenterHorizontally)
            )
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(900.dp)
            ) {
                HtmlChartWebView(
                    url = graphUrl,
                    modifier = Modifier.fillMaxHeight()  // Box 전체를 채우도록
                )
            }
        }
    }
}

@Composable
fun HtmlChartWebView(url: String?, modifier: Modifier = Modifier) {
    if (url == null) {
        EmptyScreen(
            emptyIcon = R.drawable.ic_empty_box,
            emptyText = "판매 데이터가 없습니다."
        )
    } else {
        AndroidView(
            modifier = modifier
                .clip(RoundedCornerShape(10.dp))
                .height(1000.dp),
            factory = { context ->
                WebView(context).apply {
                    settings.apply {
                        javaScriptEnabled = true               // JS 허용
                        domStorageEnabled = true                // localStorage 등 허용
                        useWideViewPort = true                  // 메타뷰포트 사용
                        loadWithOverviewMode = true             // 축소/확대 모드
                        mixedContentMode =                      // http/https 혼합 허용
                            WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                        loadUrl(url)
                    }
                    settings.javaScriptEnabled = true
                    webViewClient = WebViewClient()
                }
            }, update = { webView ->
                // Compose 재구성 시 URL 갱신
                webView.loadUrl(url)
            }
        )


    }
}