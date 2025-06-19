package com.inha.sellstarter_android.presentation.analysis

import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun HtmlReportWebView(
    url: String,
    webViewRef: (WebView) -> Unit,
    modifier: Modifier = Modifier
) {
    AndroidView(modifier = modifier
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
                webViewRef(this)
            }
        },
        update = { webView ->
            // Compose 재구성 시 URL 갱신
            webView.loadUrl(url)
        })
}